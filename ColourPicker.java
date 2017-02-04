/**
 * @author Steven Sams
 * RGB Colour inspector desktop tool
 */

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class ColourPicker {

    public static void main(String args[]) {

        try {

            Robot robot = new Robot();
            Color colour;

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));          
            panel.setLayout(new BorderLayout());
            
            JLabel label = new JLabel();
            label.setHorizontalTextPosition(SwingConstants.CENTER);
            
            JWindow frame = new JWindow();
            frame.setLayout(new BorderLayout());
            frame.setPreferredSize(new Dimension(128, 128));
            frame.setAlwaysOnTop(true);
            frame.add(panel, BorderLayout.CENTER);
            frame.add(label, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

            while (true) {
                colour = robot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
                frame.setLocation(MouseInfo.getPointerInfo().getLocation().x + 10, MouseInfo.getPointerInfo().getLocation().y - 10);
                BufferedImage image=robot.createScreenCapture(new Rectangle(MouseInfo.getPointerInfo().getLocation(), new Dimension(10,10)));
                panel.removeAll();
                panel.add(new JLabel(new ImageIcon(image.getScaledInstance(panel.getWidth(), panel.getHeight(), BufferedImage.SCALE_FAST))), BorderLayout.CENTER);
                label.setText("R="+colour.getRed() + ", G=" + colour.getGreen() + ", B=" + colour.getBlue());
                panel.validate();
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ColourPicker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(ColourPicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}