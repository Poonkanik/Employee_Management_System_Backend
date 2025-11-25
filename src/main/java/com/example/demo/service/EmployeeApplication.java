package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeApplication {

    private final EmployeeRepository repo;

    public EmployeeApplication(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repo.findById(id);
    }

    public boolean deleteEmployee(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return repo.findById(id)
                .map(emp -> {
                    emp.setName(updatedEmployee.getName());
                    emp.setEmail(updatedEmployee.getEmail());
                    emp.setRole(updatedEmployee.getRole());
                    emp.setExperience(updatedEmployee.getExperience());
                    emp.setSalary(updatedEmployee.getSalary());
                    return repo.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
