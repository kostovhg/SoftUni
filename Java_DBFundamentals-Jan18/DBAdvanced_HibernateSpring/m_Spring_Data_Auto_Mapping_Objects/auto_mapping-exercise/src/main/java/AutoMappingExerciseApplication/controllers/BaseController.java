package AutoMappingExerciseApplication.controllers;

import java.util.Deque;
import java.util.List;

public abstract class BaseController {

    protected static UserSession userSession;

    protected BaseController(){
        if(userSession == null){
            userSession = new UserSession();
        }
    }

}
