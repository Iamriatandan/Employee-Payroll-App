package com.company.employeepayrollapp.demo.controller;
import com.company.employeepayrollapp.demo.model.Employee;
import com.company.employeepayrollapp.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@Slf4j
public class PaymentRestController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        log.info("Addeing new Employee : {} ", employee );
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employee>getAllEmployees(){
        log.info("Fetching all employees ");
        return employeeService.getAllEmployees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        log.info("Fetching employee with ID : {}", id);
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id , @RequestBody Employee employee){
        log.info("Updating employee with id : {} ",id);
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        log.warn("Deleting employee with id : {}", id);
        return employeeService.deleteEmployee(id);
    }
}