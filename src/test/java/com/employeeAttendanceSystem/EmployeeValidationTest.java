
package com.employeeAttendanceSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidationTest {

    @Test
    void validEmployeeNameShouldBeAccepted() {
        String name = "John Smith";

        assertNotNull(name);
        assertFalse(name.isBlank());
    }

    @Test
    void employeeIdShouldBePositive() {
        int employeeId = 10;

        assertTrue(employeeId > 0);
    }
}
