package at.kaindorf.endpoints.toolbar;


import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.HashSet;

@RequestScoped
@Path("/toolbar")
public class ToolbarRest {

    @Inject
    ToolbarService toolbarService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/contacts")
    @RolesAllowed({"user"})
    public HashMap<Long, String> getContacts() {
        Object upn = jwt.getClaim("upn");
        return toolbarService.getContacts(upn.toString());
    }
}
