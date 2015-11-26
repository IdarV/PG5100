package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseJPA;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class CourseController {
    private CourseJPA persister;
    private Course course;
    private int selectedID;

    public CourseController() {
    }

    @Inject
    public CourseController(CourseJPA persister) {
        this.persister = persister;
    }

    @PostConstruct
    public void init() {
        this.course = new Course();
    }

    public void initCourse() {
        this.course = persister.getCourse(selectedID);
    }

    public void persistNewCourse() {
        persister.persist(course);
    }

    public CourseJPA getPersister() {
        return persister;
    }

    public void setPersister(CourseJPA persister) {
        this.persister = persister;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getFirst() {
        return persister.getCourses().get(0);
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public List<Course> getAll() {
        return persister.getCourses();
    }

    public int getUserSize(){
       return course.getUserSize();
//        StringBuilder stringBuilder = new StringBuilder();
//        for(User s : course.getUsers()){
//            stringBuilder.append(s.getEmail());
//        }
//        return stringBuilder.toString();
//        String s = course.getUsers().stream().map(i->i.getEmail()).
//        return course.getUsers().stream().forEach(stringBuilder.(User::getEmail));
    }
}
