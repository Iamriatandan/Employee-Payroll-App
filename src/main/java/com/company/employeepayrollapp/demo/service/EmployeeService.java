package com.company.employeepayrollapp.demo.service;

import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO updateEmployee);
    ResponseEntity<EmployeeDTO> updateSalary(Long id, double newSalary);
    ResponseEntity<String> deleteEmployee(Long id);
}
