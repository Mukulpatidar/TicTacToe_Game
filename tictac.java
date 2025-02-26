import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FDemo extends JFrame implements ActionListener {
    JButton b1[] = new JButton[9]; // Buttons for the grid
    JButton restartButton; // Restart button
    boolean xTurn = true; // Track player turn
    JLabel statusLabel = new JLabel("Player X's Turn"); // Status label

    FDemo() {
        setTitle("Tic Tac Toe");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label at the top
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // Panel for Tic Tac Toe Grid
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        add(panel, BorderLayout.CENTER);

        // Initialize buttons
        for (int i = 0; i < 9; i++) {
            b1[i] = new JButton();
            b1[i].setFont(new Font("Arial", Font.BOLD, 40));
            b1[i].setFocusPainted(false);
            b1[i].addActionListener(this);
            panel.add(b1[i]);
        }

        // Restart Button
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 20));
        restartButton.addActionListener(e -> resetGame());

        // Panel for Restart Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(restartButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Prevent overwriting moves
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Set X or O
        clickedButton.setText(xTurn ? "X" : "O");

        // Check for winner
        if (checkWinner()) {
            statusLabel.setText((xTurn ? "Player X" : "Player O") + " Wins!");
            disableButtons();
            return;
        }

        // Check for tie
        if (isBoardFull()) {
            statusLabel.setText("It's a Tie!");
            return;
        }

        // Switch turns
        xTurn = !xTurn;
        statusLabel.setText(xTurn ? "Player X's Turn" : "Player O's Turn");
    }

    private boolean checkWinner() {
        String[][] board = new String[3][3];

        // Read board state
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = b1[i].getText();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) return true;
            if (!board[0][i].equals("") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) return true;
        }
        if (!board[0][0].equals("") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) return true;
        if (!board[0][2].equals("") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : b1) {
            if (button.getText().equals("")) return false;
        }
        return true;
    }

    private void disableButtons() {
        for (JButton button : b1) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        for (JButton button : b1) {
            button.setText("");
            button.setEnabled(true);
        }
        xTurn = true;
        statusLabel.setText("Player X's Turn");
    }
}

public class tictac {
    public static void main(String args[]) {
        FDemo F = new FDemo();
        F.setVisible(true);
    }
}

