package at.kaindorf.persistence.dto;

import javax.persistence.Column;
import java.util.Objects;

public class UserDto {
    private String name;
    private String password;

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(name, userDto.name) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
