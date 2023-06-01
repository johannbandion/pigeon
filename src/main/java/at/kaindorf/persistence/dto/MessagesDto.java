package at.kaindorf.persistence.dto;

import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.MessagesEntity;
import at.kaindorf.persistence.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessagesDto {
    private Integer messageId;

    private ChatEntity chatEntity;

    private UserEntity userEntity;

    private LocalDateTime messageTime;

    private String messageText;

    public MessagesDto() {
    }

    public MessagesDto(MessagesEntity messagesEntity) {
        this.messageId = messagesEntity.getMessageId();
        this.chatEntity = messagesEntity.getChatEntity();
        this.userEntity = messagesEntity.getUserEntity();
        this.messageTime = messagesEntity.getMessageTime();
        this.messageText = messagesEntity.getMessageText();
    }

    public MessagesDto(Integer messageId, ChatEntity chatEntity, UserEntity userEntity, LocalDateTime messageTime, String messageText) {
        this.messageId = messageId;
        this.chatEntity = chatEntity;
        this.userEntity = userEntity;
        this.messageTime = messageTime;
        this.messageText = messageText;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public ChatEntity getChatEntity() {
        return chatEntity;
    }

    public void setChatEntity(ChatEntity chatEntity) {
        this.chatEntity = chatEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesDto that = (MessagesDto) o;

        if (!Objects.equals(messageId, that.messageId)) return false;
        if (!Objects.equals(chatEntity, that.chatEntity)) return false;
        if (!Objects.equals(userEntity, that.userEntity)) return false;
        if (!Objects.equals(messageTime, that.messageTime)) return false;
        return Objects.equals(messageText, that.messageText);
    }

    @Override
    public int hashCode() {
        int result = messageId != null ? messageId.hashCode() : 0;
        result = 31 * result + (chatEntity != null ? chatEntity.hashCode() : 0);
        result = 31 * result + (userEntity != null ? userEntity.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + (messageText != null ? messageText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessagesDto{" +
                "messageId='" + messageId + '\'' +
                ", chatEntity=" + chatEntity +
                ", userEntity=" + userEntity +
                ", messageTime=" + messageTime +
                ", messageText='" + messageText + '\'' +
                '}';
    }
}
