package at.kaindorf.persistence.repository;

import at.kaindorf.persistence.entity.ChatEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


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
}
