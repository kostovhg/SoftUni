package minionsORM.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static minionsORM.utilities.Constants.CONN_OPTIONS;
import static minionsORM.utilities.Constants.URL_FORMAT;

public class Connector {
    private EntityManagerBuilder builder;
    private String adapter;
    private String driver;
    private String host;
    private String port;
    private String user;
    private String pass;
    private List<String> options;

    public Connector(EntityManagerBuilder entityManagerBuilder) {
        this.builder = entityManagerBuilder;
    }

    public EntityManagerBuilder createConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", this.user);
        props.setProperty("password", this.pass);

        String URL = String.format(URL_FORMAT,
                this.driver,
                this.adapter,
                this.host,
                this.port,
                String.join("&", this.options));

        Connection connection = DriverManager.getConnection(
                URL, props);
        this.builder.setConnection(connection);
        return this.builder;
    }

    public Connector setBuilder(EntityManagerBuilder builder) {
        this.builder = builder;
        return this;
    }

    public Connector setAdapter(String adapter) {
        this.adapter = adapter;
        return this;
    }

    public Connector setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public Connector setHost(String host) {
        this.host = host;
        return this;
    }

    public Connector setPort(String port) {
        this.port = port;
        return this;
    }

    public Connector setUser(String user) {
        this.user = user;
        return this;
    }

    public Connector setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public Connector setOptions(String options){
        this.options = CONN_OPTIONS.entrySet()
                .stream()
                .map(s -> s.getKey() + "=" + s.getValue())
                .collect(Collectors.toList());
        this.options.addAll(Arrays.asList(options.split("&")));
        return this;
    }
}
