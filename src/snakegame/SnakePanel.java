
package snakegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class SnakePanel extends JPanel implements ActionListener {
//ZetCode.2023.Java Snake.[Online].Available at: https://zetcode.com/javagames/snake/ [Accessed 12 September 2021]
//Java Coding Community - Programming Tutorials(2022)Snake Game in Java Step by Step | Java2D package Tutorial/Available at: https://www.youtube.com/watch?v=7t5uwECup4I [Accessed 14 September 2023]
//GeeksforGeeks.2023.Design Snake Game.[Online].Available at: https://www.geeksforgeeks.org/design-snake-game/amp/ [Accessed 16 September 2023]
    private static final long serialVersionUID = 1L;

    static final int width = 500;
    static final int height = 500;
    static final int unit_size = 20;
    static final int num_units = (width * height) / (unit_size * unit_size);
    //Array to store the co-ordinates for the body of the snake
    final int x[] = new int[num_units];
    final int y[] = new int[num_units];

    //initial length of the snake
    int length = 5;
    int foodEaten;
    int foodX;
    int foodY;
    char direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;

    public SnakePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();
    }

    public void play() {
        addFood();
        running = true;
        timer = new Timer(80, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void move() {
        for (int i = length; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (direction == 'L') {
            x[0] = x[0] - unit_size;
        } else if (direction == 'R') {
            x[0] = x[0] + unit_size;
        } else if (direction == 'U') {
            y[0] = y[0] - unit_size;
        } else {
            y[0] = y[0] + unit_size;
        }
    }

    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            length++;
            foodEaten++;
            addFood();
        }
    }

    public void draw(Graphics graphics) {

        if (running) {

            graphics.setColor(new Color(200, 110, 80));
            graphics.fillOval(foodX, foodY, unit_size, unit_size);

            graphics.setColor(Color.white);
            graphics.fillRect(x[0], y[0], unit_size, unit_size);

            for (int i = 0; i < length; i++) {
                graphics.setColor(new Color(40, 200, 160));
                graphics.fillOval(x[i], y[i], unit_size, unit_size);
            }

            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans Sherif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score " + foodEaten, (width - metrics.stringWidth("Score " + foodEaten)) / 2, graphics.getFont().getSize());
        } else {
            gameOver(graphics);
        }
    }

    public void addFood() {

        foodX = random.nextInt((int) (width / unit_size)) * unit_size;
        foodY = random.nextInt((int) (height / unit_size)) * unit_size;

    }

    public void checkHit() {
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }

        }
        if (x[0] < 0 || x[0] > width || y[0] < 0 || y[0] > height) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics graphics) {

        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans Sherif", Font.ROMAN_BASELINE, 25));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over ", (width - metrics.stringWidth("Game Over ")) / 2, height / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans Sherif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score " + foodEaten, (width - metrics.stringWidth("Score " + foodEaten)) / 2, graphics.getFont().getSize());

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }

                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }

                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }

                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }

                    break;
            }
        }
    }
}
