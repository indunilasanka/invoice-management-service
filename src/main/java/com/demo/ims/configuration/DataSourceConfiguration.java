package com.demo.ims.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${invoice.db.min.pool.size}")
    private int minPoolSize;
    @Value("${invoice.db.max.pool.size}")
    private int maxPoolSize;
    @Value("${invoice.db.endpoint}")
    private String endpoint;
    @Value("${invoice.db.name}")
    private String databaseName;
    @Value("${invoice.db.username}")
    private String username;
    @Value("${invoice.db.password}")
    private String password;

    @Bean
    public AnnotationMBeanExporter annotationMBeanExporter() {
        AnnotationMBeanExporter annotationMBeanExporter = new AnnotationMBeanExporter();
        annotationMBeanExporter.addExcludedBean("dataSource");
        annotationMBeanExporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
        return annotationMBeanExporter;
    }

    @Bean(name = "invoiceDataSource")
    public DataSource invoiceDataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            hikariConfig.setPoolName("db-pool");
            hikariConfig.setMaximumPoolSize(maxPoolSize);
            hikariConfig.setMinimumIdle(minPoolSize);
            hikariConfig.setJdbcUrl("jdbc:mysql://" + endpoint + ":3306/" + databaseName);
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.addDataSourceProperty("cachePrepStmts", true);
            hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
            return new HikariDataSource(hikariConfig);
        } catch (Exception e) {
            LOGGER.error("error: Connection to the Database failed", e);
            throw new BeanInitializationException("Database connection failure");
        }
    }
}
