package at.kaindorf.endpoints.login;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.shared.sharedModel.Token;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/login")
@RequestScoped
public class LoginRest {

    @Inject
    LoginSignupService loginSignupService;

    @Inject
    LoginService loginService;

    @POST
    public Token login(UserDto userDto) {
        return loginService.login(userDto);
    }

    @POST
    @Path("/signup")
    public Response signup(UserDto userDto) {
        loginSignupService.signup(userDto);
        return Response.ok().status(Response.Status.CREATED).build();
    }
}
