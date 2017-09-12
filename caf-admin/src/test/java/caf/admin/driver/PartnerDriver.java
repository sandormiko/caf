package caf.admin.driver;

import caf.admin.partner.matcher.AddressMatcher;
import caf.admin.partner.matcher.PartnerMatcher;
import caf.admin.partners.entity.Address;
import caf.admin.partners.entity.Partner;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import static javax.ws.rs.client.Entity.json;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PartnerDriver {

    private ResourceTestRule resource;
    private List<Partner> allPartners;
    private int httpStatus;
    private Partner selectedPartner;
    private Set<Address> addressesOfSelectedPartner;

    public PartnerDriver(ResourceTestRule resourceRule){
        this.resource = resourceRule;
    }

    public PartnerDriver listAllPartners(){
        Response response = resource.client().target("/partners").request().buildGet().invoke();
        allPartners = partnersRetrieved(response);
        httpStatus = response.getStatus();
        return this;
    }

    public PartnerDriver savePartner(Partner partner){
        Response response = resource.client().target("/partners").request().buildPost(json(partner)).invoke();
        httpStatus = response.getStatus();
        return this;
    }

    public PartnerDriver nrOfPartnersIs(int nrOfPartners){
        assertThat(allPartners.size(), equalTo(nrOfPartners));
        return this;
    }

    public PartnerDriver httpStatusIs(int httpStatus){
        assertThat(this.httpStatus, is(httpStatus));
        return this;
    }

    public PartnerDriver containsPartner(PartnerMatcher ...matchers){
        assertThat(allPartners, contains(matchers));
        return this;
    }


    public PartnerDriver partnerAddressesContain(AddressMatcher ...matchers){
        assertThat(addressesOfSelectedPartner, contains(matchers));
        return this;
    }

    public PartnerDriver nrOfPartnerAddressesIs(int nrOfAddresses){
        addressesOfSelectedPartner = selectedPartner.getAdresses();
        assertThat(addressesOfSelectedPartner.size(),equalTo(nrOfAddresses));

        return this;
    }

    public PartnerDriver selectPartner(String partnerName){
        selectedPartner = allPartners.stream().filter(p -> StringUtils.equals(p.getName(),partnerName)).findAny().get();
        return this;
    }

    private List<Partner> partnersRetrieved(Response response) {
        return response.readEntity(new GenericType<List<Partner>>() {
        });
    }
}
