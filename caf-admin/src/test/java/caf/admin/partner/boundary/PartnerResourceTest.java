package caf.admin.partner.boundary;


import caf.admin.employee.matcher.EmployeeMatcher;
import caf.admin.employees.boundary.EmployeeResource;
import caf.admin.employees.control.EmployeeBuilder;
import caf.admin.employees.control.EmployeeDAO;
import caf.admin.employees.entity.Employee;
import caf.admin.partners.boundary.PartnerResource;
import caf.admin.partners.control.AdressBuilder;
import caf.admin.partners.control.PartnerBuilder;
import caf.admin.partners.control.PartnerDAO;
import caf.admin.partners.entity.Adress;
import caf.admin.partners.entity.Partner;
import com.github.fakemongo.Fongo;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static caf.admin.employees.control.EmployeeBuilder.newBuilder;
import static javax.ws.rs.client.Entity.json;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PartnerResourceTest {

    private static Fongo fongo = null;


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PartnerResource(setupDAO())).build();

    private static PartnerDAO setupDAO() {
        fongo = new Fongo("inMemoryServer");
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(fongo.getMongo(), "test");
        return new PartnerDAO(ds);

    }

    @After
    public void after() {
        fongo.dropDatabase("test");

    }

    @Test
    public void testSavePartner() {
        Set<Adress> adresses = Sets.newHashSet();
        adresses.add(AdressBuilder.newBuilder()
                .withCity("Test city")
                .withPostCode("H-1111")
                .withStreet("Matthias King")
                .withCountry("Hungary").build());

        Partner partner = PartnerBuilder.newBuilder()

                .withName("New partner")
                .withAccount("nPartner")
                .withAdresses(adresses).build();

        Response response = resources.client().target("/partners").request().buildPost(json(partner)).invoke();
        assertThat(response.getStatus(), is(CREATED_201));
        response = allPartnersRequest();
        assertThat(response.getStatus(), is(OK_200));

        List<Partner> employees = partnersRetrieved(response);
        assertThat(employees, hasSize(1));
        assertThat(employees.iterator().next().getAdresses(), hasSize(1));
    }

    private Response allPartnersRequest() {
        return resources.client().target("/partners").request().buildGet().invoke();
    }

    private List<Partner> partnersRetrieved(Response response) {
        return response.readEntity(new GenericType<List<Partner>>() {
        });
    }


}
