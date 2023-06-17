package at.kaindorf.persistence.entity;

import at.kaindorf.persistence.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity {

    @Id
    private String userName;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "userentity_chatentity",
            joinColumns = @JoinColumn(name = "userName"),
            inverseJoinColumns = @JoinColumn(name = "chatId"))
    @JsonIgnore
    Set<ChatEntity> chatEntities;

    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.chatEntities = userDto.getChatEntities();
    }

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserEntity(String userName, String password, Set<ChatEntity> chatEntities) {
        this.userName = userName;
        this.password = password;
        this.chatEntities = chatEntities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ChatEntity> getChatEntities() {
        return chatEntities;
    }

    public void setChatEntities(Set<ChatEntity> chatEntities) {
        this.chatEntities = chatEntities;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!Objects.equals(userName, that.userName)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
