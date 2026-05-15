package com.zestindia.employeemanagement.repository;

import com.zestindia.employeemanagement.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DisplayName("Employee Repository Integration Tests")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

        employee1 = Employee.builder()
                .name("Alice Smith")
                .email("alice@example.com")
                .department("Engineering")
                .position("Developer")
                .salary(new BigDecimal("70000.00"))
                .dateOfJoining(LocalDate.of(2022, 3, 1))
                .build();

        employee2 = Employee.builder()
                .name("Bob Johnson")
                .email("bob@example.com")
                .department("Marketing")
                .position("Manager")
                .salary(new BigDecimal("80000.00"))
                .dateOfJoining(LocalDate.of(2021, 6, 15))
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
    }

    @Test
    @DisplayName("Should find employee by email")
    void findByEmail_Success() {
        var result = employeeRepository.findByEmail("alice@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Alice Smith");
    }

    @Test
    @DisplayName("Should return empty when email not found")
    void findByEmail_NotFound() {
        var result = employeeRepository.findByEmail("notfound@example.com");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should check email existence correctly")
    void existsByEmail_ReturnsTrue() {
        assertThat(employeeRepository.existsByEmail("alice@example.com")).isTrue();
        assertThat(employeeRepository.existsByEmail("unknown@example.com")).isFalse();
    }

    @Test
    @DisplayName("Should find employees by department")
    void findByDepartment_Success() {
        Page<Employee> result = employeeRepository.findByDepartment(
                "Engineering", PageRequest.of(0, 10));

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Alice Smith");
    }

    @Test
    @DisplayName("Should search employees by name keyword")
    void searchEmployees_ByName() {
        Page<Employee> result = employeeRepository.searchEmployees(
                "alice", PageRequest.of(0, 10));

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    @DisplayName("Should search employees by department keyword")
    void searchEmployees_ByDepartment() {
        Page<Employee> result = employeeRepository.searchEmployees(
                "Marketing", PageRequest.of(0, 10));

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Bob Johnson");
    }

    @Test
    @DisplayName("Should return all employees with pagination")
    void findAll_WithPagination() {
        Page<Employee> result = employeeRepository.findAll(PageRequest.of(0, 1));

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getTotalPages()).isEqualTo(2);
    }
}
