package SudokuController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import SudokuModel.Game;



public class ButtonController implements ActionListener {
    private Game game;


    public ButtonController(Game game) {
        this.game = game;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Nowa Gra"))
            game.newGame();
        else if (e.getActionCommand().equals("Wyczyść"))
            game.Restart();
        else if (e.getActionCommand().equals("Wyjście"))
            System.exit(0);
        else
            game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
    }
}