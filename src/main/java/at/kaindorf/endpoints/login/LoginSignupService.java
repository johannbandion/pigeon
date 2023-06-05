package at.kaindorf.endpoints.login;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import static at.kaindorf.shared.sharedService.EncryptionService.*;


@RequestScoped
public class LoginSignupService {

    @Inject
    UserRepository userRepository;

    public void signup(UserDto userDto) {
        userDto.setPassword(encryptPassword(userDto.getPassword()));
        userDto.setUserName(userDto.getUserName().trim());
        userDto.setChatEntities(null);
        if(userRepository.doesUserExist(userDto)) {
            throw new BadRequestException("User does already exist", Response.status(400).entity("User does already exist").build());
        }
        userRepository.addUser(userDto);
    }

    private String encryptPassword(String password) {
        if (password == null) {
            throw new BadRequestException("User supplied has no password", Response.status(400).entity("User supplied has no password").build());
        }
        return getHashedPassword(password, getSalt());
    }


}
