package com.company.employeepayrollapp.demo.controller;
import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import com.company.employeepayrollapp.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class PaymentRestController {
    private final EmployeeService employeeService;

    @Autowired
    public PaymentRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET: Fetch all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // POST: Add new employee
    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employee = Optional.ofNullable(employeeService.getEmployeeById(id));
        return employee.map(ResponseEntity::ok) // If present, return 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody(); // If empty, return 404

    }
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updateEmployee) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id,updateEmployee);
        return ResponseEntity.of(Optional.ofNullable(updatedEmployee)).getBody();
            }

    @PutMapping("/{id}/salary")
    public ResponseEntity<EmployeeDTO> updateSalary(@PathVariable Long id, @RequestBody double newSalary) {
        EmployeeDTO updatedEmployees = employeeService.updateSalary(id,newSalary);
        return (updatedEmployees != null)?ResponseEntity.ok(updatedEmployees):ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id)?
                ResponseEntity.ok("Employee deleted"):
                ResponseEntity.status(404).body("Employee not found");
    }

}