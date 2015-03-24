package SudokuView;

import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Główna klasa programu.
 */
public class Sudoku extends JFrame {
    public Sudoku() {
        super("Sudoku nasze jest i bedzie zawsze teraz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        ButtonPanel buttonPanel = new ButtonPanel();
        add(buttonPanel, BorderLayout.EAST);
        SudokuPanel sudokuPanel = new SudokuPanel();
        add(sudokuPanel, BorderLayout.CENTER);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
         new Sudoku();
    }
}