package at.kaindorf.endpoints.addfriend;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.UserEntity;
import at.kaindorf.persistence.repository.ChatRepository;
import at.kaindorf.persistence.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class AddFriendService {
    @Inject
    UserRepository userRepository;

    @Inject
    ChatRepository chatRepository;

    public Response getUsers(String search, Integer pageSize, Integer page){
        List<UserEntity> userList = searchUsers(search, pageSize, page);
        if (userList == null ||userList.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No users were found for the given search string.")
                    .header("X-Total-Count", 0)
                    .build();
        }
        int length = userList.size();
        userList = getPage(userList, pageSize, page);
        if (userList == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Total-Count", 0)
                    .entity("Page does not exist").build();
        }
        List<String> users = new LinkedList<>();
        for (UserEntity user : userList){
            users.add(user.getUserName());
        }
        return Response.ok(users).header("X-Total-Count", length).build();
    }

    public List<UserEntity> searchUsers(String search, Integer pageSize, Integer page) {
        List<UserEntity> users;
        if (search == null || search.isEmpty()) {
            users = userRepository
                    .getAllUsers()
                    .stream().map(UserEntity::new)
                    .toList();
        } else {
            users = userRepository
                    .searchUsers(search)
                    .stream().map(UserDto::new)
                    .map(UserEntity::new)
                    .toList();
        }
        return users;
    }

    private List<UserEntity> getPage(List<UserEntity> users, Integer pageSize, Integer page) {
        if (pageSize != null && page != null) {
            int start = (page - 1) * pageSize;
            int end = start + pageSize;
            if (start < users.size()) {
                if (end > users.size()) {
                    end = users.size();
                }
                return users.subList(start, end);
            } else {
                return null;
            }
        } else {
            return users;
        }
    }

    public void addFriend(String user, String friend) {
        evaluateRequest(user, friend);
        userRepository
                .getUserDtoByName(user)
                .getChatEntities()
                .stream()
                .filter(chatEntity ->
                        chatEntity
                                .getUserEntities()
                                .stream()
                                .anyMatch(userEntity -> userEntity.getUserName().equals(friend)))
                .findFirst().ifPresent(chatEntity -> {
                    throw new BadRequestException("The user you tried to add is already in your contacts", Response.status(Response.Status.BAD_REQUEST).entity("The user you tried to add is already in your contacts").build());
                });
        Long chatId = chatRepository.createChat();
        userRepository.addChat(chatId, user);
        userRepository.addChat(chatId, friend);
    }

    private void evaluateRequest(String user, String friend) {
        if (user.equals(friend)) {
            throw new BadRequestException("You cannot add yourself as a friend.", Response.status(Response.Status.BAD_REQUEST).entity("You cannot add yourself as a friend.").build());
        }
        if (friend.isEmpty()) {
            throw new BadRequestException("You cannot add an empty friend.", Response.status(Response.Status.BAD_REQUEST).entity("You cannot add an empty friend.").build());
        }
        if (!userRepository.doesUserExistByString(friend)) {
            throw new BadRequestException("The user you want to add as a friend does not exist.", Response.status(Response.Status.BAD_REQUEST).entity("The user you want to add as a friend does not exist.").build());
        }
    }
}
