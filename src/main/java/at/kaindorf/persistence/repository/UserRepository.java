package at.kaindorf.persistence.repository;


import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@RequestScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    @Transactional
    public void addUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto);
        persist(userEntity);
    }

    public boolean doesUserExist (UserDto userDto) {
        return getUserByName(userDto.getUserName()).isPresent();
    }

    private Optional<UserEntity> getUserByName(String name) {
        return find("userName", name).firstResultOptional();
    }

    public UserDto getUserDtoByName(String name) {
        Optional<UserEntity> userEntity = getUserByName(name);
        if(userEntity.isPresent()) {
            return new UserDto(userEntity.get());
        }
        throw new RuntimeException("User does not exist");
    }
}
