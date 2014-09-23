package com.dcits.demo.activiti.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工作流的测试控制器
 * @author guddle
 *
 */
@Controller
@RequestMapping("/test")
public class WorkFlowTestCtrl {

	
	public boolean workflowDeploys(){
		return true;
	}
}
