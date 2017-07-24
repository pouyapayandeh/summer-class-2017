/**
 * Created by pouya on 7/24/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Window extends JFrame implements Runnable
{
    private Thread thread;
    ArrayList<DrawableComponent> components = new ArrayList<>();
    boolean isRunning = false;
    int x = 0 ,y = 0;
    public Window() throws HeadlessException
    {
        setSize(600,600);
        setTitle("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public void start()
    {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            render();
            try
            {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stop()
    {
        isRunning = false;
        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        clearScreen(g);

        bs.show();
    }

    private void clearScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.translate(getWidth() - getContentPane().getWidth(),getHeight() - getContentPane().getHeight() );
        g.fillRect(0,0,getContentPane().getWidth(),getContentPane().getHeight());
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Window game = new Window();
        game.start();
        //game.addDrawableComponent(grid);
        // write your code here
    }

}