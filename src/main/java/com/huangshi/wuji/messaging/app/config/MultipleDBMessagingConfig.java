package com.huangshi.wuji.messaging.app.config;


import com.huangshi.wuji.messaging.app.messaging.entity.MessagingEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.huangshi.wuji.messaging.app.messaging",entityManagerFactoryRef = "messagingEntityManagerFactory",
        transactionManagerRef= "messagingTransactionManager")
@EnableTransactionManagement
@EnableAutoConfiguration
//@ComponentScan("com.huangshi.wuji.messaging.app.jpa")
@EntityScan("com.huangshi.wuji.messaging.app.messaging.entity")
public class MultipleDBMessagingConfig {

    private static final String[] EntityManager_Packages_To_Scan = {"com.huangshi.wuji.messaging.app.messaging.entity"};

    /*@Bean
    @ConfigurationProperties("app.datasource.messaging")
    public DataSourceProperties messagingDataSourceProperties() {
        return new DataSourceProperties();
    }*/

    @Bean
    public DataSource messagingDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/messaging");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");
        return dataSource;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        return vendorAdapter;
    }

    private Properties jpaHibernateProperties() {

        Properties properties = new Properties();

//        properties.put(Property_Name_Hibernate_Max_Fetch_Depth, env.getProperty(Property_Name_Hibernate_Max_Fetch_Depth));
//        properties.put(Property_Name_Hibernate_JDBC_Fetch_Size, env.getProperty(Property_Name_Hibernate_JDBC_Fetch_Size));
//        properties.put(Property_Name_Hibernate_JDBC_Batch_Size, env.getProperty(Property_Name_Hibernate_JDBC_Batch_Size));
//        properties.put(Property_Name_Hibernate_Show_SQL, env.getProperty(Property_Name_Hibernate_Show_SQL));
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.create"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

//        properties.put(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
//        properties.put(AvailableSettings.USE_CLASS_ENHANCER, "false");
        return properties;
    }

    @Bean(name = "messagingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean messagingEntityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(messagingDataSource());
//        em.setPackagesToScan("com.huangshi.wuji.messaging.app.jpa.repository");
        em.setJpaVendorAdapter(vendorAdaptor());
//        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan(EntityManager_Packages_To_Scan);
        em.setJpaProperties(jpaHibernateProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager messagingTransactionManager(
            final @Qualifier("messagingEntityManagerFactory") LocalContainerEntityManagerFactoryBean messagingEntityManagerFactory) {
        return new JpaTransactionManager(messagingEntityManagerFactory.getObject());
    }

}
