import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LudoDiceGame {

    static int score1 = 0;
    static int score2 = 0;
    static boolean player1Turn = true;
    static Random rand = new Random();
    static boolean isStarted1=false;
    static boolean isStarted2=false;

    public static void main(String[] args) {

        JFrame frame = new JFrame("LudoDiceGame");
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("LUDO DICE GAME", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(150, 10, 400, 50);

        JLabel turnLabel = new JLabel("Player 1 Turn", JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setBounds(200, 60, 300, 30);

        JLabel diceLabel = new JLabel(new ImageIcon("1.jpg"));
        diceLabel.setBounds(200, 100, 300, 300);

        JLabel scoreLabel1 = new JLabel("Player 1 Score: 0");
        JLabel scoreLabel2 = new JLabel("Player 2 Score: 0");

        scoreLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel2.setFont(new Font("Arial", Font.BOLD, 18));

        scoreLabel1.setBounds(100, 420, 250, 30);
        scoreLabel2.setBounds(400, 420, 250, 30);

        JButton rollButton = new JButton("ROLL DICE");
        rollButton.setFont(new Font("Arial", Font.BOLD, 18));
        rollButton.setBounds(250, 470, 200, 60);

        JButton resetButton = new JButton("RESET GAME");
        resetButton.setBounds(250, 550, 200, 40);

        // Dice Roll Logic
        rollButton.addActionListener(e -> {
            int num = rand.nextInt(6) + 1;
            diceLabel.setIcon(new ImageIcon(num + ".jpg"));

            if (player1Turn) {
                if (!isStarted1 && num == 6) {
                    isStarted1 = true;
                }
                if (isStarted1) {
                    score1 += num;
                    scoreLabel1.setText("Player 1 Score: " + score1);
                }


                if (score1 >= 30) {
                    JOptionPane.showMessageDialog(frame, "Player 1 Wins! 🎉");
                    resetGame(scoreLabel1, scoreLabel2, turnLabel);
                    return;
                }
                if (num != 6) {
                    player1Turn = false;
                    turnLabel.setText("Player 2 Turn");

                }
            }
                else {
                    if (!isStarted2 && num == 6) {
                        isStarted2 = true;

                    }
                    if (isStarted2) {
                        score2 += num;
                        scoreLabel2.setText("Player 2 Score: " + score2);
                    }
                    if (score2 >= 30) {
                        JOptionPane.showMessageDialog(frame, "Player 2 Wins! 🎉");
                        resetGame(scoreLabel1, scoreLabel2, turnLabel);
                        return;
                    }
                    if (num != 6) {
                        player1Turn = true;
                        turnLabel.setText("Player 1 Turn");
                    }
                }
        });

        // Reset Button
        resetButton.addActionListener(e -> resetGame(scoreLabel1, scoreLabel2, turnLabel));

        frame.add(title);
        frame.add(turnLabel);
        frame.add(diceLabel);
        frame.add(scoreLabel1);
        frame.add(scoreLabel2);
        frame.add(rollButton);
        frame.add(resetButton);

        frame.setVisible(true);
    }

    static void resetGame(JLabel s1, JLabel s2, JLabel turnLabel) {
        score1 = 0;
        score2 = 0;
        isStarted1=false;
        isStarted2=false;
        player1Turn = true;

        s1.setText("Player 1 Score: 0");
        s2.setText("Player 2 Score: 0");
        turnLabel.setText("Player 1 Turn");
    }
}
