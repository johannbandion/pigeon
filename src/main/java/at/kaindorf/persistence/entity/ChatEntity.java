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
    private Long chatId;

    @ManyToMany(mappedBy = "chatEntities")
    Set<UserEntity> userEntities;

//    @ManyToOne
//    @JoinColumn(name = "userName", nullable = true)
//    private UserEntity userEntity;

//    @OneToMany
//    @JoinColumn(name = "messageId", nullable = true)
//    private Set<MessagesEntity> messagesEntities;

    public ChatEntity() {
    }

    public ChatEntity(ChatDto chatDto) {
        this.chatId = chatDto.getChatId();
        this.userEntities = chatDto.getUserEntities();
    }

    public ChatEntity(Long chatId) {
        this.chatId = chatId;
    }



    public ChatEntity(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Set<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(Set<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }


//    public Set<MessagesEntity> getMessagesEntities() {
//        return messagesEntities;
//    }
//
//    public void setMessagesEntities(Set<MessagesEntity> messagesEntities) {
//        this.messagesEntities = messagesEntities;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        return Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return chatId != null ? chatId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "chatId=" + chatId +
                '}';
    }
}
