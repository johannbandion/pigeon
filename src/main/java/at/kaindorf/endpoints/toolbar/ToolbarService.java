package at.kaindorf.endpoints.toolbar;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;
import at.kaindorf.persistence.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Set;

@RequestScoped
public class ToolbarService {

    @Inject
    UserRepository userRepository;

    public HashMap<Long, String> getContacts(String username) {
        Set<ChatEntity> chatEntities = userRepository.getUserDtoByName(username).getChatEntities();
        HashMap<Long, String> contacts = new HashMap<>();
        for (ChatEntity chatEntity : chatEntities) {
            for (UserEntity userEntity : chatEntity.getUserEntities()) {
                if (!userEntity.getUserName().equals(username)) {
                    contacts.put(chatEntity.getChatId(), userEntity.getUserName());
                }
            }
        }
        return contacts;
    }
}
