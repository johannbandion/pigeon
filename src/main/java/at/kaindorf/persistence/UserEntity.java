package at.kaindorf.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    private String name;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinColumn(name = "name")
    private List<UserEntity> contacts;


    public UserEntity(String name, String password, List<UserEntity> contacts) {
        this.name = name;
        this.password = password;
        this.contacts = contacts;
    }

    public UserEntity() {
    }

    public List<UserEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<UserEntity> contacts) {
        this.contacts = contacts;
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
}
