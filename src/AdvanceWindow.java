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


public class AdvanceWindow extends JFrame implements Runnable, KeyListener, MouseMotionListener
{
    private Thread thread;
    ArrayList<DrawableComponent> components = new ArrayList<>();
    boolean isRunning = false;
    int x = 0 ,y = 0;
    public AdvanceWindow() throws HeadlessException
    {
        setSize(600,600);
        setTitle("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        setResizable(false);
        addKeyListener(this);
        addMouseMotionListener(this);


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
        g.setColor(Color.WHITE);
        g.translate(getWidth() - getContentPane().getWidth(),getHeight() - getContentPane().getHeight() );
        g.fillRect(0,0,getContentPane().getWidth(),getContentPane().getHeight());

        for(DrawableComponent component : components)
            component.draw(g);
        bs.show();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        AdvanceWindow game = new AdvanceWindow();
        game.start();
        //game.addDrawableComponent(grid);
        // write your code here
    }

    private void addDrawableComponent(DrawableComponent comp)
    {
        comp.setParent(getContentPane());
        components.add(comp);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                x++;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_DOWN:
                y++;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }
}