package com.company.employeepayrollapp.demo.service;

import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO updateEmployee);
    EmployeeDTO updateSalary(Long id,double newSalary);
    boolean deleteEmployee(Long id);
}
