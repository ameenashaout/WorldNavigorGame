package Graphics.GUI;

import Entity.Player;
import WorldNavigator.GameMain;
import WorldNavigator.Handler;
import WorldNavigator.Id;

import java.awt.*;

import static WorldNavigator.GameMain.timerGame;

public class Button {
  public int x, y;
  public int width, height;
public static boolean movement=true;
  public String label;

  public Button(int x, int y, int width, int height, String label) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.label = label;
  }

  public void render(Graphics graphics) {
    graphics.drawString(getLabel(), getX(), getY());
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public void triggerEvent() {
    if (getLabel().toLowerCase().contains("start")) {
      GameMain.playing = true;
      GameMain.restarted = false;
      timerGame.start();
      movement=true;
      setLabel("Stop game");
    } else if (getLabel().toLowerCase().contains("exit")) System.exit(0);
    else if (getLabel().toLowerCase().contains("stop")) {
        movement=false;
        GameMain.restarted=true;
        setLabel("Start game");
        timerGame.stop1();
        timerGame.getTimeLabel().setVisible(true);
    }
  }
}