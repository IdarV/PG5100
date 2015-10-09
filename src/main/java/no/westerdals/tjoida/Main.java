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
        MyService myService = service.get();

        List<User> users = myService.getUsers();
        users.forEach(System.out::println);

        User user = users.get(0);
        user.setEmail("hehehe@ehhhehe.com");

        myService.updateUser(users.get(0));

        users = myService.getUsers();
        users.forEach(System.out::println);

        myService.close();
    }
}
