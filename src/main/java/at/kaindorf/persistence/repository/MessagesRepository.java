package at.kaindorf.persistence.repository;

import at.kaindorf.persistence.dto.MessagesDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.MessagesEntity;
import at.kaindorf.persistence.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class MessagesRepository implements PanacheRepository<MessagesEntity> {

    @Inject
    ChatRepository chatRepository;

    @Inject
    UserRepository userRepository;

    public List<MessagesDto> getMessagesDtoByChatId(Long chatId) {
        List<MessagesDto> messagesDtos = find("chatId", chatId).list().stream().map(MessagesDto::new).toList();
        return messagesDtos;
    }

    @Transactional
    public void addMessage(Long chatId, MessagesDto messagesDto, String userName) {
        ChatEntity chatEntity = chatRepository.getChatById(chatId);

        MessagesEntity messagesEntity = new MessagesEntity(messagesDto);
        messagesEntity.setChatEntity(chatEntity);

        UserEntity userEntityByName = userRepository.getUserEntityByName(userName);
        messagesEntity.setUserEntity(userEntityByName);

        persistAndFlush(messagesEntity);
    }
}
