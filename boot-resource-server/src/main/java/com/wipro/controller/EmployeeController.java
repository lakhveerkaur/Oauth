package com.wipro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.model.Employee;

@Controller
public class EmployeeController {
	
	@RequestMapping(value= "/user/getEmployeeList", produces="application/json")
	@ResponseBody
	public List<Employee> getEmployeeList(){
		
		List <Employee> employee = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setEmpId("la354338");
		emp.setEmpName("lakhveer");
		employee.add(emp);
		
		return employee;
		
	}

}
