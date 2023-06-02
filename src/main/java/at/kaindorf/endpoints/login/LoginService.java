package at.kaindorf.endpoints.login;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.repository.UserRepository;
import at.kaindorf.shared.EncryptionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import static at.kaindorf.shared.EncryptionService.*;


@RequestScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    public void signup(UserDto userDto) {
        userDto.setPassword(encryptPassword(userDto.getPassword()));
    }

    private String encryptPassword(String password) {
        if (password == null) {
            throw new BadRequestException("User supplied has no password", Response.status(400).entity("User supplied has no password").build());
        }
        return getHashedPassword(password, getSalt());
    }


}
