package at.kaindorf.persistence.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ChatEntity {
    @Id
    @SequenceGenerator(
            name = "chatEntitySequence",
            sequenceName = "chatEntitySequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatEntitySequence")
    private String chatId;

    @ManyToMany(mappedBy = "chatEntities")
    Set<UserEntity> userEntities;
}
