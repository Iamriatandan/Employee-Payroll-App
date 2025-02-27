package com.company.employeepayrollapp.demo.service;

import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import com.company.employeepayrollapp.demo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final List<Employee> employees = new ArrayList<>();
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
        employee.setId((long) (employees.size() + 1)); // Mock ID generation
        employees.add(employee);
        return employeeDTO;
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
    public ResponseEntity<EmployeeDTO> updateSalary(Long id, double newSalary) {
        for (Employee emp : employees) {
            if (id.equals(emp.getId())) {
                emp.setSalary(newSalary);
                return ResponseEntity.ok(new EmployeeDTO(emp.getName(), emp.getSalary()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> deleteEmployee(Long id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            if (id.equals(iterator.next().getId())) {
                iterator.remove();
                return ResponseEntity.ok("Employee deleted");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }
}
