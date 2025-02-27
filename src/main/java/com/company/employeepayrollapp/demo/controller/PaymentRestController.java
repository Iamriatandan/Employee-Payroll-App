package com.company.employeepayrollapp.demo.controller;
import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import com.company.employeepayrollapp.demo.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class PaymentRestController {
        //creating a list of employees
        private final List<Employee> employees = new ArrayList<>();

        //Gives all Employees USING dto object
        @GetMapping
        public List<EmployeeDTO> getAllEmployees(){

            List<EmployeeDTO>employeeDTOS = new ArrayList<>();
            for(Employee employee: employees){
                employeeDTOS.add(new EmployeeDTO(employee.getName(),employee.getSalary()));
            }
            return employeeDTOS;
        }

        //search employee by id
        @GetMapping("/{id}")
        public Employee getEmployeeById(@PathVariable Long id) {
            for (Employee emp : employees) {
                if (id != null && id.equals(emp.getId())) {
                    return emp;
                }
            }
            return null;
        }

        //add employee using dto
        @PostMapping
        public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
            //Creating object
            Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getSalary());
            employee.setId((long)(employees.size()+1)); // generate mock id
            employees.add(employee);
            return employeeDTO;
        }

        //updating employees  based on id
        @PutMapping("/{id}")
        public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee){
            for(int i =0;i<employees.size();i++){
                if(id!=null && id.equals(employees.get(i).getId())){
                    employees.set(i,updateEmployee);
                }
            }
            return null;
        }

        //updating salary of employee based on salary
        @PutMapping("/{id}/salary")
        public ResponseEntity<Employee> updateSalary(@PathVariable Long id, @RequestBody double newSalary){
            for(Employee emp : employees){
                if(id.equals(emp.getId())){
                    emp.setSalary(newSalary);
                    return ResponseEntity.ok(emp);
                }
            }
            return ResponseEntity.ok(null);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
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