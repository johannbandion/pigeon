package at.kaindorf.endpoints.toolbar;

import at.kaindorf.persistence.dto.UserDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;
import at.kaindorf.persistence.repository.UserRepository;
import at.kaindorf.shared.sharedModel.Contact;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestScoped
public class ToolbarService {

    @Inject
    UserRepository userRepository;

    public List<Contact> getContacts(String username) {
        Set<ChatEntity> chatEntities = userRepository.getUserDtoByName(username).getChatEntities();
        List<Contact> contacts = new ArrayList<>();
        for (ChatEntity chatEntity : chatEntities) {
            for (UserEntity userEntity : chatEntity.getUserEntities()) {
                if (!userEntity.getUserName().equals(username)) {
                    contacts.add(new Contact(chatEntity.getChatId(), userEntity.getUserName()));
                }
            }
        }
        return contacts;
    }
}
