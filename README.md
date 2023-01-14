## CDC with Hibernate

### Demo

1. Build the application

```shell
mvn install
```

2. Start docker compose
```shell
docker compose up
```

3. Run the transmitter app

```shell
cd cdc-transmitter
mvn spring-boot:run
```

4. Run the receiver app

```shell
cd cdc-receiver
mvn spring-boot:run
```

5. Execute CRUD operators

- Open the Swagger UI: http://localhost:8080/swagger-ui/index.html
- Run some Insert/Update/Delete
- See the events appearing in the receiver log
