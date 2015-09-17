package no.westerdals.tjoida;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Instance;

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
    }
}
