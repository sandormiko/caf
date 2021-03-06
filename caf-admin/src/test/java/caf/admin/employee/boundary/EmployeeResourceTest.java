package caf.admin.employee.boundary;


import caf.admin.employee.matcher.EmployeeMatcher;
import caf.admin.employees.boundary.EmployeeResource;
import caf.admin.employees.control.EmployeeBuilder;
import caf.admin.employees.control.EmployeeDAO;
import caf.admin.employees.entity.Employee;
import caf.admin.fongo.InMemoryDataSource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

import static javax.ws.rs.client.Entity.json;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeResourceTest {

    private static final String DB_NAME = "test";
    private static final InMemoryDataSource ds = new InMemoryDataSource(DB_NAME);


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new EmployeeResource(new EmployeeDAO(ds.getDataStore()))).build();



    @After
    public void after() {
        ds.dropDataBase();

    }

    @Test
    public void testGetAllEmployees() {
        Response response = allEmployeesRequest();
        assertThat(response.getStatus(), is(OK_200));
        assertThat(employeesRetrieved(response), hasSize(0));
    }

    @Test
    public void testSaveEmployee() {
        Employee emp = EmployeeBuilder.newBuilder().withBirthDate(LocalDate.now()).withFirstName("Jakab").withLastName("Gipsz").withPremiumAccess().build();
        Response response = resources.client().target("/employees").request().buildPost(json(emp)).invoke();
        assertThat(response.getStatus(), is(CREATED_201));
        response = allEmployeesRequest();
        assertThat(response.getStatus(), is(OK_200));

        List<Employee> employees = employeesRetrieved(response);
        assertThat(employees, hasSize(1));
        assertThat(employees, contains(new EmployeeMatcher()
                .hasPremiumAccess(emp.isHasPremiumAccess())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .birthDate(emp.getBirthDate())));

    }


    private Response allEmployeesRequest() {
        return resources.client().target("/employees").request().buildGet().invoke();
    }

    private List<Employee> employeesRetrieved(Response response) {
        return response.readEntity(new GenericType<List<Employee>>() {});
    }
}
