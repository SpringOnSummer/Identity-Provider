package org.project.personal.identity_provider.config;

import org.project.personal.identity_provider.repository.RepositoryBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

// TODO 07
//  Caused by: java.lang.IllegalStateException: Unable to retrieve @EnableAutoConfiguration base packages
@EnableJpaRepositories(basePackageClasses = RepositoryBase.class)
//@Configuration
public class JpaConfig {

    // TODO 02
    //  Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'entityManagerFactory' available
    //  Entity Manager 를 사용하기 위해 entityManagerFactory 빈 추가

    // TODO 06
    //  Entity Manager 에 Datasource 주입
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//
        // TODO 03
        //  Caused by: java.lang.IllegalStateException: No persistence units parsed from {classpath*:META-INF/persistence.xml}
        emf.setPackagesToScan("org.project.personal.identity_provider.entity");

        // TODO 04
        //  Caused by: java.lang.IllegalArgumentException: No PersistenceProvider specified in EntityManagerFactory configuration,
        //  and chosen PersistenceUnitInfo does not specify a provider class name either
        emf.setJpaVendorAdapter(jpaVendorAdapters());

        // TODO 06
        //  Set DataSource to supply JDBC connections
        emf.setDataSource(dataSource);

        return emf;
    }

    // TODO 04 JPA Vendor Adapter
    private JpaVendorAdapter jpaVendorAdapters() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);

        return hibernateJpaVendorAdapter;
    }

    // TODO 05
    //  java.lang.IllegalStateException: Failed to retrieve PlatformTransactionManager for @Transactional
    //  @Transactional 사용을 위해 PlatformTransactionManager 추가
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

}
