package com.dcits.demo.activiti;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkFlowService {

	public static Logger log = LoggerFactory.getLogger(WorkFlowService.class);
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	/**
	 * 部署工作流程，按找classPath部署
	 */
	public String proccessDeploys(String proccessPath) {
		String deployId = repositoryService.createDeployment()
				.addClasspathResource(proccessPath).deploy().getId();
		return deployId;
	}

	/**
	 * 测试helloProcess的流程
	 */
	public void hello() {
//		 proccessDeploys("com/dcits/demo/activiti/hello.bpmn20.xml");
//		repositoryService.createProcessDefinitionQuery().
		runtimeService.startProcessInstanceByKey("helloProcess");
	}

	/**
	 * 按文件部署流程"com/dcits/demo/activiti/leave.bar"
	 * 
	 * @param path
	 */
	public void streamDeloys(String path) {
		try {
			ZipInputStream ins = new ZipInputStream(new FileInputStream(path));
			repositoryService.createDeployment().name("leave.bar")
					.addZipInputStream(ins).deploy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询流程定义对象
	 */
	
	public ProcessDefinition getProcessDefinition(String processDefinitionId){
		return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	}

	/**
	 * 测试FinancialReportProcess.bpmn20.xml部署情况
	 */
	public String getProcessName(String processKey){
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processDefinitionKey(processKey).singleResult();
		return instance.getId();
	}
	
	/**
	 * 
	 */
	public void vacationRequest(){
		proccessDeploys("com/dcits/demo/activiti/VacationRequest.bpmn20.xml");
		log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
		/*
		 * 把流程定义发布到Activiti引擎后，我们可以基于它发起新流程实例。 对每个流程定义，都可以有很多流程实例。 流程定义是“蓝图”，流程实例是它的一个运行的执行。
		 * 所有与流程运行状态相关的东西都可以通过RuntimeService获得。 有很多方法可以启动一个新流程实例。在下面的代码中，我们使用定义在流程定义xml 
		 * 中的key来启动流程实例。 我们也可以在流程实例启动时添加一些流程变量，因为第一个用户任务的表达式需要这些变量。 流程变量经常会被用到，因为它们赋予来自同
		 * 一个流程定义的不同流程实例的特别含义。 简单来说，流程变量是区分流程实例的关键。
		 */
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays",new Integer(4));
		variables.put("vacationMotivation", "我很累了，想休息下！");
		runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		
	    /*流程启动后，第一步就是用户任务。这是必须由系统用户处理的一个环节。 通常，用户会有一个“任务列表”，
	     * 展示了所有必须由整个用户处理的任务。 下面的代码展示了对应的查询可能是怎样的：*/
		log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
			log.info("Task available: " + task.getName());
		}
		
		//为了让流程实例继续运行，我们需要完成整个任务。对Activiti来说，就是需要complete任务。 下面的代码展示了如何做这件事：
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(),taskVariables);
		
		/*
		 * 流程实例会进入到下一个环节。在这里例子中， 下一环节允许员工通过表单调整原始的请假申请。
		 * 员工可以重新提交请假申请， 这会使流程重新进入到第一个任务。
		 */
		/*repositoryService.suspendProcessDefinitionByKey("vacationRequest");
		try {
		  repositoryService.activateProcessDefinitionByKey("vacationRequest");
		  runtimeService.startProcessInstanceByKey("vacationRequest");
		} catch (ActivitiException e) {
		  e.printStackTrace();
		}*/
		
		List<Task> tasks_kemit = taskService.createTaskQuery()
				.taskAssignee("kermit")
				.processVariableValueEquals("orderId", "0815")
				.orderByDueDate().asc().list();
		log.info("---------------"+tasks_kemit.size());
	}
	
	public void queryWorkFolow(){
//		proccessDeploys("com/dcits/demo/activiti/hello.bpmn20.xml");
		
	}
}
