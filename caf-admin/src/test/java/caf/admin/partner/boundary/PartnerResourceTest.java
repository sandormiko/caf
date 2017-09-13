package caf.admin.partner.boundary;


import caf.admin.driver.PartnerDriver;
import caf.admin.partners.boundary.PartnerResource;
import caf.admin.partners.control.AdressBuilder;
import caf.admin.partners.control.PartnerBuilder;
import caf.admin.partners.control.PartnerDAO;
import caf.admin.partners.entity.Address;
import caf.admin.partners.entity.Partner;
import caf.admin.fongo.InMemoryDataSource;
import caf.admin.users.boundary.UserResource;
import caf.admin.users.control.UserDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.util.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Set;

import static caf.admin.partner.matcher.AddressMatcher.newAddressMatcher;
import static caf.admin.partner.matcher.PartnerMatcher.newPartnerMatcher;
import static org.eclipse.jetty.http.HttpStatus.CREATED_201;
import static org.eclipse.jetty.http.HttpStatus.OK_200;

public class PartnerResourceTest {

    private static final String DB_NAME = "test";
    private static final InMemoryDataSource ds = new InMemoryDataSource(DB_NAME);

    private PartnerDriver driver;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PartnerResource(new PartnerDAO(ds.getDataStore())))
            .addResource(new UserResource(new UserDAO(ds.getDataStore()))).build();

    @After
    public void tearDown() {
        ds.dropDataBase();
    }

    @Before
    public void setup() {
        driver = new PartnerDriver(resources);
    }


    @Test
    public void testSaveAndListPartners() {
        Set<Address> adresses = Sets.newHashSet();
        Address partnerAddress = AdressBuilder.newBuilder()
                .withCity("Test city")
                .withPostCode("H-1111")
                .withStreet("Matthias King")
                .withCountry("Hungary").build();
        adresses.add(partnerAddress);

        Partner partner = PartnerBuilder.newBuilder()
                .withName("New partner")
                .withAccount("nPartner")
                .withAdresses(adresses).build();

        driver.listAllPartners()
                .httpStatusIs(OK_200)
                .nrOfPartnersIs(0)
                .savePartner(partner)
                .httpStatusIs(CREATED_201)
                .listAllPartners()
                .httpStatusIs(OK_200)
                .nrOfPartnersIs(1);

        driver.containsPartner(newPartnerMatcher()
                .account(partner.getAccount())
                .name(partner.getName()))
                .selectPartner(partner.getName())
                .nrOfPartnerAddressesIs(1)
                .partnerAddressesContain(newAddressMatcher()
                        .city(partnerAddress.getCity())
                        .country(partnerAddress.getCountry())
                        .postCode(partnerAddress.getPostCode())
                        .street(partnerAddress.getStreet()));

    }

}
