package com.dcits.demo.activiti.db;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.dcits.modules.test.spring.SpringTransactionalTestCase;
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class DBTest extends SpringTransactionalTestCase {

	private static Logger log = LoggerFactory.getLogger(DBTest.class);
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Test
	public void testSqlQuery(){
		
		String sql = "select count(1) rows_ from ACT_GE_BYTEARRAY";
		/*
		 * PersisternceContext 测试
		 */
		assertNotNull(entityManager);
		
		/*
		 * JDBC测试
		 */
		 List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		
		log.debug("@JdbcTemplate查询结果：第一个部署的流程名称是："+list.get(0).get("rows_"));
	}
}