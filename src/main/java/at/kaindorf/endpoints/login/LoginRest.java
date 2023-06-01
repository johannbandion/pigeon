//package at.kaindorf.endpoints.login;
//
//import at.kaindorf.persistence.dto.UserDto;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Response;
//
//@Path("/login")
//@RequestScoped
//public class LoginRest {
//
//    @Inject
//    LoginService loginService;
//
//    @POST
//    public Response login(UserDto userDto) {
//
//        return Response.ok().status(Response.Status.CREATED).build();
//    }
//
//    @POST
//    @Path("/signup")
//    public Response signup(UserDto userDto) {
//        loginService.signup(userDto);
//        return Response.ok().status(Response.Status.CREATED).build();
//    }
//}
