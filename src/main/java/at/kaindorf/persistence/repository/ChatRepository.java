package at.kaindorf.persistence.repository;

import at.kaindorf.persistence.dto.MessagesDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.MessagesEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@RequestScoped
public class ChatRepository implements PanacheRepository<ChatEntity> {

    @Inject
    UserRepository userRepository;

    @Transactional
    public Long createChat() {
//        UserEntity userDto = userRepository.getUserEntityByName(user);
//        UserEntity friendDto = userRepository.getUserEntityByName(friend);
        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setUserEntities(Set.of(userDto, friendDto));
        persist(chatEntity);
        return chatEntity.getChatId();
    }

    public ChatEntity getChatById(Long chatId) {
        return findById(Long.valueOf(chatId));
    }


    @Transactional
    public void addMessage(Long chatId, MessagesDto messagesDto) {
        ChatEntity chatEntity = getChatById(chatId);
        chatEntity.getMessagesEntities().add(new MessagesEntity(messagesDto));
        persist(chatEntity);
    }
}
