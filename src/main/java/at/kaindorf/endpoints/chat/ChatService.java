package at.kaindorf.endpoints.chat;

import at.kaindorf.persistence.dto.MessagesDto;
import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.repository.ChatRepository;
import at.kaindorf.persistence.repository.MessagesRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@RequestScoped
public class ChatService {

    @Inject
    MessagesRepository messagesRepository;

    @Inject
    ChatRepository chatRepository;

    public List<MessagesDto> getChat(Long chatId) {
        return messagesRepository.getMessagesDtoByChatId(chatId);
    }

    public void sendMessage(Long chatId, MessagesDto messagesDto) {
        messagesDto.setMessageTime(LocalDateTime.now());

        chatRepository.addMessage(chatId, messagesDto);
    }
}
