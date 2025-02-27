package com.company.employeepayrollapp.demo.controller;
import com.company.employeepayrollapp.demo.dto.ResponseDTO;
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

        //Gives all Employees
        @GetMapping
        public List<Employee> findAllEmployees(){
            return employees;
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

        //add employe
        @PostMapping
        public Employee addEmployee(@RequestBody Employee employee){
            employees.add(employee);
            return employee;
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