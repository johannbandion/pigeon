package at.kaindorf.endpoints.chat;


import at.kaindorf.persistence.dto.MessagesDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("chat")
@RequestScoped
public class ChatRest {
    @Inject
    ChatService chatService;

    @GET
    @Path("/{chatId}")
    public List<MessagesDto> getChat(@PathParam("chatId") Long chatId) {
        return chatService.getChat(chatId);
    }

    @POST
    @Path("/{chatId}")
    public void sendMessge(@PathParam("chatId") Long chatId, MessagesDto messagesDto) {
        chatService.sendMessage(chatId, messagesDto);
    }
}
