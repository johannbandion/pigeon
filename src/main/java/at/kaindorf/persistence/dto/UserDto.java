package at.kaindorf.persistence.dto;

import at.kaindorf.persistence.entity.ChatEntity;
import at.kaindorf.persistence.entity.UserEntity;

import javax.persistence.Column;
import java.util.Objects;
import java.util.Set;

public class UserDto {
    private String name;
    private String password;
    Set<ChatEntity> chatEntities;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.name = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.chatEntities = userEntity.getChatEntities();
    }

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDto(String name, String password, Set<ChatEntity> chatEntities) {
        this.name = name;
        this.password = password;
        this.chatEntities = chatEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        if (!Objects.equals(name, userDto.name)) return false;
        if (!Objects.equals(password, userDto.password)) return false;
        return Objects.equals(chatEntities, userDto.chatEntities);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (chatEntities != null ? chatEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", chatEntities=" + chatEntities +
                '}';
    }


}
