package com.guddle.test.data;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class JdbcTemplateTest extends SpringTransactionalTestCase {

	private static Logger log = LoggerFactory.getLogger(JdbcTemplateTest.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	public void testSqlQuery(){
		
		String sql = "select *from T_test where name='hello'";
		/*
		 * PersisternceContext 测试
		 */
		assertNotNull(entityManager);
		
		/*
		 * JDBC测试
		 */
		String i = jdbcTemplate.queryForObject(sql, String.class);
		
		log.debug("@JdbcTemplate查询结果：第一个部署的流程名称是："+i);
	}
}
