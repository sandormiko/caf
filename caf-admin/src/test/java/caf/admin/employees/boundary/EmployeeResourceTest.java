package caf.admin.employees.boundary;


import caf.admin.employees.control.EmployeeDAO;
import caf.admin.employees.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fakemongo.Fongo;
import com.github.fakemongo.junit.FongoRule;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.eclipse.jetty.http.HttpStatus;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeResourceTest {

    private static EmployeeDAO employeeDao;


    private static EmployeeDAO setupDAO() {
        Fongo fongo = new Fongo("inMemoryServer");
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(fongo.getMongo(), "test");
        return new EmployeeDAO(ds);

    }

    @Rule
    public FongoRule fongo = new FongoRule();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new EmployeeResource(setupDAO(),null))
            .build();

    @Test
    public void getHttpGet(){
        Response response = resources.client().target("/employees").request().buildGet().invoke();
        assertThat(response.getStatus(), is(OK_200));
        List<Employee> employees = (List<Employee>)response.readEntity(List.class);
        assertThat(employees,hasSize(0));
    }

    @Test
    public void getHttpPost(){
        Response response = resources.client().target("/employees").request().buildPost(Entity.json("{\"firstName\":\"Giacomo\",\"lastName\": \"Guilizzoni\", \"birthDate\":\"1982-06-01\"}")).invoke();
                assertThat(response.getStatus(), is(CREATED_201));
        response = resources.client().target("/employees").request().buildGet().invoke();
        assertThat(response.getStatus(), is(OK_200));
        List<Employee> employees = (List<Employee>)response.readEntity(List.class);
        assertThat(employees,hasSize(1));
    }
}
