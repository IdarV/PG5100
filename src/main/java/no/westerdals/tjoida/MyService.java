package no.westerdals.tjoida;

import no.westerdals.tjoida.service.UserDAO;

import javax.inject.Inject;

/**
 * Created by Cyzla on 17.09.2015.
 */
public class MyService {

    @Inject
    private UserDAO userDAO;

    public void printUserNames(){
        System.out.println(userDAO.names());
    }
}
