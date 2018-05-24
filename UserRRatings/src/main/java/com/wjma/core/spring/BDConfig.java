package com.wjma.core.spring;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class BDConfig {

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) throws IllegalArgumentException, NamingException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}
}
