package at.kaindorf.persistence.repository;

import at.kaindorf.persistence.dto.MessagesDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.MessagesEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class MessagesRepository implements PanacheRepository<MessagesEntity> {

    @Inject
    ChatRepository chatRepository;

    public List<MessagesDto> getMessagesDtoByChatId(Long chatId) {
        List<MessagesDto> messagesDtos = find("chatId", chatId).list().stream().map(MessagesDto::new).toList();
        return messagesDtos;
    }
}
