package com.huangshi.wuji.messaging.app.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 查看官网：https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
 *
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.huangshi.wuji.messaging.app")
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan("com.huangshi.wuji.messaging.app")
@EntityScan("com.huangshi.wuji.messaging.app")
public class SpringDataJpaConfig {

    private static final String Property_Name_Hibernate_Dialect = "hibernate.dialect";
    private static final String Property_Name_Hibernate_Max_Fetch_Depth = "hibernate.max_fetch_depth";
    private static final String Property_Name_Hibernate_JDBC_Fetch_Size = "hibernate.jdbc.fetch_size";
    private static final String Property_Name_Hibernate_JDBC_Batch_Size = "hibernate.jdbc.batch_size";
    private static final String Property_Name_Hibernate_Show_SQL = "hibernate.show_sql";
    private static final String[] EntityManager_Packages_To_Scan = {"com.huangshi.wuji.messaging.app"};

    @Autowired
    private Environment env;

    @Bean
    public DataSource jpaDataSource() {

        //只适用于DBCP连接池，在pom.xml中已注释掉
        /*
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
        */

        /*
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/sb-all");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("123456");
        return dataSourceBuilder.build();
        */

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sb-all");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");
        return dataSource;

    }

    /*
    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
    */

    private HibernateJpaVendorAdapter vendorAdaptor() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(jpaDataSource());
//        em.setPackagesToScan("com.huangshi.wuji.messaging.app.jpa.repository");
        em.setJpaVendorAdapter(vendorAdaptor());
//        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPackagesToScan(EntityManager_Packages_To_Scan);
        em.setJpaProperties(jpaHibernateProperties());

        return em;
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


    @Primary
    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
