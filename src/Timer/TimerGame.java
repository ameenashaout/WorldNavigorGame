package Timer;

import WorldNavigator.GameMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerGame {

    public static JLabel timeLabel = new JLabel();

    public static int elapsedTime = 0;
    public static int seconds =0;
    public static int minutes =0;
    public static int hours =0;

    public static String seconds_string = String.format("%02d", seconds);
    public static String minutes_string = String.format("%02d", minutes);
    public static String hours_string = String.format("%02d", hours);

    public static Timer timer1 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime=elapsedTime+1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
           }
       });

    public static JLabel getTimeLabel() {
        return timeLabel;
    }

    public static void timerGraphics(){
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(20,370,150,70);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,25));
        timeLabel.setBackground(Color.lightGray);
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
    }

    public static int getSeconds() {
        return seconds;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static Timer getTimer1() {
        return timer1;
    }

    public static void start() {
        timer1.start();
    }

    public static void stop1() {
        timer1.stop();
    }

    public static void reset() {
        timer1.stop();
        elapsedTime=0;
        seconds =0;
        minutes=0;
        hours=0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    }

}
