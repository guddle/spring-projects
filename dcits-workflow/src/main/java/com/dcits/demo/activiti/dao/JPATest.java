package com.dcits.demo.activiti.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

//@RepositoryDefinition(domainClass = Act_ge_property.class, idClass = String.class)
public interface JPATest{

	//@Query("From Act_ge_property a where a.name=:name")
	//public Act_ge_property findByName(@Param("name") String name);
}
