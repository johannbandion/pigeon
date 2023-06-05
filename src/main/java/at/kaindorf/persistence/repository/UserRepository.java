package at.kaindorf.persistence.repository;


import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    public List<UserDto> getAllUsers(){
        return findAll(Sort.ascending("name")).stream().map(UserDto::new).collect(Collectors.toList());
    }

    public List<UserEntity> searchUsers(String searchString){
        return find("lower(name) like lower(CONCAT('%', ?1 ,'%')) order by last_name", searchString).list();
    }

}
