package no.westerdals.tjoida;

import no.westerdals.tjoida.Models.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import java.util.List;

public class Main {

    static{
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<MyService> service = container.instance().select(MyService.class);
        //service.get().printUserNames();

//        List<User> users= service.get().getUsers();
//        service.get().updateUser(users.get(users.size()-1).getId(), "name", "hi! im update");
//        users.forEach(System.out::println);
//
//        service.get().deleteUser(1);

        User first = service.get().getUser(1);
        if(first != null){
            System.out.println("First:" +  first.toString());
        } else{
            System.out.println("First is null");
        }


    }
}
