package view;

import util.GameManager;
import util.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    JLabel title;
    JButton button;

    //Input: the application window, Output: none
    //Purpose: constructor for the MenuPanel
    //Example: none needed
    MenuPanel(Window window) {

        this.setPreferredSize(new Dimension(Window.screenWidth, Window.screenHeight));
        this.setLayout(null);

        title = new JLabel("Farming Soldier");
        title.setFont(new Font("Ariel",Font.PLAIN,24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(Window.halfScreenWidth-100,Window.halfScreenHeight-200,200,100);
        this.add(title);

        button = new JButton("play");
        button.addActionListener(new PlayGame(window));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBounds(Window.halfScreenWidth-100,Window.halfScreenHeight-100,200,200);
        this.add(button);
    }
}



class PlayGame implements ActionListener {
    Window window;
    PlayGame(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameManager.startGame();
    }
}
