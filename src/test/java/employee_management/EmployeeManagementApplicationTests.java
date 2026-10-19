
package employee_management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagementApplicationTests {

    @Test
    void employeeShouldStoreAndReturnItsDetails() {
        Employee employee = new Employee(
            "Yaswanth",
            "yaswanth@example.com",
            "Engineering"
        );

        assertEquals("Yaswanth", employee.getName());
        assertEquals("yaswanth@example.com", employee.getEmail());
        assertEquals("Engineering", employee.getDepartment());
    }
}
