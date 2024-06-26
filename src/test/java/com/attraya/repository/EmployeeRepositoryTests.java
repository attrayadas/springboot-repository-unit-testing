package com.attraya.repository;

import com.attraya.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Attraya")
                .lastName("Das")
                .email("attraya@gmail.com")
                .build();
    }

    // Junit test for save employee operation
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        // given - precondition or setup

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
        Employee employee2 = Employee.builder()
                .firstName("Sudhakar")
                .lastName("S")
                .email("sudhakar.s@gmail.com")
                .build();
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when - action or the behavior that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // Junit test for get employee by id operation
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        // given - precondition or setup

        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit test for get employee by email operation
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        // given - precondition or setup

        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // Junit test for update employee operation
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("attrayadas@yahoo.com");
        savedEmployee.setLastName("Ghosh Das");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("attrayadas@yahoo.com");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Ghosh Das");
    }

    // Junit test for delete employee operation
    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployee(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    // Junit test for custom query using JPQL with index params
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenRemoveEmployeeObject(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL("Attraya", "Das");

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // Junit test for custom query using JPQL with named params
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenRemoveEmployeeObject(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams("Attraya", "Das");

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // Junit test for custom query using Native SQL with index params
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // Junit test for custom query using Native SQL with named params
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
        employeeRepository.save(employee);

        // when - action or the behavior that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

}
