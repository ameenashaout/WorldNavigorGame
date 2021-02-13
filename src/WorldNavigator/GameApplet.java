package WorldNavigator;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class GameApplet extends Applet {
    private static GameMain game = new GameMain();
    public static final boolean DEBUG = false;

    @Override
    public void init() {
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        setMaximumSize(GameMain.DIMENSIONS);
        setMinimumSize(GameMain.DIMENSIONS);
        setPreferredSize(GameMain.DIMENSIONS);

    }
    @Override
    public void start() {
        game.start();
    }

    @Override
    public void stop() {
        game.stop();
    }
    public static void main(String[] args) {
        GameMain gameMain=new GameMain();
        JFrame frame=new JFrame(gameMain.TITLE);
        frame.add(gameMain.timerGame.getTimeLabel());
        frame.add(gameMain);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gameMain.start();
    }
}
