package com.company.employeepayrollapp.demo.service;
import com.company.employeepayrollapp.demo.model.Employee;
import com.company.employeepayrollapp.demo.repository.EmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;
@Service
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

public Employee saveEmployee(Employee employee){
    log.info("Saving employee: {}",employee);
    return employeeRepository.save(employee);
}

public List<Employee> getAllEmployees() {
    log.info("Retrieving all Employees from database");
    return employeeRepository.findAll();
}

public Employee getEmployeeById(Long id){
    log.info("Retrieving employee with id : {} " , id);
    return employeeRepository.findById(id).orElse(null);
}

public Employee updateEmployee(Long id, Employee newEmployeeData){
    log.info("Updating employee with id: {}",id);
    Employee existingEmployee = employeeRepository.findById(id).orElse(null);

    if(existingEmployee != null){
        existingEmployee.setName(newEmployeeData.getName());
        existingEmployee.setSalary(newEmployeeData.getSalary());
        return employeeRepository.save(existingEmployee);
    }
    log.warn("Employee with ID {} not found",id);
    return null;
}

public String deleteEmployee(Long id){
    log.warn("Deleting employee with id: {}",id);
    employeeRepository.deleteById(id);
    return "Employee Deleted";
}
}
