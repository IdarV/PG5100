package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import no.westerdals.tjoida.service.UserService.JPAUserDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cyzla on 29.10.2015.
 */
@Model
public class UserController {
    private JPAUserDao persister;
    private User user;

    @Inject
    public UserController(JPAUserDao persister){
        this.persister = persister;
    }

    @PostConstruct
    public void init(){
        this.user = new User();
    }

    public User getFirst(){
        return persister.getUsers().get(0);
    }

    public List<User> getAll(){
        return persister.getUsers();
    }
}
