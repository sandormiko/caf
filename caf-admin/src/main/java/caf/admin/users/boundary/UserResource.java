package caf.admin.users.boundary;

import caf.admin.users.control.UserDAO;
import caf.admin.users.entity.User;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDAO userDAO;

    @Inject
    public UserResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @POST
    public Response saveUser(User user, @Context UriInfo uriInfo) {

        Object resourceId = userDAO.save(user).getId();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(resourceId.toString());
        return Response.created(builder.build()).build();

    }
}
