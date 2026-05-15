package com.zestindia.employeemanagement.service;

import com.zestindia.employeemanagement.dto.EmployeeRequest;
import com.zestindia.employeemanagement.dto.EmployeeResponse;
import com.zestindia.employeemanagement.entity.Employee;
import com.zestindia.employeemanagement.exception.DuplicateResourceException;
import com.zestindia.employeemanagement.exception.ResourceNotFoundException;
import com.zestindia.employeemanagement.repository.EmployeeRepository;
import com.zestindia.employeemanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Service Unit Tests")
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequest employeeRequest;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .department("Engineering")
                .position("Software Engineer")
                .salary(new BigDecimal("75000.00"))
                .dateOfJoining(LocalDate.of(2023, 1, 15))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        employeeRequest = EmployeeRequest.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .department("Engineering")
                .position("Software Engineer")
                .salary(new BigDecimal("75000.00"))
                .dateOfJoining(LocalDate.of(2023, 1, 15))
                .build();
    }

    // ==================== CREATE TESTS ====================

    @Test
    @DisplayName("Should create employee successfully")
    void createEmployee_Success() {
        when(employeeRepository.existsByEmail(anyString())).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse response = employeeService.createEmployee(employeeRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("John Doe");
        assertThat(response.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(response.getDepartment()).isEqualTo("Engineering");

        verify(employeeRepository, times(1)).existsByEmail("john.doe@example.com");
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when email already exists")
    void createEmployee_DuplicateEmail_ThrowsException() {
        when(employeeRepository.existsByEmail(anyString())).thenReturn(true);

        assertThatThrownBy(() -> employeeService.createEmployee(employeeRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("john.doe@example.com");

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // ==================== GET TESTS ====================

    @Test
    @DisplayName("Should get employee by ID successfully")
    void getEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponse response = employeeService.getEmployeeById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when employee not found")
    void getEmployeeById_NotFound_ThrowsException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Employee");
    }

    @Test
    @DisplayName("Should return paginated list of employees")
    void getAllEmployees_ReturnsPaginatedList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Employee> employeePage = new PageImpl<>(List.of(employee), pageable, 1);

        when(employeeRepository.findAll(pageable)).thenReturn(employeePage);

        Page<EmployeeResponse> result = employeeService.getAllEmployees(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("John Doe");
    }

    // ==================== UPDATE TESTS ====================

    @Test
    @DisplayName("Should update employee successfully")
    void updateEmployee_Success() {
        EmployeeRequest updateRequest = EmployeeRequest.builder()
                .name("John Updated")
                .email("john.doe@example.com")
                .department("Management")
                .position("Senior Engineer")
                .salary(new BigDecimal("90000.00"))
                .dateOfJoining(LocalDate.of(2023, 1, 15))
                .build();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        lenient().when(employeeRepository.existsByEmail(anyString())).thenReturn(false);
        when(employeeRepository.save(any(Employee.class))).thenAnswer(inv -> inv.getArgument(0));

        EmployeeResponse response = employeeService.updateEmployee(1L, updateRequest);

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("John Updated");
        assertThat(response.getDepartment()).isEqualTo("Management");
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating non-existent employee")
    void updateEmployee_NotFound_ThrowsException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(99L, employeeRequest))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    // ==================== DELETE TESTS ====================

    @Test
    @DisplayName("Should delete employee successfully")
    void deleteEmployee_Success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        assertThatCode(() -> employeeService.deleteEmployee(1L))
                .doesNotThrowAnyException();

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting non-existent employee")
    void deleteEmployee_NotFound_ThrowsException() {
        when(employeeRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.deleteEmployee(99L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(employeeRepository, never()).deleteById(any());
    }

    // ==================== SEARCH TESTS ====================

    @Test
    @DisplayName("Should search employees by keyword")
    void searchEmployees_ReturnsMatchingResults() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employeePage = new PageImpl<>(List.of(employee), pageable, 1);

        when(employeeRepository.searchEmployees("john", pageable)).thenReturn(employeePage);

        Page<EmployeeResponse> result = employeeService.searchEmployees("john", pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).containsIgnoringCase("John");
    }
}