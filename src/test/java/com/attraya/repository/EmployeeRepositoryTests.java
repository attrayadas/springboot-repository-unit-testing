package com.attraya.repository;

import com.attraya.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Junit test for save employee operation
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Attraya")
                .lastName("Das")
                .email("attraya@gmail.com")
                .build();

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // Junit test for get all employee operation
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("Attraya")
                .lastName("Das")
                .email("attraya@gmail.com")
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Sudhakar")
                .lastName("S")
                .email("sudhakar.s@gmail.com")
                .build();
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        // when - action or the behavior that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }
}
