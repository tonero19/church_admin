package com.tony.church.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.tony.church")
//@PropertySource("classpath:application.properties")
//@EnableJpaRepositories(basePackages = {"${spring.data.jpa.repository.packages}"})
public class ChurchAppConfig {
//    @Autowired
//    private Environment env;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.datasource")
    public DataSource appDataSource(){ return DataSourceBuilder.create().build(); }

    @Bean
    @ConfigurationProperties(prefix="security.datasource")
    public DataSource securityDataSource(){ return DataSourceBuilder.create().build(); }


}
