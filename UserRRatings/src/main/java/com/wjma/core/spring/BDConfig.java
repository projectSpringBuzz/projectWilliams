package com.wjma.core.spring;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * class of spring configuration (database)
 * enable transaction in spring
 * @author wjma
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class BDConfig {

	@Autowired
    DataSource dataSource;

	/**
	 * Utility of spring to access database
	 * @param dataSource
	 * @return
	 * @throws IllegalArgumentException
	 * @throws NamingException
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) throws IllegalArgumentException, NamingException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
	
	/**
	 * Manager of transaction
	 * @return
	 */
	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
