package com.dcits.demo.activiti;

import static org.junit.Assert.assertEquals;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.dcits.modules.test.spring.SpringTransactionalTestCase;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ActivitiTestCase extends SpringTransactionalTestCase{
	
	private Logger log = LoggerFactory.getLogger(ActivitiTestCase.class);
	
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule; 
	
	@Test
	@Deployment
	public void simpleProccessTest(){
		runtimeService.startProcessInstanceByKey("simpleProcess");
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("My Task",task.getName());
		log.debug("----------------------task name is:"+task.getTaskDefinitionKey());
		
		taskService.complete(task.getId());
		assertEquals(0,runtimeService.createProcessInstanceQuery().count());
		
	}

	
}
