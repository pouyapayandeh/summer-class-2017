/**
 * Created by pouya on 7/24/17.
 */
import javax.swing.*;
import java.awt.*;

/**
 * Created by Pouya Payandeh on 7/31/2016.
 */
public interface DrawableComponent
{
    void setParent(Container container);
    void draw(Graphics graphics);
}