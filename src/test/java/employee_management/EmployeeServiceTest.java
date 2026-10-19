
package employee_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployeeShouldSaveEmployee() {
        Employee employee = new Employee(
            "Yaswanth",
            "yaswanth@example.com",
            "Engineering"
        );

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertEquals("Yaswanth", result.getName());
        assertEquals("yaswanth@example.com", result.getEmail());

        verify(employeeRepository).save(employee);
    }

    @Test
    void getEmployeeByIdShouldReturnEmployee() {
        Employee employee = new Employee(
            "Yaswanth",
            "yaswanth@example.com",
            "Engineering"
        );

        when(employeeRepository.findById(1L))
            .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("Yaswanth", result.getName());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeByIdShouldThrowWhenEmployeeDoesNotExist() {

        when(employeeRepository.findById(999L))
            .thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> employeeService.getEmployeeById(999L)
        );

        assertEquals(404, exception.getStatusCode().value());

        verify(employeeRepository).findById(999L);
    }
}
