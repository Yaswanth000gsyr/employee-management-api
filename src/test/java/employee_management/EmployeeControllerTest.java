
package employee_management;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void createEmployeeShouldReturn201() throws Exception {
        Employee employee = new Employee(
            "Yaswanth",
            "yaswanth@example.com",
            "Engineering"
        );

        when(employeeService.createEmployee(any(Employee.class)))
            .thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Yaswanth",
                      "email": "yaswanth@example.com",
                      "department": "Engineering"
                    }
                    """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Yaswanth"))
            .andExpect(jsonPath("$.email").value("yaswanth@example.com"));
    }

    @Test
    void createEmployeeShouldRejectInvalidEmail() throws Exception {
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Yaswanth",
                      "email": "invalid-email",
                      "department": "Engineering"
                    }
                    """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.email")
                .value("Please provide a valid email"));
    }
}
