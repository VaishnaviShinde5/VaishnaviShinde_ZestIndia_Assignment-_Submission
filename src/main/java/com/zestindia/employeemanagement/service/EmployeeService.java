package com.zestindia.employeemanagement.service;

import com.zestindia.employeemanagement.dto.EmployeeRequest;
import com.zestindia.employeemanagement.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);

    Page<EmployeeResponse> getEmployeesByDepartment(String department, Pageable pageable);

    Page<EmployeeResponse> searchEmployees(String keyword, Pageable pageable);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);
}
