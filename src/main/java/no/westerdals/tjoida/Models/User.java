package no.westerdals.tjoida.Models;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Cyzla on 17.09.2015.
 */
@Entity
public class User {
    @Id
    @Min(value = 0, message = "ID cannot be negatice")
    private int id;

    @NotNull
    @Pattern(regexp = "^([A-Z|a-z|0-9](\\.|_){0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$")
    private String email;

    @Pattern(regexp = "^(([A-Za-z]+[^0-9]*)([0-9]+[^\\W]*)([\\W]+[\\W0-9A-Za-z]*))|(([A-Za-z]+[^\\W]*)" +
            "([\\W]+[^0-9]*)([0-9]+[\\W0-9A-Za-z]*))|(([\\W]+[^A-Za-z]*)([A-Za-z]+[^0-9]*)([0-9]+[\\W0-9A-Za-z]*))" +
            "|(([\\W]+[^0-9]*)([0-9]+[^A-Za-z]*)([A-Za-z]+[\\W0-9A-Za-z]*))|(([0-9]+[^A-Za-z]*)" +
            "([A-Za-z]+[^\\W]*)([\\W]+[\\W0-9A-Za-z]*))|(([0-9]+[^\\W]*)([\\W]+[^A-Za-z]*)([A-Za-z]+[\\W0-9A-Za-z]*))$")

    @Size(min = 6)
    private String password;
    private String type;

    public User(int id, String email, String password, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(int id, String email, String password) {
        this(id, email, password, "Student");
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}
