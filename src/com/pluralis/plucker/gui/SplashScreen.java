/*
 * Created on 19.11.2005 by Karsten $Id$
 */
package com.pluralis.plucker.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import de.kroesch.util.GuiUtils;


class SplashScreen extends JWindow {
  
  public final JProgressBar progressBar = new JProgressBar();
  
  public SplashScreen(JFrame f, int waitTime) {
    super(f);
    JLabel l = new JLabel(GuiUtils.readImageIcon("logo.jpg"));

    getContentPane().add(l, BorderLayout.CENTER);
    
    progressBar.setIndeterminate(true);
    progressBar.setStringPainted(true);    
    progressBar.setString("Loading...");
    getContentPane().add(progressBar, BorderLayout.SOUTH);
    
    pack();
    GuiUtils.centerOnScreen(this);
    
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        setVisible(false);
        dispose();
      }
    });
    
    final int pause = waitTime;
    final Runnable closerRunner = new Runnable() {
      public void run() {
        setVisible(false);
        dispose();
      }
    };
    Runnable waitRunner = new Runnable() {
      public void run() {
        try {
          Thread.sleep(pause);
          SwingUtilities.invokeAndWait(closerRunner);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
    
    setVisible(true);
    Thread splashThread = new Thread(waitRunner, "SplashThread");
    
    if (waitTime > 0) splashThread.start();
  }
}