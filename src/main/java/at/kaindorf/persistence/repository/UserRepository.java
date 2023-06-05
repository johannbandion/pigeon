package at.kaindorf.persistence.repository;


import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import java.util.Optional;

@RequestScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    public List<UserDto> getAllUsers(){
        return findAll(Sort.ascending("name")).stream().map(UserDto::new).collect(Collectors.toList());
    }

    public List<UserEntity> searchUsers(String searchString){
        return find("lower(name) like lower(CONCAT('%', ?1 ,'%')) order by last_name", searchString).list();
    }

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
