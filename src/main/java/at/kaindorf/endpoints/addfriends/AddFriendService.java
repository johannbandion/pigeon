package at.kaindorf.endpoints.addfriends;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.UserEntity;
import at.kaindorf.persistence.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class AddFriendService {
    @Inject
    UserRepository userRepository;

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
        return Response.ok(userList).header("X-Total-Count", length).build();
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
}
