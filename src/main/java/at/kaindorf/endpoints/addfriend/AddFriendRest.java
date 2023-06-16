package at.kaindorf.endpoints.addfriend;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/addfriend")
@RequestScoped
public class AddFriendRest {
    @Inject
    AddFriendService friendService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/getUsers")
    @RolesAllowed({"user"})
    public Response getUsers(@QueryParam("search") String search, @QueryParam("page") Integer page, @QueryParam("size") Integer pageSize) {
        return friendService.getUsers(search, pageSize, page);
    }

    @POST
    @RolesAllowed({"user"})
    public Response addFriend(String friend) {
        Object upn = jwt.getClaim("upn".toString());
        try{
            friendService.addFriend(upn.toString(), friend);
            return Response.ok().build();
        } catch (BadRequestException e){
            throw new BadRequestException(e);
        }
    }
}
