package WorldNavigator;

import Entity.*;
import Graphics.GUI.*;
import Input.MouseInput;
import Net.GameClient;
import Net.GameSarver;
import Net.Packets.Packet00Login;
import Net.Packets.Packet01Disconnect;
import Timer.TimerGame;
import Graphics.Sprites;
import Graphics.SpritesSheet;
import Input.KeyInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameMain extends Canvas implements Runnable{

    public static final int SCREEN_WIDTH = 260;
    public static final int SCREEN_HEIGHT =SCREEN_WIDTH/14*10;
    public static final int UNIT_SIZE = 4;
    public static final Dimension DIMENSIONS = new Dimension(SCREEN_WIDTH * UNIT_SIZE, SCREEN_HEIGHT * UNIT_SIZE);
    public static int coins=10;
    public static int keys=0;
    public static boolean win=false;
    public static boolean playing=false;
    public static boolean restarted=false;
    private boolean running=false;
    public static final String TITLE = "World Navigator";
    public static Handler handler;
    public static SpritesSheet spritesSheet;
    public static Sprites wallGraph;
    public static Sprites playerGraph;
    public static Sprites keyGraph;
    public static Sprites coinGraph;
    public static Sprites sellerGraph;
    public static Sprites targetGraph;
    public static Sprites mirrorGraph;
    public static Sprites lightGraph;
    public static Launcher launcher;
    public static InternalButton internalButton;
    public static KeyInput keyInput;
    public static TimerGame timerGame;
    public static Camera cam;
    public static BufferedImage image;
    public static BufferedImage background;
    private Player player;
    public MouseInput mouse;
    private Thread thread;
    public GameSarver sarverPocket;
    public GameClient clientpocket;
    public Packet01Disconnect diPacket;
    public static GameMain game;
    public boolean isApplet=false;
    public static final String NAME = "GameMain";
    public GameMain(){
        Dimension size=new Dimension(SCREEN_WIDTH*UNIT_SIZE,SCREEN_HEIGHT*UNIT_SIZE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }
    public void init(){
        game = this;
        handler=new Handler();
        spritesSheet= new SpritesSheet("/pic2.png");
        cam=new Camera();
        launcher=new Launcher();
        internalButton=new InternalButton();
        mouse=new MouseInput();
        addKeyListener(new  KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        wallGraph=new Sprites(spritesSheet,1,1);
        playerGraph = new Sprites(spritesSheet, 4, 4);
        keyGraph=new Sprites(spritesSheet,4,3);
        coinGraph=new Sprites(spritesSheet,5,2);
        mirrorGraph=new Sprites(spritesSheet,4,1);
        sellerGraph=new Sprites(spritesSheet,3,1);
        targetGraph=new Sprites(spritesSheet,1,3);
        lightGraph=new Sprites(spritesSheet,3,3);
        timerGame=new TimerGame();
        timerGame.getTimeLabel();
       try {
           image = ImageIO.read(getClass().getResource("/levels51.png"));
           background= ImageIO.read(getClass().getResource("/backgroundThree.jpg"));
        } catch (IOException e) {
         System.out.println(e);
           }
        handler.creatLevel(image);
        sarverPocket=new GameSarver(this);
        clientpocket=new GameClient("localhost",this);
        player = new playerMp( 400, 600,32,32,true, Id.Player,
                null, -1, JOptionPane.showInputDialog(this, "Please enter a username"));
      handler.addEntity(new Player(300,800,32,32,true,Id.Player,handler,player.getUsername()));

        System.out.println(player.getUsername());
        if (!isApplet) {
            Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.x, player.y);
            if (sarverPocket != null) {
                sarverPocket.addConnection((playerMp) player, loginPacket);
            }
            loginPacket.writeData(clientpocket);
        }
      }

    public synchronized void start(){
     if (running)return;
     running=true;
        thread = new Thread(this, NAME + "_main");
        thread.start();
        if (!isApplet) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to run the server") == 0) {
                sarverPocket = new GameSarver(this);
                sarverPocket.start();
            }

            clientpocket=new GameClient("localhost",this);
            clientpocket.start();
        }

     }

    public synchronized void stop(){
        if (!running)return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static int getFrameWidth(){
        return SCREEN_WIDTH*UNIT_SIZE;
    }

    public static int getFrameHeight(){
        return SCREEN_HEIGHT*UNIT_SIZE;
    }

    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int frames = 0;
        int ticks = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs ==null){
            createBufferStrategy(3);
            return;
        }
        Graphics graphics=bs.getDrawGraphics();
        graphics.drawImage(background,0,0,getFrameWidth(),getFrameHeight(),null);
        GameMain.playing = true;

     if (timerGame.getMinutes()<2 &&win==false ) {
         if (restarted==true){timerGame.stop1();}
         else
         {timerGame.start();}

         timerGame.timerGraphics();
         graphics.drawImage(coinGraph.getImage(),50,20,64,64,null);
         graphics.setColor(Color.white);
         graphics.setFont(new Font("Courier",Font.BOLD,25));
         graphics.drawString("x"+coins,120,90);

         graphics.drawImage(keyGraph.getImage(),50,120,64,64,null);
         graphics.setColor(Color.white);
         graphics.setFont(new Font("Courier",Font.BOLD,25));
         graphics.drawString("x"+keys,120,200);

         graphics.setColor(Color.white);
         graphics.setFont(new Font("Courier",Font.BOLD,20));
         graphics.drawString("the direction is",30,300);
         graphics.drawString(keyInput.getDirection(),30,350);
         internalButton.render(graphics);

         graphics.translate(cam.getX(),cam.getY());

      handler.render(graphics);
    }

      else if (timerGame.getMinutes()>=2&&win==false){
           timerGame.stop1();
           timerGame.getTimeLabel().setVisible(false);
           graphics.setColor(Color.RED);
           graphics.setFont(new Font("Courier",Font.BOLD,50));
           graphics.drawString("game over",400,350);
      } else if (win==true){
        timerGame.getTimeLabel().setVisible(false);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Courier",Font.BOLD,50));
        graphics.drawString("Gongraduation You win"+player.getUsername(),250,350);
        graphics.drawImage(coinGraph.getImage(),340,400,64,64,null);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Courier",Font.BOLD,35));
        graphics.drawString("x"+coins,430,460);

       }
        graphics.dispose();
        bs.show();
     }

    public void tick() {
       if (playing)handler.tick();
        for ( Entity en:handler.entities){
            if (en.getId()==Id.Player){
                cam.tick(en);
            }
        }
    }


}
