package caf.admin.employees.boundary;


import caf.admin.employee.matcher.EmployeeMatcher;
import caf.admin.employees.control.EmployeeBuilder;
import caf.admin.employees.control.EmployeeDAO;
import caf.admin.employees.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fakemongo.Fongo;
import com.github.fakemongo.junit.FongoRule;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.util.Lists;
import org.eclipse.jetty.http.HttpStatus;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.client.Entity.json;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeResourceTest {

    private static Fongo fongo = null;

    private static EmployeeDAO setupDAO() {
        fongo = new Fongo("inMemoryServer");
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(fongo.getMongo(), "test");
        return new EmployeeDAO(ds);

    }


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new EmployeeResource(setupDAO())).build();

    @After
    public void after() {
        fongo.dropDatabase("test");

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
        return response.readEntity(new GenericType<List<Employee>>() {
        });
    }
}
