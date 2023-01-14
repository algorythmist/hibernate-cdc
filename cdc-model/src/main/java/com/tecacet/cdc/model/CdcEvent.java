package com.tecacet.cdc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CdcEvent {

    public enum Action {
        INSERT, UPDATE, DELETE
    }

    private String uniqueIdentifier;

    private Action action;
    private String tableName;

    private Map<String, Object> properties;
}
