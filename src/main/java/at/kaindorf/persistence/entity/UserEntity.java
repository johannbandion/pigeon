package at.kaindorf.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    private String userName;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "userentity_chatentity",
            joinColumns = @JoinColumn(name = "userName"),
            inverseJoinColumns = @JoinColumn(name = "chatId"))
    Set<ChatEntity> chatEntities;

    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserEntity() {
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
}
