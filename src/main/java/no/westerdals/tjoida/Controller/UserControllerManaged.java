package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copy of
 * http://stackoverflow.com/questions/3180400/recommended-jsf-2-0-crud-frameworks
 */

@ManagedBean
@ViewScoped
public class UserControllerManaged implements Serializable{
    private List<User> list;
    private User user = new User();
    private boolean edit;

    @PostConstruct
    public void init(){
        list = new ArrayList<>();
        list.add(new User(400, "user01@user.com", "JpA132hui!1", "student"));
        list.add(new User(401, "user02@user.com", "JpA132hui!2", "student"));
        list.add(new User(402, "user03@user.com", "JpA132hui!3", "student"));
        list.add(new User(403, "user04@user.com", "JpA132hui!4", "student"));
        list.add(new User(404, "user05@user.com", "JpA132hui!5", "student"));
    }

    public void add(){
        user.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(user);
        user = new User();
    }

    public void edit(User user){
        this.user = user;
        edit = true;
    }

    public void save(){
        user = new User();
        edit = false;
    }

    public void delete(User user){
        list.remove(user);
    }

    public List<User> getList(){
        return list;
    }

    public User getUser(){
        return user;
    }

    public boolean isEdit(){
        return edit;
    }

}
