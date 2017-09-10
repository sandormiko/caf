package caf.admin.partners.boundary;

import caf.admin.partners.control.PartnerDAO;
import caf.admin.partners.entity.Partner;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/partners")
@Produces(MediaType.APPLICATION_JSON)
public class PartnerResource {

	private final PartnerDAO partnerDAO;

	@Inject
	public PartnerResource(PartnerDAO partnerDAO) {
		this.partnerDAO = partnerDAO;

	}

	@GET
	public Response getpartners() {

		return Response.ok(partnerDAO.find().asList()).build();
	}

	@POST
	public Response savePartner(Partner partner, @Context UriInfo uriInfo) {

		Object resourceId = partnerDAO.save(partner).getId();
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(resourceId.toString());
		return Response.created(builder.build()).build();

	}


}