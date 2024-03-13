import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    public TicTacToe() {
        super("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeButtons();
    }

    private void initializeButtons() {
        setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);

                final int finalRow = row;
                final int finalCol = col;

                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(finalRow, finalCol);
                    }
                });

                add(buttons[row][col]);
            }
        }
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(Character.toString(currentPlayer));

            if (checkWin(row, col)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin(int row, int col) {
        return (checkRow(row) || checkColumn(col) || checkDiagonals() || checkAntiDiagonal());
    }

    private boolean checkRow(int row) {
        return (buttons[row][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[row][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[row][2].getText().equals(Character.toString(currentPlayer)));
    }

    private boolean checkColumn(int col) {
        return (buttons[0][col].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][col].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][col].getText().equals(Character.toString(currentPlayer)));
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][2].getText().equals(Character.toString(currentPlayer)));
    }

    private boolean checkAntiDiagonal() {
        return (buttons[0][2].getText().equals(Character.toString(currentPlayer)) &&
                buttons[1][1].getText().equals(Character.toString(currentPlayer)) &&
                buttons[2][0].getText().equals(Character.toString(currentPlayer)));
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
}

