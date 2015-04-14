package SudokuModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Ta klasa reprezentuje grę Sudoku. Zawiera rozwiązanie, wybór gracza,
 * wybrany numer oraz metody sprawdzające.
 */
public class Game extends Observable {
    private int[][] solution;  
    private int[][] game; 
   // public int[][] gamecopy;  
    private int[][] copy;
    private boolean[][] check;
    private int[][] gamecopy;
    private int selectedNumber;  
    private boolean help; 
    private List<Integer> positions = new ArrayList<Integer>();


    /**
     * Konstruktor
     */
    public Game() {
        newGame();
        check = new boolean[9][9];
        help = false;
    }
 
    
    /**
     * Restartuje grę
     */
    public void Restart() {
     
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                    game[y][x] = gamecopy[y][x];
        }
        
        setChanged();
        notifyObservers(UpdateAction.NEW_GAME);
    }


    
    /**
     * Generuje nową grę.
     */
    public void newGame() {
        solution = generateSolution(new int[9][9], 0);
        game = generateGame(copy(solution));
        gamecopy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++){
                gamecopy[y][x] = game[y][x];
        }
        }
        setChanged();
        notifyObservers(UpdateAction.NEW_GAME);

        
    }


    /**
     * Liczba wybrana przez gracza.
     */
    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
        setChanged();
        notifyObservers(UpdateAction.SELECTED_NUMBER);
    }

    /**
     * Zwraca wybraną liczbę.
     *
     */
    public int getSelectedNumber() {
        return selectedNumber;
    }

    /**
     * Sprawdzenie czy pomoc jest włączona
     *
     */
    public boolean isHelp() {
        return help;
    }

    /**
     * Sprawdza czy liczba pasuje do danej pozycji.
     *
     */
    public boolean isSelectedNumberCandidate(int x, int y) {
        return game[y][x] == 0 && isPossibleX(game, y, selectedNumber)
                && isPossibleY(game, x, selectedNumber) && isPossibleBlock(game, x, y, selectedNumber);
    }

    /**
     * Wpisuje wybraną liczbę do wybranej pozycji.
     */
    public void setNumber(int x, int y, int number) {
        game[y][x] = number;        
    }

    /**
     * Zwraca x oraz y wybranej pozycji
     */
    public int getNumber(int x, int y) {
        return game[y][x];
    }

    /**
     * Sprawdza poprawność wylosowanej liczby
     */
    public boolean isCheckValid(int x, int y) {
        return check[y][x];
    }

    /**
     * Sprawdza poprawność na osi X
     */
    private boolean isPossibleX(int[][] game, int y, int number) {
        for (int x = 0; x < 9; x++) {
            if (game[y][x] == number)
                return false;
        }
        return true;
    }

    /**
     * Sprawdza poprawność na osi Y
     */
    private boolean isPossibleY(int[][] game, int x, int number) {
        for (int y = 0; y < 9; y++) {
            if (game[y][x] == number)
                return false;
        }
        return true;
    }

    /**
     * Sprawdza poprawność w kwadracie 3x3
     */
    private boolean isPossibleBlock(int[][] game, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (game[yy][xx] == number)
                    return false;
            }
        }
        return true;
    }

    /**
     * Zwraca następną możliwą liczbę
     */
    private int getNextPossibleNumber(int[][] game, int x, int y, List<Integer> numbers) {
        while (numbers.size() > 0) {
            int number = numbers.remove(0);
            if (isPossibleX(game, y, number) && isPossibleY(game, x, number) && isPossibleBlock(game, x, y, number))
                return number;
        }
        return -1;
    }

    /**
     * Generuje rozwiązanie
     */
    private int[][] generateSolution(int[][] game, int index) {
        if (index > 80)
            return game;

        int x = index % 9;
        int y = index / 9;

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) numbers.add(i);
        Collections.shuffle(numbers);

        while (numbers.size() > 0) {
            int number = getNextPossibleNumber(game, x, y, numbers);
            if (number == -1)
                return null;

            game[y][x] = number;
            int[][] tmpGame = generateSolution(game, index + 1);
            if (tmpGame != null)
                return tmpGame;
            game[y][x] = 0;
        }

        return null;
    }

    /**
     * Generuje grę na podstawie rozwiązania
     */
    private int[][] generateGame(int[][] game) {
        for (int i = 0; i < 81; i++)
            positions.add(i);
        Collections.shuffle(positions);
        return generateGame(game, positions);
    }

    /**
     * Usuwa losowe liczby z rozwiązania
     *
     */
    private int[][] generateGame(int[][] game, List<Integer> positions) {

        while (positions.size() > 0) {
            int position = positions.remove(0);
            int x = position % 9;
            int y = position / 9;
            int temp = game[y][x];
            game[y][x] = 0;

            

            if (!isValid(game)){
                game[y][x] = temp;
        }
        }

        return game;
    }
    
    

    /**
     * Sprawdza poprawność wygenerowanej gry
     */
    private boolean isValid(int[][] game) {
        return isValid(game, 0, new int[] { 0 });
    }

    /**
     * Sprawdza ilość rozwiązań (może być tylko jedno poprawne rozwiązanie)
     */
    private boolean isValid(int[][] game, int index, int[] numberOfSolutions) {
        if (index > 80)
            return ++numberOfSolutions[0] == 1;

        int x = index % 9;
        int y = index / 9;

        if (game[y][x] == 0) {
            List<Integer> numbers = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++)
                numbers.add(i);

            while (numbers.size() > 0) {
                int number = getNextPossibleNumber(game, x, y, numbers);
                if (number == -1)
                    break;
                game[y][x] = number;

                if (!isValid(game, index + 1, numberOfSolutions)) {
                    game[y][x] = 0;
                    return false;
                }
                game[y][x] = 0;
            }
        } else if (!isValid(game, index + 1, numberOfSolutions))
            return false;

        return true;
    }

    /**
     * Kopiuje grę
     */
    private int[][] copy(int[][] game) {
        //int[][] copy = new int[9][9];
        copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
                copy[y][x] = game[y][x];
        }
        return copy;
    }

}