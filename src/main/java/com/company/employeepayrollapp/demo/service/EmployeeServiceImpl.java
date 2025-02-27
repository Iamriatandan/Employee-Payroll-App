package com.company.employeepayrollapp.demo.service;

import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import com.company.employeepayrollapp.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee.getName(), employee.getSalary()));
        }
        return employeeDTOs;
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
        employee.setId(nextId++); // Mock ID generation
        employees.add(employee);
        return new EmployeeDTO(employee.getName(), employee.getSalary());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        for (Employee emp : employees) {
            if (id != null && id.equals(emp.getId())) {
                return new EmployeeDTO(emp.getName(), emp.getSalary());
            }
        }
        return null;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updateEmployee) {
        for (Employee emp : employees) {
            if (id.equals(emp.getId())) {
                emp.setName(updateEmployee.getName());
                emp.setSalary(updateEmployee.getSalary());
                return new EmployeeDTO(emp.getName(), emp.getSalary());
            }
        }
        return null;
    }

    @Override
    public EmployeeDTO updateSalary(Long id, double newSalary) {
        for (Employee employee : employees) {
            if (id.equals(employee.getId())) {
                employee.setSalary(newSalary);
                return new EmployeeDTO(employee.getName(), employee.getSalary());
            }
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Long id){
        return employees.removeIf(employee -> employee.getId().equals(id));
    }
}
