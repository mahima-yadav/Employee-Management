package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping("/")
	public String home(Model m) {
		
		/*
		 * List<Employee> emp=service.getAllEmployee(); m.addAttribute("emp",emp);
		 */
		logger.info("Home page accessed.");
		return findPaginated(0, m);
//		return "index";
			
	}
    
   
	@GetMapping("/addemployee")
	public String addEmployeeForm() {
		logger.info("Add Employee page accessed.");	
		return "add_employee";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e,HttpSession session)
	{
		System.out.println(e);
		
		
		service.addEmployee(e);
		session.setAttribute("msg", "Employee Added Successfully.");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		Employee e=service.getEmpById(id);
		
		m.addAttribute("emp",e);
		logger.info("Edit page accessed.");
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee e,HttpSession session)
	{
		service.addEmployee(e);
		session.setAttribute("msg", "Employee data updated successfully.");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id,HttpSession session)
	{
		service.deleteEmployee(id);
		session.setAttribute("msg", "Employee data deleted successfully.");
		logger.info("Employee deleted.");
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Employee> emplist = service.getEMpByPaginate(pageno, 2);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}

}
