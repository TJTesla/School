import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;

public class Frame extends JFrame implements ActionListener
{
    JLabel mainLbl = new JLabel("Test", SwingConstants.RIGHT);
    JButton[] btnNums = new JButton[10];
    
    JButton enter = new JButton();
    
    public Frame() {
        super("Calculator");
        setLayout(null);
        int windowWidth = 480, windowHeight = 720;
        setSize(windowWidth, windowHeight);
        setVisible(true);
        setResizable(true);
        
        mainLbl.setSize(windowWidth-16, windowHeight/8);
        mainLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        mainLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainLbl.setVisible(true);
        
        int restHeight= windowHeight - windowHeight/8;
        int newWidth = 116, newHeight = 118;
        int startingHeight = windowHeight/8;
        int bottomHeight = newHeight/2+24;
        
        Pos[] posBtnNums = new Pos[10];
        posBtnNums[0] = new Pos(0, startingHeight + newHeight*4, newWidth, newHeight);
        posBtnNums[1] = new Pos(0, startingHeight + newHeight*3, newWidth, newHeight);
        posBtnNums[2] = new Pos(newWidth, startingHeight + newHeight*3, newWidth, newHeight);
        posBtnNums[3] = new Pos(newWidth*2, startingHeight + newHeight*3, newWidth, newHeight);
        posBtnNums[4] = new Pos(0, startingHeight + newHeight*2, newWidth, newHeight);
        posBtnNums[5] = new Pos(newWidth, startingHeight + newHeight*2, newWidth, newHeight);
        posBtnNums[6] = new Pos(newWidth*2, startingHeight + newHeight*2, newWidth, newHeight);
        posBtnNums[7] = new Pos(0, startingHeight + newHeight, newWidth, newHeight);
        posBtnNums[8] = new Pos(newWidth, startingHeight + newHeight, newWidth, newHeight);
        posBtnNums[9] = new Pos(newWidth*2, startingHeight + newHeight, newWidth, newHeight);
        
        String[] names = { "NULL", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
            "SEVEN", "EIGHT", "NINE" };
        
        for (int i = 0; i < btnNums.length; i++) {
            btnNums[i] = new JButton(""+i);
            btnNums[i].setBounds(posBtnNums[i].x, posBtnNums[i].y, 
                            posBtnNums[i].width, posBtnNums[i].height);
            btnNums[i].setOpaque(true);
            btnNums[i].setBackground(Color.WHITE);
            btnNums[i].addActionListener(this);
            btnNums[i].setActionCommand(names[i]);
        }
        
        enter.setBounds(newWidth*3, startingHeight + newHeight*2, newWidth, newHeight*2);
        
        ImageIcon icon = new ImageIcon("ReturnKey.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 70, 10, enter.getSize().width, enter.getSize().height, null);
        ImageIcon finalIcon = new ImageIcon(bi);
        enter.setIcon(finalIcon);
        
        enter.setOpaque(true);
        enter.setBackground(Color.ORANGE);
        enter.setVisible(true);
        
        
        
        
        
        
        add(enter);
        for (int i = 0; i < btnNums.length; i++) {
            add(btnNums[i]);
        }
        add(mainLbl);
        for (int i = 0; i < btnNums.length; i++) {
            btnNums[i].setVisible(true);
        }
        repaint();
    }
    
    public void actionPerformed(ActionEvent evt) {
    }
}
