package com.jws.settings.config.database;


import com.jws.settings.constants.Etc;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
    basePackages = {
        "com.jws.repo.main"
    },
    entityManagerFactoryRef = "mainEntityManagerFactory",
    transactionManagerRef = "mainTransactionManager"
)
public class DatabaseConfigMain {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariConfig mainHikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean
  public DataSource mainDataSource() {
    HikariConfig config = mainHikariConfig();
    String url = config.getJdbcUrl();
    config.setJdbcUrl(url + Etc.MYSQL_CONNECTION_PROPERTIES);
    return new HikariDataSource(config);
  }

  @Primary
  @Bean
  public PlatformTransactionManager mainTransactionManager() {
    EntityManagerFactory factory = mainEntityManagerFactory().getObject();
    return new JpaTransactionManager(factory);
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(mainDataSource());
    factory.setPackagesToScan(
        "com.jws.domain.main"
    );
    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factory.setPersistenceUnitName("main");

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.hbm2ddl.auto", "update");
    jpaProperties.setProperty("hibernate.physical_naming_strategy",
        "com.jws.settings.config.database.NamingStrategy");
    factory.setJpaProperties(jpaProperties);

    return factory;
  }

  @Primary
  @Bean
  public DataSourceInitializer mainDataSourceInitializer() {
    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(mainDataSource());
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    initializer.setDatabasePopulator(databasePopulator);
    initializer.setEnabled(false);
    return initializer;
  }
}
