package SudokuView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class ButtonPanel extends JPanel {
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

        JPanel pnlOptions = new JPanel();
        pnlOptions.setLayout(new BoxLayout(pnlOptions, BoxLayout.PAGE_AXIS));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Opcje "));
        pnlAlign.add(pnlOptions);

        
        JPanel pnlOptionsA = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlOptionsA);
        
        btnNew = new JButton("Nowa Gra");
        btnNew.setFocusable(false);
        pnlOptionsA.add(btnNew);
        
        btnRestart = new JButton("Wyczyść");
        btnRestart.setFocusable(false);
        pnlOptionsA.add(btnRestart);
                     

        
        JPanel pnlOptionsB = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.add(pnlOptionsB);
        btnCheck = new JButton("Sprawdź");
        btnCheck.setFocusable(false);
        pnlOptionsB.add(btnCheck);

        btnExit = new JButton("Wyjście");
        btnExit.setFocusable(false);
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

        JPanel pnlNumbersNumbersA = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbersA);
        JPanel pnlNumbersNumbersB = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbersB);
        JPanel pnlNumbersNumbersC = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbersC);


        bgNumbersA = new ButtonGroup();
        bgNumbersB = new ButtonGroup();
        bgNumbersC = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 3; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(50, 50));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersA.add(btnNumbers[i]);
        }

        
            for (int i = 3; i < 6; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(50, 50));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersB.add(btnNumbers[i]);
        }

            for (int i = 6; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(50, 50));
            btnNumbers[i].setFocusable(false);
            bgNumbersA.add(btnNumbers[i]);
            pnlNumbersNumbersC.add(btnNumbers[i]);
        }
    }
}