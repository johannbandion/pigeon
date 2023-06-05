package at.kaindorf.persistence.dto;

import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;

import java.util.Objects;
import java.util.Set;

public class UserDto {
    private String userName;
    private String password;
    Set<ChatEntity> chatEntities;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.userName = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.chatEntities = userEntity.getChatEntities();
    }

    public UserDto(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public UserDto(String name, String password, Set<ChatEntity> chatEntities) {
        this.userName = name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (!Objects.equals(userName, userDto.userName)) return false;
        if (!Objects.equals(password, userDto.password)) return false;
        return Objects.equals(chatEntities, userDto.chatEntities);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (chatEntities != null ? chatEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", chatEntities=" + chatEntities +
                '}';
    }


}
