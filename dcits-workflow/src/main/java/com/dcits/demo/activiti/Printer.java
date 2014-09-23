package com.dcits.demo.activiti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Printer {
	private static Logger log = LoggerFactory.getLogger(Printer.class);

	public void printMessage(){
		log.info("hello World!");
	}
	
	public void simpleProcess(){
		log.info("simpleProcess Test!");
	}
}
