package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	
	@GetMapping("/")
	public String home(Model m) {
		
		
		List<Employee> emp=service.getAllEmployee();
		m.addAttribute("emp",emp);
		return "index";
	}
	
	@GetMapping("/addemployee")
	public String addEmployeeForm() {
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
		return "redirect:/";
	}

}
