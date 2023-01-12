import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wilderness
 *
 * @author Adrian Księżak
 */

/**
 * Klasa dziedziczy metody i atrybuty z klasy bazowej JPanel
 */
public class Game extends JPanel {
    /**
     * Wywołanie klasy Avatar na potrzeby uzyskania elementów tablicy oraz liczby punktów i żyć gracza
     */
    Avatar avatar = new Avatar();
    /**
     * Tablica pobierająca aktualne położenie zagadek na planszy oraz rysującej postać gracza
     */
    int[] a = avatar.getMysteryField();
    /**
     * Wywołanie klasy rysującej planszę
     */
    Labirynt labirynt = new Labirynt(a);
    /**
     * Aktualny poziom gry
     */
    public static int level = 1;
    /**
     * Obiekt przechowujący czcionkę użytą w grze
     */
    Font font = new Font("Serif", Font.BOLD, 12);
    /**
     * Obiekt przechowujący pole gry
     */
    public static JFrame myWindow;
    /**
     * Zmienna przechowująca dany wygląd bohatera
     */
    String avatarLook = "/Users/macbookpro/Desktop/git/Wilderness-/Project/Resources/avatar1.png";
    /**
     * zmienne przechowujące liczbę punktów i życia
     */
    int points = 0;
    /**
     * zmienne przechowujące liczbę punktów i życia
     */
    int lifes = 0;

    /**
     * Metoda generująca po wciśnięciu klawisza 'n' okno możliwości zakupu przez gracza nowego stroju lub życia w grze
     */
    public void createShop() {
        lifes = avatar.getLife();
        points = avatar.getPoints();
        /**
         * Modyfikacja domyślnych nazw przycisków okna dialogowego
         */
        UIManager.put("OptionPane.cancelButtonText", "Nic/Anuluj");
        UIManager.put("OptionPane.yesButtonText", "Kup zycie (20pkt)");
        UIManager.put("OptionPane.noButtonText", "Kup nowy stroj(50pkt)");
        String message = "Masz następujacą liczbę punktów: " + points;
        message += "\nCo chcesz zrobic?";
        int answer = JOptionPane.showConfirmDialog(myWindow, message);
        if (answer == JOptionPane.YES_OPTION) {
            if (points >= 20 && lifes >= 3) {
                JOptionPane.showMessageDialog(null, "Masz pełen asortyment życ!\nBrak akcji....");
            } else if (lifes < 3 && points >= 20) {
                lifes++;
                points -= 20;
                avatar.setLife(lifes);
                avatar.setPoints(points);
            } else {
                JOptionPane.showMessageDialog(null, "Nie wystarczająca liczba punktów!\nBrak akcji....");
            }
        } else if (answer == JOptionPane.NO_OPTION) {
            if (points >= 50) {
                if (avatarLook == "/Users/macbookpro/Desktop/git/Wilderness-/Project/Resources/avatar1.png") {
                    avatarLook = "/Users/macbookpro/Desktop/git/Wilderness-/Project/Resources/avatar2.png";
                    points -= 50;
                    avatar.setPoints(points);
                } else avatarLook = "/Users/macbookpro/Desktop/git/Wilderness-/Project/Resources/avatar3.png";
            } else {
                JOptionPane.showMessageDialog(null, "Nie wystarczająca liczba punktów!\nBrak akcji....");
            }
        }
    }

    /**
     * Sprawdzenie czy graczowi skończyły się życia
     *
     * @return Jeśli tak - zakończ grę, w przeciwnym razie kontynuuj
     */
    public boolean theEnd() {
        lifes = avatar.getLife();

        if (lifes <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Konstruktor głównej klasy odpowiadający za rejestrowanie każdego wciśniętego klawisza (szczególnie strzałek do poruśzania postacią) i wykonujący określone akcje.
     */
    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                /**
                 * Wciśnięcie klawisza 'n' przekierowuje do sklepu
                 */
                if (e.getKeyChar() == 'n') {
                    createShop();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                avatar.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                /**
                 * Za każdym razie, gdy klawisz jest zwalniany, sprawdzane są warunki wygranej
                 */
                if (theEnd()) {
                    JOptionPane.showMessageDialog(null, "Przegrałeś, ale możesz spróbować ponownie :)");
                    myWindow.repaint();
                }
            }
        });
        setFocusable(true);
    }

    /**
     * Metoda odpowiedzalna za generowanie graficznej planszy
     *
     * @param graphics Obiekt odpowiedzialny za rysowanie po planszy
     */
    public void paint(Graphics graphics) {
        try {
            labirynt.paint(graphics);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            avatar.paint(graphics, avatarLook);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda odpowiedzialna za zmianę poziomu po spełnieniu odpowiednich warunków
     *
     * @return
     */
    public static int changeLevel() {
        return level++;
    }

    /**
     * Metoda zwracająca aktualny poziom gry
     *
     * @return Aktualny poziom gry
     */
    public static int getLevel() {
        return level;
    }

    /**
     * Główna metoda gry, tworząca okno gry i uruchamiająca rozgrywkę
     *
     * @param args Standardowy argument z języka C
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JOptionPane.showMessageDialog(null, "Welcome to the game Wilderness! Let's start!");
        JFrame myWindow = new JFrame("Wilderness");
        Game game = new Game();
        myWindow.add(game);
        myWindow.setSize(920, 540);
        myWindow.setLocation(300, 200);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * W razie nieprzewidzianych błędów gra ma wsparcie w postaci rozpoczęcia rozgrywki ponownie (repaint)
         */
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "GAME OVER");
            }
            myWindow.repaint();

            /**
             * Po ukończeniu 3 poziomów wynik gracza zapisywany jest do pliku o nazwie "LevelScores". Gracz konczy grę
             */
            if (getLevel() > 3) {

                long finish = System.currentTimeMillis();
                double timeElapsed = (finish - start) / 1000.0;
                System.out.println(timeElapsed);
                writeToFile("Result.txt", timeElapsed);

                JOptionPane.showMessageDialog(null, "YOU WIN");
                System.exit(0);
            }
        }
    }

    /**
     * Metoda zapisujaca do pliku wynik gry
     *
     * @param fileName nazwa pliku do zapisu
     * @param s        wynik do zapisu
     */
    public static void writeToFile(String fileName, double s) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write("Your result during game: " + s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



