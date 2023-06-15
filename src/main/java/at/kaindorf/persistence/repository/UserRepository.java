package at.kaindorf.persistence.repository;


import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@RequestScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    @Inject
    ChatRepository chatRepository;

    public List<UserDto> getAllUsers(){
        return findAll(Sort.ascending("username")).stream().map(UserDto::new).collect(Collectors.toList());
    }

    public List<UserEntity> searchUsers(String searchString){
        return find("lower(username) like lower(CONCAT('%', ?1 ,'%')) order by username", searchString).list();
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

    public boolean doesUserExistByString(String friend) {
        return getUserByName(friend).isPresent();
    }

    public UserEntity getUserEntityByName(String friend) {
        Optional<UserEntity> userEntity = getUserByName(friend);
        if(userEntity.isPresent()) {
            return userEntity.get();
        }
        throw new RuntimeException("User does not exist");
    }

    @Transactional
    public void addChat(Long chatId, String user) {
        UserEntity userEntityByName = getUserEntityByName(user);
        ChatEntity chat = chatRepository.getChatById(chatId);
        userEntityByName.getChatEntities().add(chat);
        flush();
    }
}
