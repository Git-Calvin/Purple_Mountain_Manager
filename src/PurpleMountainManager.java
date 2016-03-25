/**
 * PurpleMountainManger.java
 *
 * Created by Calvin Wong on 10/11/14.
 *
 * Purple Mountain Manager
 */
import java.awt.*;

public class PurpleMountainManager {

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Controller().setVisible(true);
            }
        });
    }
} // end of class
