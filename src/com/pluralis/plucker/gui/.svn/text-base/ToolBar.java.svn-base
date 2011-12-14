package com.pluralis.plucker.gui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;

import de.kroesch.util.GuiUtils;


public class ToolBar implements ComponentCreator {

  private Settings settings;
  
  public ToolBar(Settings settings) {
    this.settings = settings;
  }

  public JComponent create() {
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(true);
    toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
    // Swing
    toolBar.putClientProperty(Options.HEADER_STYLE_KEY, settings
        .getToolBarHeaderStyle());
    toolBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, settings
        .getToolBarPlasticBorderStyle());
    toolBar.putClientProperty(WindowsLookAndFeel.BORDER_STYLE_KEY, settings
        .getToolBarWindowsBorderStyle());
    toolBar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY, settings
        .getToolBar3DHint());

    AbstractButton button;

    toolBar.add(createToolBarButton("backward.gif"));
    button = createToolBarButton("forward.gif");
    button.setEnabled(false);
    toolBar.add(button);
    toolBar.add(createToolBarButton("home.gif"));
    toolBar.addSeparator();
    toolBar.add(createToolBarButton("undo.gif"));
    toolBar.add(createToolBarButton("redo.gif"));
    toolBar.addSeparator();
    toolBar.add(createToolBarButton("cut.gif"));
    toolBar.add(createToolBarButton("copy.gif"));
    toolBar.add(createToolBarButton("paste.gif"));
    toolBar.addSeparator();
    toolBar.add(createToolBarButton("refresh.gif"));
    toolBar.add(createToolBarButton("print.gif"));
    toolBar.addSeparator();

    ButtonGroup group = new ButtonGroup();
    button = createToolBarRadioButton("pie_mode.png");
    button.setSelectedIcon(GuiUtils.readImageIcon("pie_mode_selected.gif"));
    group.add(button);
    button.setSelected(true);
    toolBar.add(button);

    button = createToolBarRadioButton("bar_mode.png");
    button.setSelectedIcon(GuiUtils.readImageIcon("bar_mode_selected.gif"));
    group.add(button);
    toolBar.add(button);

    button = createToolBarRadioButton("table_mode.png");
    button.setSelectedIcon(GuiUtils.readImageIcon("table_mode_selected.gif"));
    group.add(button);
    toolBar.add(button);
    toolBar.addSeparator();

    button = createToolBarButton("help.gif");
    toolBar.add(button);

    return toolBar;
  }

  /**
   * Creates and returns a <code>JButton</code> 
   * configured for use in a JToolBar.<p>
   * 
   * This is a simplified method that is overriden by the Looks Demo.
   * The full code uses the JGoodies UI framework's ToolBarButton
   * that better handles platform differences.
   */
  protected AbstractButton createToolBarButton(String iconName) {
    JButton button = new JButton(GuiUtils.readImageIcon(iconName));
    button.setFocusable(false);
    return button;
  }
  
  /**
   * Creates and returns a <code>JToggleButton</code> 
   * configured for use in a JToolBar.<p>
   * 
   * This is a simplified method that is overriden by the Looks Demo.
   * The full code uses the JGoodies UI framework's ToolBarButton
   * that better handles platform differences.
   */
  protected AbstractButton createToolBarRadioButton(String iconName) {
    JToggleButton button = new JToggleButton(GuiUtils.readImageIcon(iconName));
    button.setFocusable(false);
    return button;
  }


}
