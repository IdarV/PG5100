package no.westerdals.tjoida;

import no.westerdals.tjoida.Models.User;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;
import java.util.List;

/**
 * Created by Cyzla on 17.09.2015.
 */
public class Main {

    static{
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }

    public static void main(String[] args) {
        WeldContainer container = new Weld().initialize();
        Instance<MyService> service = container.instance().select(MyService.class);
        service.get().printUserNames();

        int i = service.get().updateUser(2, "email", "newEmail@mainmethod.com");
        System.out.println("Update code: " + i);

        List<User> users= service.get().getUsers();
        users.forEach(System.out::println);


    }
}
