package com.rucsok.test.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {	
	
	private static final String RUCSOK = "rucsok";
	private static final String PACKAGES_TO_SCAN = "com.rucsok";
	private static final String DATABASE_SCRIPT = "classpath:database/integration-test.sql";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(HibernateJpaVendorAdapter jpaVendorAdapter, DataSource rucsokDataSource){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName(RUCSOK);
		em.setDataSource(rucsokDataSource);
		em.setPackagesToScan(PACKAGES_TO_SCAN);
		em.setJpaVendorAdapter(jpaVendorAdapter);
		return em;
	}
	
	public JpaTransactionManager transactionManager(EntityManagerFactory emf, DataSource rucsokDataSource){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		transactionManager.setDataSource(rucsokDataSource);
		return transactionManager;
	}
	
	@Bean
	public DataSource rucsokDataSource(){
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().
				              setType(EmbeddedDatabaseType.HSQL).
				              addScript(DATABASE_SCRIPT).
				              build();
			
		return db;
	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setGenerateDdl(false);
		return jpaVendorAdapter;
	}
	
}
