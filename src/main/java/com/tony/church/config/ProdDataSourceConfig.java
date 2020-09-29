package com.tony.church.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@Profile("production")
//@EnableWebMvc
//@ComponentScan(basePackages = "com.tony.church")
//@PropertySource("classpath:application.properties")
//@EnableJpaRepositories(basePackages = {"${spring.data.jpa.repository.packages}"})
public class ProdDataSourceConfig implements ChurchDataSource {
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
