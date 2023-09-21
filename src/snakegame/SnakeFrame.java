package snakegame;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    SnakeFrame(){
    SnakePanel panel = new SnakePanel();
        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    }


