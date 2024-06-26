package com.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/")
	public String viewAllEmployee(Model model)
	{
	    model.addAttribute("employeelist",employeeRepository.findAll());
	    return "index";
	}
	
	
	@GetMapping("/addEmp")
	public String addEmployee(Model model)
	{
		Employee emp= new Employee();
		model.addAttribute("employee",emp);
		return "add_employee";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmployee(@ModelAttribute("employee")Employee employee)
	{
		employeeRepository.save(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showUpdatefrom/{id}")
	public String showUpdateForm(@PathVariable(value = "id")int id,Model model)
	{
	 Employee employee=employeeRepository.findByid(id);
	
	 model.addAttribute("employeedata", employee);
	 return "update_employee";
	 
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute("employeedata")Employee employee)
	{
		employeeRepository.save(employee);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable(value = "id")int id)
	{
		employeeRepository.deleteById(id);
		return "redirect:/";
	}
	
	
}
