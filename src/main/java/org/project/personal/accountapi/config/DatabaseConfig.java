package org.project.personal.accountapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.project.personal.accountapi.proprety.DatabaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.getDriverClassName());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUsername());
        dataSource.setPassword(databaseProperties.getPassword());

        dataSource.setMaxIdle(databaseProperties.getMaxIdle());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setMaxTotal(databaseProperties.getMaxTotal());
        dataSource.setInitialSize(databaseProperties.getInitialSize());

        dataSource.setTestOnBorrow(databaseProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(databaseProperties.isTestOnReturn());
        dataSource.setValidationQuery(databaseProperties.getValidationQuery());

        return dataSource;
    }


}
