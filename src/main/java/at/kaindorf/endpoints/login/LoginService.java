package at.kaindorf.endpoints.login;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.repository.UserRepository;
import at.kaindorf.shared.sharedModel.Token;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;

import static at.kaindorf.shared.sharedService.EncryptionService.isPasswordCorrect;


@RequestScoped
public class LoginService {

    @Inject
    UserRepository userRepository;


    public Token login(UserDto userDto) {
        if (userDto.getUserName() == null ) {
            throw new BadRequestException("Username is undefined", Response.status(400).entity("Username is undefined").build());
        }
        if (userDto.getPassword() == null) {
            throw new BadRequestException("Password is undefined", Response.status(400).entity("Password is undefined").build());
        }
        userDto.setUserName(userDto.getUserName().trim());
        if (!userRepository.doesUserExist(userDto)) {
            throw new BadRequestException("User does not exist", Response.status(400).entity("User does not exist").build());
        }
        UserDto user = userRepository.getUserDtoByName(userDto.getUserName());
        throwIfPasswordIsIncorrect(user.getPassword(), userDto.getPassword());
        return getToken(userDto.getUserName());
    }

    private Token getToken(String name) {
        String jwt = Jwt.upn(name)
                .groups(new HashSet<>(Arrays.asList("user")))
                .sign();
        return new Token(jwt);
    }

    public void throwIfPasswordIsIncorrect(String encryptedPassword, String passwordFromRequest) {
        if (!isPasswordCorrect(encryptedPassword, passwordFromRequest)) {
            throw new BadRequestException("Password is incorrect", Response.status(400).entity("Password is incorrect").build());
        }
    }
}
