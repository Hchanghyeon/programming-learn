package com.lock.namedlock.common;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplateDataSource());
    }

    @Bean
    @ConfigurationProperties(prefix = "jdbc.datasource")
    public DataSource jdbcTemplateDataSource() {
        return DataSourceBuilder.create().build();
    }
}
