package at.kaindorf.endpoints.addfriends;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/addfriends")
@RequestScoped
public class AddFriendRest {
    @Inject
    AddFriendService friendService;

    @GET
    @Path("/getUsers")
    public Response getFriends(@QueryParam("search") String search, @QueryParam("page") Integer page, @QueryParam("size") Integer pageSize) {
        return friendService.getUsers(search, pageSize, page);
    }

    @GET
    @Path("/test")
    public Response getAllUsers() {
        return Response.ok().build();
    }
}
