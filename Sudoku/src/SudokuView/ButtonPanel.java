package SudokuView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import SudokuController.ButtonController;
import SudokuModel.UpdateAction;


public class ButtonPanel extends JPanel implements Observer {
    JButton btnNew, btnCheck, btnExit, btnRestart;   // Przyciski.
    JCheckBox cbHelp;               // Pomoc.
    ButtonGroup bgNumbersA;          // Grupa przycisków A.
    ButtonGroup bgNumbersB;          // Grupa przycisków B.
    ButtonGroup bgNumbersC;          // Grupa przycisków C.
    JToggleButton[] btnNumbers;     // Przełącznik przycisków.

    public ButtonPanel() {
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

                JPanel pnlSpace = new JPanel();
        pnlSpace.setLayout(new BoxLayout(pnlSpace, BoxLayout.PAGE_AXIS));
        pnlSpace.setBorder(BorderFactory.createEmptyBorder(41, 0, 0, 0));
        pnlAlign.add(pnlSpace);

        
        JPanel pnlOptions = new JPanel();
        pnlOptions.setLayout(new BoxLayout(pnlOptions, BoxLayout.PAGE_AXIS));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Opcje "));
        pnlAlign.add(pnlOptions);

        
        
        JPanel pnlOptionsA = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlOptionsA);
        
        btnNew = new JButton("Nowa Gra");
        btnNew.setFocusable(false);
        btnNew.setPreferredSize(new Dimension(83, 25));
        pnlOptionsA.add(btnNew);
        
        
        btnRestart = new JButton("Wyczyść");
        btnRestart.setFocusable(false);
        btnRestart.setPreferredSize(new Dimension(83, 25));
        pnlOptionsA.add(btnRestart);
        
        
        JPanel pnlOptionsB = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlOptionsB);
        btnCheck = new JButton("Sprawdź");
        btnCheck.setFocusable(false);
        btnCheck.setPreferredSize(new Dimension(83, 25));
        pnlOptionsB.add(btnCheck);

        btnExit = new JButton("Wyjście");
        btnExit.setFocusable(false);
        btnExit.setPreferredSize(new Dimension(83, 25));
        pnlOptionsB.add(btnExit);
        
       
        
        
        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Cyfry "));
        
        pnlAlign.add(pnlNumbers);


        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);


        cbHelp = new JCheckBox("Podpowiedź", false);
        cbHelp.setFocusable(false);
        pnlNumbersHelp.add(cbHelp);

        JPanel pnlNumbersNumbersA = new JPanel(new GridLayout());
        pnlNumbers.add(pnlNumbersNumbersA);
        JPanel pnlNumbersNumbersB = new JPanel(new GridLayout());
        pnlNumbers.add(pnlNumbersNumbersB);
        JPanel pnlNumbersNumbersC = new JPanel(new GridLayout());
        pnlNumbers.add(pnlNumbersNumbersC);


        bgNumbersA = new ButtonGroup();
        bgNumbersB = new ButtonGroup();
        bgNumbersC = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 3; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(60, 60));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersA.add(btnNumbers[i]);
        }

        
            for (int i = 3; i < 6; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(60, 60));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersB.add(btnNumbers[i]);
        }

            for (int i = 6; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(60, 60));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersC.add(btnNumbers[i]);
        }
    }

    public void update(Observable o, Object arg) {
        switch ((UpdateAction)arg) {
            case NEW_GAME:
            case CHECK:
                bgNumbersA.clearSelection();
                bgNumbersB.clearSelection();
                bgNumbersC.clearSelection();
                break;
        }
    }

   
    
    public void setController(ButtonController buttonController) {
        btnNew.addActionListener(buttonController);
        btnRestart.addActionListener(buttonController);
   //     cbDifficulty.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        cbHelp.addActionListener(buttonController);
        for (int i = 0; i < 9; i++)
            btnNumbers[i].addActionListener(buttonController);
    }
}