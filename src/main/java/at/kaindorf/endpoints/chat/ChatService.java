package at.kaindorf.endpoints.chat;

import at.kaindorf.persistence.dto.MessagesDto;
import at.kaindorf.persistence.repository.ChatRepository;
import at.kaindorf.persistence.repository.MessagesRepository;
import at.kaindorf.persistence.repository.UserRepository;

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
        List<MessagesDto> messagesDtoByChatId = messagesRepository.getMessagesDtoByChatId(chatId);
        for (MessagesDto messagesDto : messagesDtoByChatId) {
            messagesDto.getUserEntity().setPassword(null);
        }
        return messagesDtoByChatId;
    }

    public void sendMessage(Long chatId, MessagesDto messagesDto, String userName) {
        messagesDto.setMessageTime(LocalDateTime.now());
        messagesRepository.addMessage(chatId, messagesDto, userName);
//        chatRepository.addMessage(chatId, messagesDto, userName);
    }
}
