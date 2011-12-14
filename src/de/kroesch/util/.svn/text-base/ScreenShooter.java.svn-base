package de.kroesch.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;

import com.pluralis.plucker.gui.Application;
import com.pluralis.plucker.gui.Components;
import com.pluralis.plucker.gui.NavigationPanel;
import com.pluralis.plucker.gui.TestApplication;
import com.pluralis.plucker.gui.dialog.ActorEditor;
import com.pluralis.plucker.gui.dialog.ChangeRequestEditor;
import com.pluralis.plucker.gui.dialog.ProjectPropertiesEditor;
import com.pluralis.plucker.gui.dialog.RequirementsEditor;
import com.pluralis.plucker.gui.dialog.TaskDialog;
import com.pluralis.plucker.gui.dialog.UseCaseEditor;

/**
 * Renders a specified component and writes it to a specified PNG file.
 *
 * @author Karsten
 */
public class ScreenShooter {

  public static final String BASE_PATH = "res/help/";
  
  public static void main(String[] args) {
    Application app = new TestApplication();
    ScreenShooter shooter = new ScreenShooter();
    
    shooter.snap(new TaskDialog(app).create(), BASE_PATH + "taskdialog.png");
    
    // Use-Case
    UseCaseEditor uced = new UseCaseEditor(app);
    shooter.snap(uced, BASE_PATH + "uc-editor_1.png");
    uced.setTabIndex(1);
    shooter.snap(uced, BASE_PATH + "uc-editor_2.png");
    
    shooter.snap(new ActorEditor(app).create(), BASE_PATH + "actor-editor.png");
    
    shooter.snap(new ProjectPropertiesEditor(app).create(), BASE_PATH + "project.png");
    
    shooter.snap(new Components(app).create(), BASE_PATH + "components.png");
    
    shooter.snap(new RequirementsEditor(app).create(), BASE_PATH + "requirements.png");

    shooter.snap(new NavigationPanel(app).create(), BASE_PATH + "navigation.png");
    
    shooter.snap(new TaskDialog(app).create(), BASE_PATH + "taskdialog.png");

    //shooter.snap(new ChangeRequestEditor(app).create(), BASE_PATH + "cr-editor.png");

    
    System.exit(0);
  }
  
  public void snap(JComponent comp, String filename) {
    comp.setBorder(new EmptyBorder(5,5,5,5));
    comp.setVisible(true);
    JDialog packer = new JDialog();
    packer.getContentPane().add(comp);
    packer.pack();

    int width = comp.getWidth();
    int height = comp.getHeight();

    BufferedImage bufferedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = bufferedImage.createGraphics();

    comp.paint(g2d);

    try {
      File file = new File(filename);
      if (file.exists()) return;
      ImageIO.write(bufferedImage, "png", file);

      System.out.println("Wrote: " + file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
