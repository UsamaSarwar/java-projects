package notepad;

import javafx.animation.Timeline;

public class Login {
    int Login(String username, String password){
        if(username == null ? password == null : username.equals(password)){
            return 1;
        }else{
            return 0;
        }
    }
}
