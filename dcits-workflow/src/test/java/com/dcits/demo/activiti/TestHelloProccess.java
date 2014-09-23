package com.dcits.demo.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.dcits.modules.test.spring.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class TestHelloProccess extends SpringTransactionalTestCase{
	
	private static Logger log  = LoggerFactory.getLogger(TestHelloProccess.class);

	@Autowired
	private WorkFlowService proccess;
	
	
	@Test
	public void testHelloProccess(){
	
		proccess.hello();
//		log.debug("------------------------------"+proccess.getProcessDefinition(proccess.getProcessName("helloProcess")).getName());
		
	}
	
	@Test
	public void testGetDeployProcess(){
		proccess.vacationRequest();
	}
}
