import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 2017-05-05.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        Date dateFirst = new Date();
        Thread.sleep(1000);
        Date dateSecond = new Date();
        System.out.println(dateFirst.before(dateSecond));
    }
}
