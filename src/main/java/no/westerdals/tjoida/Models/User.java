package no.westerdals.tjoida.Models;


/**
 * Created by Cyzla on 17.09.2015.
 */
public class User {
    private int id;
    private String email;
    private String password;
    private Enum<Type> type;

    public User(int id, String email, String password, Enum<Type> type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public User(int id, String email, String password) {
        this(id, email, password, Type.STUDENT);
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

    public Enum<Type> getType() {
        return type;
    }

    public void setType(Enum<Type> type) {
        this.type = type;
    }
}
