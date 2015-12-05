package no.westerdals.tjoida.Models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Min(value = 0, message = "ID cannot be negative")
    private int id;

    @NotNull
    @Pattern(message = "email cannot be empty", regexp = "^([A-Z|a-z|0-9](\\.|_){0,1})+[A-Z|a-z|0-9]\\@([A-Z|a-z|0-9])+((\\.){0,1}[A-Z|a-z|0-9]){2}\\.[a-z]{2,3}$")
    private String email;

    @Pattern(message = "Password must contain one character, letter and symbol", regexp = "^(([A-Za-z]+[^0-9]*)([0-9]+[^\\W]*)([\\W]+[\\W0-9A-Za-z]*))|(([A-Za-z]+[^\\W]*)" +
            "([\\W]+[^0-9]*)([0-9]+[\\W0-9A-Za-z]*))|(([\\W]+[^A-Za-z]*)([A-Za-z]+[^0-9]*)([0-9]+[\\W0-9A-Za-z]*))" +
            "|(([\\W]+[^0-9]*)([0-9]+[^A-Za-z]*)([A-Za-z]+[\\W0-9A-Za-z]*))|(([0-9]+[^A-Za-z]*)" +
            "([A-Za-z]+[^\\W]*)([\\W]+[\\W0-9A-Za-z]*))|(([0-9]+[^\\W]*)([\\W]+[^A-Za-z]*)([A-Za-z]+[\\W0-9A-Za-z]*))$")

    @Size(message = "Password must be at least 6 characters long", min = 6)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    // Removed CascadeType.MERGE 03.12.2015 10:42, because of java.lang.IllegalStateException: Multiple representations of the same entity
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Course> courses;

    public User() {
    }

    public User(int id, String email, String password, UserType type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = type;
    }

    public User(int id, String email, String password) {
        this(id, email, password, UserType.STUDENT);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + userType +
                '}';
    }
}
