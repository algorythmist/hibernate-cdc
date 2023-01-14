package com.tecacet.cdc;

import com.tecacet.cdc.model.CdcEvent;

import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO: only apply to selected Entities
//TODO: only monitor specific fields
//TODO: handle exceptions during processing
@Component
@Slf4j
@RequiredArgsConstructor
public class CdcHibernateListener implements PostInsertEventListener, PostUpdateEventListener,
        PostDeleteEventListener {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        var persister = (AbstractEntityPersister) postDeleteEvent.getPersister();
        var id = persister.getIdentifier(postDeleteEvent.getEntity());
        var event = CdcEvent.builder()
                .action(CdcEvent.Action.DELETE)
                .uniqueIdentifier(id.toString())
                .tableName(persister.getTableName())
                .build();
        rabbitTemplate.convertAndSend(QueueConfig.topicExchangeName, QueueConfig.binding, event);
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        var persister = (AbstractEntityPersister) postInsertEvent.getPersister();
        var id = persister.getIdentifier(postInsertEvent.getEntity());
        Object[] state = postInsertEvent.getState();
        String[] propertyNames = persister.getPropertyNames();
        var properties = IntStream.range(0, state.length).boxed()
                .filter(i -> state[i] != null)
                .collect(Collectors.toMap(i -> propertyNames[i], i -> state[i]));
        var event = CdcEvent.builder()
                .action(CdcEvent.Action.INSERT)
                .uniqueIdentifier(id.toString())
                .tableName(persister.getTableName())
                .properties(properties)
                .build();
        rabbitTemplate.convertAndSend(QueueConfig.topicExchangeName, QueueConfig.binding, event);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        var persister = (AbstractEntityPersister) postUpdateEvent.getPersister();
        var id = persister.getIdentifier(postUpdateEvent.getEntity());
        Object[] state = postUpdateEvent.getState();
        int[] updated = postUpdateEvent.getDirtyProperties();
        String[] propertyNames = persister.getPropertyNames();
        var properties = Arrays.stream(updated).boxed()
                .filter(i -> state[i] != null)
                .collect(Collectors.toMap(i -> propertyNames[i], i -> state[i]));
        var event = CdcEvent.builder()
                .action(CdcEvent.Action.UPDATE)
                .uniqueIdentifier(id.toString())
                .tableName(persister.getTableName())
                .properties(properties)
                .build();
        rabbitTemplate.convertAndSend(QueueConfig.topicExchangeName, QueueConfig.binding, event);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

}
