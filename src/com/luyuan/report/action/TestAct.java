package com.luyuan.report.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.BaseAct;
import com.luyuan.report.model.Person;

public class TestAct extends BaseAct {

	@Override
	public String execute() throws Exception {
		persons.add(new Person("101", "小博", 22, "湖北"));    
		persons.add(new Person("102", "张三", 21, "湖南"));    
		persons.add(new Person("103", "李四", 23, "江苏"));    
		persons.add(new Person("104", "王五", 22, "上海"));    
   
		return "success";
	}

	private List<Person> persons = new ArrayList<Person>();

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}
	
	
}
