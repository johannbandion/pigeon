package at.kaindorf.persistence.entity;


import at.kaindorf.persistence.dto.ChatDto;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class ChatEntity {
    @Id
    @SequenceGenerator(
            name = "chatEntitySequence",
            sequenceName = "chatEntitySequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatEntitySequence")
    private Integer chatId;

    @ManyToMany(mappedBy = "chatEntities")
    Set<UserEntity> userEntities;

    public ChatEntity() {
    }

    public ChatEntity(ChatDto chatDto) {
        this.chatId = chatDto.getChatId();
        this.userEntities = chatDto.getUserEntities();
    }

    public ChatEntity(Integer chatId, Set<UserEntity> userEntities) {
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

        ChatEntity that = (ChatEntity) o;

        if (!Objects.equals(chatId, that.chatId)) return false;
        return Objects.equals(userEntities, that.userEntities);
    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (userEntities != null ? userEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "chatId='" + chatId + '\'' +
                ", userEntities=" + userEntities +
                '}';
    }
}