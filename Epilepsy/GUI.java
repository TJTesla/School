import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Random;

public class GUI extends JFrame implements ActionListener 
{
    JLabel lbl = new JLabel();
    JLabel[] movingLbls = new JLabel[10];
    JLabel lblTwo = new JLabel();
    JButton btn = new JButton("START");
    Random rng = new Random();
    Timer timerOne, timerTwo;
    
    Color[] colors = { Color.BLUE,
                    Color.CYAN, Color.DARK_GRAY,
                    Color.GRAY, Color.GREEN,
                    Color.LIGHT_GRAY, Color.MAGENTA,
                    Color.PINK, Color.RED,
                    Color.ORANGE, Color.YELLOW };
    
    int sizeX = 700, sizeY = 700;
                    
    public GUI() {
        super("EPILEPSIE!!!");
        setSize(sizeX, sizeY);
        setVisible(true);
        setResizable(true);
        
        lbl.setBounds(sizeX/4, sizeY/4, sizeX/2, sizeY/2);
        lbl.setOpaque(true);
        lbl.setVisible(false);
        
        for (int i = 0; i < movingLbls.length; i++) {
            movingLbls[i] = new JLabel();
            movingLbls[i].setBounds(0, 0, sizeX/10, sizeY/15);
            movingLbls[i].setOpaque(true);
            movingLbls[i].setVisible(false);
            add(movingLbls[i]);
        }
        
        /*
        lblTwo.setBounds(0, 0, size/20, size/20);
        lblTwo.setOpaque(true);
        lblTwo.setVisible(true); */
        
        btn.setBounds(0, 0, sizeX, sizeY);
        btn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        btn.setActionCommand("Start");
        btn.addActionListener(this);
        btn.setVisible(true);
        
        timerOne = new Timer(15, new ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                startEpilepsie();
            }
        } );
        timerOne.stop();
        
        timerTwo = new Timer(37, new ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                moveDecal();
            }
        } );
        timerTwo.stop();
        
        add(lbl);
        //add(lblTwo);
        add(btn);
    }
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Start")) {
            btn.setVisible(false);
            lbl.setVisible(true);
            for (int i = 0; i < movingLbls.length; i++) {
                movingLbls[i].setVisible(true);
            }
            timerOne.start();
            try { Thread.sleep(rng.nextInt(30)); }
            catch (Exception e) {};
            timerTwo.start();
        }
    }
    
    private void moveDecal() {
        int rndIndex = 0;
        sizeX = getContentPane().getSize().width;
        sizeY = getContentPane().getSize().height;
        for (int i = 0; i < movingLbls.length; i++) {
            rndIndex = rng.nextInt(colors.length);
            movingLbls[i].setBackground(colors[rndIndex]);
            movingLbls[i].setBounds(rng.nextInt(sizeX-sizeX/10), rng.nextInt(sizeY-sizeY/15), sizeX/10, sizeY/15);
        }
    }
    
    private void startEpilepsie() {
        int rndIndex = rng.nextInt(colors.length);
        getContentPane().setBackground(colors[rndIndex]);
        rndIndex = rng.nextInt(colors.length);
        lbl.setBounds(sizeX/4, sizeY/4, sizeX/2, sizeY/2);
        lbl.setBackground(colors[rndIndex]);
    }
}