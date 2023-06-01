package at.kaindorf.persistence.dto;

import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

public class ChatDto {

    private Integer chatId;

    Set<UserEntity> userEntities;

    public ChatDto() {
    }

    public ChatDto(ChatEntity chatEntity) {
        this.chatId = chatEntity.getChatId();
        this.userEntities = chatEntity.getUserEntities();
    }
    public ChatDto(Integer chatId, Set<UserEntity> userEntities) {
        this.chatId = chatId;
        this.userEntities = userEntities;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatDto chatDto = (ChatDto) o;

        if (!Objects.equals(chatId, chatDto.chatId)) return false;
        return Objects.equals(userEntities, chatDto.userEntities);
    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (userEntities != null ? userEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "chatId='" + chatId + '\'' +
                ", userEntities=" + userEntities +
                '}';
    }
}
