//package at.kaindorf.endpoints.login;
//
//import at.kaindorf.persistence.dto.UserDto;
//import at.kaindorf.persistence.repository.UserRepository;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.BadRequestException;
//import javax.ws.rs.core.Response;
//
//@RequestScoped
//public class LoginService {
//
//    @Inject
//    UserRepository userRepository;
//
////    public void signup(UserDto userDto) {
////        encryptPassword(userDto);
////    }
//
////    private UserDto encryptPassword(UserDto userDto) {
////        if (userDto.getPassword() == null) {
////            throw new BadRequestException("User supplied has no password", Response.status(400).entity("User supplied has no password").build());
////        }
////        getHashedPassword(userDto, getSalt());
////    }
//
//
//}
