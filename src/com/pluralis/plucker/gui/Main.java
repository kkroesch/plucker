package com.pluralis.plucker.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.pluralis.plucker.gui.action.FileExit;
import com.pluralis.plucker.model.ClientOptions;
import com.pluralis.plucker.model.Project;
import com.pluralis.plucker.plugin.PluginRegistry;

import de.kroesch.util.GuiUtils;


/**
 * Main window for the application. 
 * 
 * It implements the Application interface which is given to all dependant
 * objects and also responsible for communicating with the plugins.
 * 
 * @author Karsten 
 */
public class Main extends JFrame implements Application {

  protected static final Dimension PREFERRED_SIZE = new Dimension(1024, 768);

  private static JFrame frame;
  
  private static final ClientOptions options = new ClientOptions();
  
  private static ContainerPanel contentPanel;

  private MenuBar bar;
  
  private static StatusBar statusBar = new StatusBar();

  private SplashScreen splash;
  
  /** Describes optional settings of the JGoodies Looks. */
  private final Settings settings;
  
  private static Logger log = Logger.getLogger("Main");
  
  private Project project = new Project();
  
  /**
   * Constructs a <code>DemoFrame</code>, configures the UI, 
   * and builds the content.
   */
  protected Main(Settings settings) {
    this.settings = settings;
    frame = this;

    configureUI();

    splash = new SplashScreen(frame, 0);
    splash.progressBar.setString("Waiting for login...");
    
    splash.progressBar.setString("Building User Interface...");
    build();
    splash.dispose();
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        new FileExit(Main.this).actionPerformed(null);
      }
    });
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    // Run tip of the day
    new Thread(new TipRunner(this)).start();
    
  }

  public static void main(String[] args) {
    JFrame instance = new JFrame();
    try {
      instance = new Main(createSettings());
      instance.setSize(PREFERRED_SIZE);
      GuiUtils.centerOnScreen(instance);
      
      instance.setVisible(true);
    } catch (RuntimeException t) {
      JOptionPane.showMessageDialog(instance, "Cannot start Application: " + t, "ERROR", JOptionPane.ERROR_MESSAGE);
      log.severe("Cannot start Application.");
      System.exit(1);
    }
  }

  private static Settings createSettings() {
    Settings settings = Settings.createDefault();

    // Configure the settings here.

    return settings;
  }

  /**
   * Configures the user interface; requests Swing settings and 
   * jGoodies Looks options from the launcher.
   */
  private void configureUI() {
    log.info("Configuring Look and Feel");
    Options.setDefaultIconSize(new Dimension(18, 18));

    // Set font options		
    UIManager
        .put(Options.USE_SYSTEM_FONTS_APP_KEY, settings.isUseSystemFonts());
    Options.setGlobalFontSizeHints(settings.getFontSizeHints());
    Options.setUseNarrowButtons(settings.isUseNarrowButtons());

    // Global options
    Options.setTabIconsEnabled(settings.isTabIconsEnabled());
    UIManager.put(Options.POPUP_DROP_SHADOW_ENABLED_KEY, settings
        .isPopupDropShadowEnabled());

    // Swing Settings
    LookAndFeel selectedLaf = settings.getSelectedLookAndFeel();
    if (selectedLaf instanceof PlasticLookAndFeel) {
      PlasticLookAndFeel.setMyCurrentTheme(settings.getSelectedTheme());
      PlasticLookAndFeel.setTabStyle(settings.getPlasticTabStyle());
      PlasticLookAndFeel.setHighContrastFocusColorsEnabled(settings
          .isPlasticHighContrastFocusEnabled());
    } else if (selectedLaf.getClass() == MetalLookAndFeel.class) {
      MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
    }

    // Work around caching in MetalRadioButtonUI
    JRadioButton radio = new JRadioButton();
    radio.getUI().uninstallUI(radio);
    JCheckBox checkBox = new JCheckBox();
    checkBox.getUI().uninstallUI(checkBox);

    try {
      UIManager.setLookAndFeel(selectedLaf);
    } catch (Exception e) {
      log.severe("Can't change L&F" + e.getMessage());
    }

  }

  /**
   * Builds the <code>DemoFrame</code> using Options from the Launcher.
   */
  private void build() {
    log.fine("Building user interface");

    setContentPane(buildContentPane());
    setTitle(getWindowTitle());

    bar = new MenuBar(this);
    JMenuBar menubar = bar.create();
    menubar.putClientProperty(Options.HEADER_STYLE_KEY, settings
        .getMenuBarHeaderStyle());
    menubar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, settings
        .getMenuBarPlasticBorderStyle());
    menubar.putClientProperty(WindowsLookAndFeel.BORDER_STYLE_KEY, settings
        .getMenuBarWindowsBorderStyle());
    menubar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY, settings
        .getMenuBar3DHint());

    setJMenuBar(menubar);

    setIconImage(GuiUtils.readImageIcon("logo.gif").getImage());
  }
  
  /**
   * Builds and answers the content.
   */
  private JComponent buildContentPane() {
    JPanel panel = new JPanel(new BorderLayout());
    
    log.fine("Building toolbar");
    panel.add(new ToolBar(settings).create(), BorderLayout.NORTH);

    log.fine("Building main panel");
    panel.add(buildMainPanel(), BorderLayout.CENTER);
    
    panel.add(statusBar, BorderLayout.SOUTH);
    return panel;
  }

  private Component buildMainPanel() {
    log.fine("Building main panel");
    contentPanel = new ContainerPanel(this);
    JComponent comp = contentPanel.build();

    comp.setBorder(new EmptyBorder(10, 10, 10, 10));
    return comp;
  }

  protected String getWindowTitle() {
    return "PREPare Use Case Engineering";
  }

  public Project getProject() {
    return project;
  }
  
  public void setProject(Project project) {
    log.info("Project set to " + project);
    this.project = project;
  }

  public void setTopic(ComponentCreator comp, String title) {
    contentPanel.setTopicComponent(comp, title);
  }

  public MenuBar menu() {
    return bar;
  }

  public JComponent navigationPanel() {
    // TODO Auto-generated method stub
    return null;
  }

  public ClientOptions options() {
    return options;
  }

  public PluginRegistry pluginRegistry() {
    // TODO Auto-generated method stub
    return null;
  }

  public JFrame rootWindow() {
    return frame;
  }

  public void centerDialog(JDialog dialog) {
    GuiUtils.center(dialog, this);
  }

  public void status(String status) {
    statusBar.setText(status);
  }

  public void userError(String message) {
    JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
  }

  public void userInfo(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }

  public void userWarn(String message) {
    JOptionPane.showMessageDialog(frame, message, "WARNING", JOptionPane.WARNING_MESSAGE);
  }

  public void handle(Throwable throwable) {
    userError(throwable.getMessage());
  }
  
  public String getApplicationName() {
    return "PREPare Use Case Engineering";
  }

  public String getVersion() {
    return "0.8b-2";
  }
}