package com.misipuk.dictionaryServer.config


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


@Configuration @EnableTransactionManagement @Suppress("SpringKotlinAutowiring")
open class DatabaseConfig {

    @Autowired
    private val env: Environment? = null

    @Autowired
    private val dataSource: DataSource? = null

    @Autowired
    private val entityManagerFactory: LocalContainerEntityManagerFactoryBean? = null


    @Bean
    open fun dataSource(): DataSource
            = DriverManagerDataSource().apply {
        setDriverClassName(env!!.getProperty("db.driver"))
        url = env.getProperty("db.url")
        username = env.getProperty("db.username")
        password = env.getProperty("db.password")
    }


    @Bean
    open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean
            = LocalContainerEntityManagerFactoryBean().apply {
        this.dataSource = this@DatabaseConfig.dataSource!!
        setPackagesToScan(env!!.getProperty("entitymanager.packagesToScan"))
        jpaVendorAdapter = HibernateJpaVendorAdapter()
        setJpaProperties(Properties().apply {
            put("hibernate.dialect", env.getProperty("hibernate.dialect"))
            put("hibernate.show_sql", env.getProperty("hibernate.show_sql"))
            put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"))
        })
    }


    @Bean
    open fun transactionManager(): JpaTransactionManager
            = JpaTransactionManager().apply {
        entityManagerFactory = this@DatabaseConfig.entityManagerFactory!!.`object`
    }


    @Bean
    open fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor
            = PersistenceExceptionTranslationPostProcessor()


    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().apply {
        serializationInclusion(JsonInclude.Include.NON_NULL)
        configure(jacksonObjectMapper())
    }

}
