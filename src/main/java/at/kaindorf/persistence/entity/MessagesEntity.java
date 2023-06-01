package at.kaindorf.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class MessagesEntity {
    @Id
    @SequenceGenerator(
            name = "packageGroupSequence",
            sequenceName = "package_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packageGroupSequence")
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "chatId", nullable = true)
    private ChatEntity chatEntity;

    @ManyToOne
    @JoinColumn(name = "userName", nullable = true)
    private UserEntity userEntity;

    private LocalDateTime messageTime;

    private String messageText;

    public MessagesEntity() {
    }


    public MessagesEntity(Integer messageId, ChatEntity chatEntity, UserEntity userEntity, String messageText) {
        this.messageId = messageId;
        this.chatEntity = chatEntity;
        this.userEntity = userEntity;
        this.messageTime = LocalDateTime.now();
        this.messageText = messageText;
    }

    public MessagesEntity(Integer messageId, ChatEntity chatEntity, UserEntity userEntity, LocalDateTime messageTime, String messageText) {
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
}
