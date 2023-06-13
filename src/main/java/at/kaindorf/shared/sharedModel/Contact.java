package at.kaindorf.shared.sharedModel;

import java.util.Objects;

public class Contact {
    private Long chatId;
    private String userName;

    public Contact() {
    }

    public Contact(Long chatId, String userName) {
        this.chatId = chatId;
        this.userName = userName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!Objects.equals(chatId, contact.chatId)) return false;
        return Objects.equals(userName, contact.userName);
    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
