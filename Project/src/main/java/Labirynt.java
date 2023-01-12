import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Generowanie planszy
 *
 * @author Adrian Księżak
 */
public class Labirynt {
    /**
     * Plansza gry
     */
    int[][] lab = new int[13][23];
    /**
     * Liczba wierszy przy generowaniu planszy
     */
    int row = 0;
    /**
     * Liczba kolumn przy generowaniu planszy
     */
    int col = 0;
    /**
     * Łączna liczba wierszy tablicy planszy przy generowaniu planszy
     */
    int rownumber = 13;
    /**
     * Łączna liczba kolumn tablicy planszy przy generowaniu planszy
     */
    int colnumber = 23;
    /**
     * Wymiar grafiki bloku (obiektu po którym można chodzić, zagadki oraz
     * przeszkody)
     */
    int blockwidth = 40;
    /**
     * Wymiar grafiki bloku (obiektu po którym można chodzić, zagadki oraz
     * przeszkody)
     */
    int blockheight = 40;
    /**
     * Obiekty przechowujące grafiki obiektów planszy
     */
    Image zagadka, blok, trawa;
    /**
     * Obiekt przechowujący czcionkę gry
     */
    Font font = new Font("Serif", Font.BOLD, 12);
    /**
     * Tablica przechowująca stan możliwych pól do "obsadzenia" zagadkami
     */
    int[] tab = new int[16];

    /**
     * Konstruktor klasy odpowiadający za aktualizację stanu zagadek na planszy
     *
     * @param riddles
     */
    public Labirynt(int[] riddles) {
        this.tab = riddles;
    }

    /**
     * Metoda odpowiedzialna za rysowanie interaktywnych bloków na planszy oraz wyznaczanie początku i końca gry
     * @param graphics Obiekt odpowiedzialny za rysowanie po planszy
     * @throws IOException Wymagana obsługa wyjątków dla obiektu
     */
    public void paint(Graphics graphics) throws IOException {
        int[][] maze = getMaze(tab);
        blok = ImageIO.read(new File("Resources/blok.png"));
        zagadka = ImageIO.read(new File("Resources/zagadka.png"));
        trawa = ImageIO.read(new File("Resources/trawa.png"));

        for (row = 0; row < rownumber; row++) {
            for (col = 0; col < colnumber; col++) {
                if (maze[row][col] == 1) {
                    if (Game.getLevel() == 1) {
                        graphics.setColor(Color.WHITE);
                        graphics.setFont(font);
                    }
                    if (Game.getLevel() == 2) {
                        graphics.setColor(Color.green);
                    }
                    if (Game.getLevel() >= 3) {
                        graphics.setColor(Color.blue);
                    }

                    graphics.drawImage(blok, col * 40, row * 40, blockwidth, blockheight, null);

                } else if (maze[row][col] == 2) {
                    graphics.drawImage(zagadka, col * 40, row * 40, blockwidth, blockheight, null);
                } else {
                    graphics.drawImage(trawa, col * 40, row * 40, blockwidth, blockheight, null);
                }
            }
        }
        graphics.drawString("Start", 5, 62);
        graphics.drawString("Meta", 850, 462);
    }

    /**
     * Metoda tworząca labirynt w grze
     * @param b tablica przechowująca stan zagadek (rozwiązanych/nierozwiązanych) przez gracza
     * @return Zwracany jest aktualny stan labiryntu
     */
    public int[][] getMaze(int[] b) {
        if (Game.getLevel() == 1) {

            /**
             * Labirynt o wymiarach 13x23 składa się z elementów:
             * "0" - ścieżka/trasa po której bohater się przemieszcza
             * "1" - przeszkoda
             * "2" - zagadka (tu jest określane jako element tablicy b[]
             * indicados por los "0".
             */
            //Level 1
            int maze[][]
                    = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, b[12], 0, 0, 1, 1},
                    {1, 1, b[0], 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, b[1], 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, b[11], 1, 0, 1, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, b[2], 1, 0, 1, b[10], 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, b[13], 1, 0, 1, 1},
                    {1, 1, b[3], 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, b[9], 1, 1, 1, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, b[14], 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, b[8], 1, 0, 1, 1},
                    {1, 1, b[4], 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                    {1, 1, 0, 0, b[5], 0, 0, 0, b[6], 0, 0, 0, 0, 0, 0, b[7], 0, 1, b[15], 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

            lab = maze;
        }

        if (Game.getLevel() == 2) {

            //Level 2
            int maze[][]
                    = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, b[0], 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, b[1], 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, b[2], 1, 0, 0, 0, 0, b[12], 1, b[13], 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, b[14], 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, b[3], 1, b[10], 0, 0, 0, 0, 0, b[11], 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
                    {1, 1, b[4], 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, b[7], 0, 0, b[8], 1, b[9], 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                    {1, 1, b[5], 0, 0, 0, 0, b[6], 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, b[15], 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

            lab = maze;
        }

        if (Game.getLevel() == 3) {

            //Level 3
            int maze[][]
                    = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, b[4], 0, 0, b[8], 0, 0, 0, 0, b[9], 0, 0, 0, 0, 1, 1},
                    {1, 1, 1, b[0], 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, b[1], 0, 1, 0, 0, 0, 0, 0, 0, b[10], 1, 0, 0, 0, 0, b[12], 0, 1, 0, 1, 1},
                    {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
                    {1, 1, 0, 0, 1, 0, 1, 0, 0, b[6], 1, 0, 0, b[13], 0, 0, 0, b[11], 0, 1, 0, 1, 1},
                    {1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
                    {1, 1, 0, 1, 0, 0, 0, 0, 1, b[5], 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, b[14], 1, 1},
                    {1, 1, 0, 1, b[3], 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                    {1, 1, 0, b[2], 0, 0, 0, 0, 1, 0, 0, b[7], 0, 0, 0, 0, 0, 1, b[15], 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

            lab = maze;
        }
        return lab;
    }
}
