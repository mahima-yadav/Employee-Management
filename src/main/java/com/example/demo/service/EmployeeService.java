package com.example.demo.service;

import java.util.List;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
@Service
public class EmployeeService {
	
	
	@Autowired
	private EmployeeRepository repo;
	public void addEmployee(Employee e)
	{
		repo.save(e);
	}
	
	public List<Employee> getAllEmployee()
	{
		return repo.findAll();
	}
	
	public Employee getEmpById(int id)
	{
		Optional<Employee> e=repo.findById(id);
		if(e.isPresent())
		{
			return e.get();
		}
		return null;
	}
	
	public void deleteEmployee(int id)
	{
		repo.deleteById(id);
	}
	
	public Page<Employee> getEMpByPaginate(int currentPage, int size)
	{
		Pageable p = PageRequest.of(currentPage, size);
		return repo.findAll(p);
	}
	

}
