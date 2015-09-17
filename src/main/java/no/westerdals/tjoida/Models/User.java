package no.westerdals.tjoida.Models;


/**
 * Created by Cyzla on 17.09.2015.
 */
public class User {
    private int id;
    private String email;
    private String name;
    private Enum<Type> type;

    public User(int id, String email, String name, Enum<Type> type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public User(int id, String email, String name) {
        this(id, email, name, Type.STUDENT);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum<Type> getType() {
        return type;
    }

    public void setType(Enum<Type> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
