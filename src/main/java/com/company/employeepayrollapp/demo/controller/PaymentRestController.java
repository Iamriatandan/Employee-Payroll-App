package com.company.employeepayrollapp.demo.controller;
import com.company.employeepayrollapp.demo.dto.EmployeeDTO;
import com.company.employeepayrollapp.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updateEmployee) {
        return employeeService.updateEmployee(id, updateEmployee);
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<EmployeeDTO> updateSalary(@PathVariable Long id, @RequestBody double newSalary) {
        return employeeService.updateSalary(id, newSalary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

}