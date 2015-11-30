package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class UserController{
    private UserDAO persister;
    private User user;
    private int selectedID = 103;
    private String lastPassword;

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    private List<Integer> integerList = new ArrayList<>();
    private List<Integer> availableCourses = new ArrayList<>();
    private List<Integer> selectedCourses = new ArrayList<>();

    private String testString;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        System.out.println("Setting textstring/" + testString + "/");
        this.testString = testString;
    }

    @Inject
    public UserController(UserDAO persister) {
        this.persister = persister;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public void initUser() {
        System.out.println("initUser()");
        user = persister.getUser(selectedID);
        lastPassword = user.getPassword();
    }

    public User getFirst() {
        return persister.getUsers().get(0);
    }

    public List<User> getAll() {
        return persister.getUsers();
    }

    public User getUser() {
        return user;
    }

    public List<Course> getUserCourses() {
        System.out.println("User courses: " + persister.getUser(selectedID).getCourses());
        return user.getCourses();
    }

    public List<Integer> getUserCoursesAsIntegers(){
        System.out.println("getUserCourseAsIntegers()");
        System.out.println("User is " + (user == null ? "null" : "not null"));
        System.out.println("User has " + user.getCourses().size() + " courses.");
        for(Course c : user.getCourses()){
            System.out.println(c);
            if(c != null && c.getId() > 1){

                integerList.add(c.getId());
            }
        }
        return integerList;
    }

    public void setCurrentUserToSelectedId() {
        user = persister.getUser(selectedID);
    }

    public String persistNewUser() {
        persister.persist(user);
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateExistingUser(int id) {
        System.out.println("User in updateUser = " + user);
        User updateUser = persister.getUser(id);
        System.out.println("updateuser in updateUser = " + user);

        updateUser.setType(user.getType());
        updateUser.setEmail(user.getEmail());
        if (user.getPassword() != null) {

            updateUser.setPassword(user.getPassword());
        }
        System.out.println("Before: " + user.toString());
        System.out.println("After: " + updateUser.toString());
        persister.update(updateUser);

            return "/user/user-index.xhtml?faces-redirect=true";
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public String removeUserFromCourse(int courseID){
//        System.out.println("removeUserFromCourse, selectedID: " + selectedID);
//        System.out.print("selectedItems (size = " + selectedCourses.size() + ") : ");
//        selectedCourses.stream().forEach(System.out::print);
//        System.out.println();
        System.out.println("REMOVEUSERFROMCOURSE withd SelectedID=" + selectedID + " and param = " + courseID) ;
        return "/user/user-index.xhtml?faces-redirect=true";
    }

    public List<Integer> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Integer> selectedCourses) {
        System.out.println("-- SETSELECTEDCOURSES");
        System.out.println(selectedCourses == null ? "SelectedCourses is NULL" : "SelectedCourses is not NULL" );
        //selectedCourses.forEach(System.out::println);
        System.out.println("--");
        this.selectedCourses = selectedCourses;
    }

    public List<Integer> getAvailableCourses() {
        return availableCourses;
    }
}
