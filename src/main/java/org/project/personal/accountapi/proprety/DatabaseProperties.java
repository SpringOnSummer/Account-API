package org.project.personal.accountapi.proprety;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dbcp2")
public class DatabaseProperties {

    private String driverClassName;
    private String url;
    private String database;
    private String username;
    private String password;

    private int initialSize;
    private int maxTotal;
    private int minIdle;
    private int maxIdle;

    private int maxWaitMillis;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private String validationQuery;

    public String getUrl() {
        return url + "/" + database;
    }
}
