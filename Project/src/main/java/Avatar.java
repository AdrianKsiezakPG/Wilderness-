import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Tworzenie i poruszanie postacią w grze
 *
 * @author Adrian Księżak
 */
public class Avatar {

    /**
     * Punkty w grze
     */
    int points = 0;
    /**
     * Liczba zyc gracza
     */
    public int life = 3;
    /**
     * Tablica przechowująca możliwe położenie zagadek
     */
    int[] mysteryField = new int[16];
    /**
     * Zmienna okreslająca początek poziomu
     */
    boolean start = false;
    /**
     * Dziedziczenie klasy odpowiadajacej za generowanie planszy
     */
    Labirynt lab = new Labirynt(mysteryField);
    /**
     * Zmienna przechowujaca polozenie gracza w plaszczyznie poziomej
     */
    int x = 40;
    /**
     * Zmienna przechowująca położenie gracza w plaszczyznie pionowej
     */
    int y = 40;
    /**
     * Zmienna określająca szerokość obiektów (grafik) na planszy
     */
    int width = 40;
    /**
     * Zmienna określająca wysokość obiektów (grafik) na planszy
     */
    int height = 40;
    /**
     * Zmienna określająca ruch gracza na planszy
     */
    int movement = 40;
    /**
     * Obiekt przechowujący grafikę gracza
     */
    Image boy;

    /**
     * Tablica liczb odpowiadająca za wylosowane i umiejscowione na planszy
     * elementy
     */
    int[] randomMystery = new int[5];
    /**
     * Tablica obiektów przechowujących grafikę reprezentującą zycia bohatera
     */
    Image[] PNG_life = new Image[3];
    /**
     * Tablica łancuchów znaków z pytaniami zadawanymi podczas interakcji z
     * zagadkami
     */
    String[] pytania
            = {"Ile miesiecy ma rok?	\nA. 9 B. 11 C. 12",
            "Ile lat to pelnoletniosc wedug polskiego prawa?	\nA. 19 B. 18 C. 20",
            "W ktorym roku miala miejsce bitwa pod Grunwaldem?	\nA. 1410 B. 1999 C. 1236",
            "W ktorym roku mial miejsce chrzest Polski?	\nA. 1000 B. 1100 C. 966",
            "Ile sztuk to kopa?	\nA. 9 B. 60 C. 62",
            "Jak ma na imie prezydent Polski?	\nA. Andrzej B. Wojciech C. Adam",
            "Czy Adam i Andrzej to to samo imie?	\nA. Nie B. Moze C. Tak",
            "Autorem piosenki 'Parostatek' byl?	\nA. Krzysztof Krawczyk B. Pezet C. Feel",
            "Slynnym polskim skoczkiem byl?	\nA. Adam Kowalski B. Adam Nawalka C. Adam Malysz",
            "Metr to ile centymetrow?	\nA. 10 B. 100 C. 1000",
            "Ktory polak dostal pokojowa nagrode Nobla?	\nA. Lech Walesa B. Jaroslaw Kaczynski C. Krzysztof Cugowski",
            "Ile jest podstawowych kolorow ?	\nA. 9 B. 5 C. 3",
            "(2+2)*5 daje wynik ?	\nA. 20 B. 60 C. 62",
            "Stolica Polski jest?	\nA. Radom B. Gdansk C. Warszawa",
            "Ktora placowka szkolna juz nie istnieje?	\nA. Gimnazjum B. Politechnika C. Szkola podstawowa",
            "Ile sztuk to mendel?	\nA. 9 B. 60 C. 15",
            "Ktory mamy dzis rok?	\nA. 2022 B. 2023 C. 2024",
            "Autorem lektory 'Dziady' jest ?	\nA. Adam Mickiewicz B. Jan Kochanowski C. Edyta Bartosiewicz",};
    /**
     * Tablica przechowująca poprawne odpowiedzi do pytań
     */
    char[] answers = {'C', 'B', 'A', 'C', 'B', 'A', 'A', 'A', 'C', 'B', 'A', 'C', 'A', 'C', 'A', 'C', 'B', 'A'};
    /**
     * Zmienna przechowująca odpowiedź gracza na pytanie z zagadki
     */
    char odpPlayer = ' ';
    /**
     * Liczba zagadek przez które gracz przeszedł
     */
    int foundMystery = 0;
    /**
     * Wiadomość tekstowa przy oknie dialogowym zagadek
     */
    String window;


    /**
     * Metoda odpowiedzialna za przemieszczanie postaci sterowanej przez gracza
     *
     * @param graphics Obiekt odpowiedzialny za rysowanie po planszy
     * @param player   Zmienna przechowująca aktualną grafikę postaci
     * @throws IOException Wymagana obsługa wyjątków dla obiektu
     */
    public void paint(Graphics graphics, String player) throws IOException {
        boy = ImageIO.read(new File(player));
        graphics.drawImage(boy, x, y, width, height, null);
        graphics.drawString("Punkty: " + points, 20, 500);
        drawLife(graphics);
    }

    /**
     * Metoda odpowiedzialna za rysowanie grafiki prezentującej liczbę żyć gracza
     *
     * @param graphics Obiekt odpowiedzialny za rysowanie po planszy
     * @throws IOException Wymagana obsługa wyjątków dla obiektu
     */
    public void drawLife(Graphics graphics) throws IOException {
        for (int z = 0; z < life; z++) {
            PNG_life[z] = ImageIO.read(new File("Resources/zycie.png"));
            graphics.drawImage(PNG_life[z], 750 + (z * 45), 0, width, height, null);

            if (life == 2) {
                PNG_life[2].getScaledInstance(1, 1, 0);
            }
        }
    }

    /**
     * Metoda wywołana w konstruktorze klasy głównej, odpowiedzialna za sterowanie postacią, określająca zmianę poziomu oraz czas losowania zagadek
     *
     * @param event Zdarzenie polegające na wciśnięciu klawisza
     */
    public void keyPressed(KeyEvent event) {
        int[][] maze = lab.getMaze(mysteryField);

        //Przesuń w lewo
        if (event.getKeyCode() == 37) {
            if (maze[y / 40][(x / 40) - 1] != 1) {
                x = x - movement;
            }
        }
        //Przesuń w prawo
        if (event.getKeyCode() == 39) {
            if (maze[y / 40][(x / 40) + 1] != 1) {
                x = x + movement;
            }
        }
        //Przesun w dół
        if (event.getKeyCode() == 40) {
            if (maze[(y / 40) + 1][x / 40] != 1) {
                y = y + movement;
            }
        }
        //Przesun w górę
        if (event.getKeyCode() == 38) {
            if (maze[(y / 40) - 1][x / 40] != 1) {
                y = y - movement;
            }
        }

        /**
         * Kolizja bohatera z zagadką - następuje wyświetlenie okna dialogowego w celu uzyskania odpowiedzi na pytanie
         */
        if (maze[y / 40][x / 40] == 2) {
            int losuj = randomNumber(0, 18);
            window = JOptionPane.showInputDialog(null, pytania[losuj]);
            odpPlayer = window.charAt(0);
            if (odpPlayer == answers[losuj]) {

                mysteryField[randomMystery[foundMystery]] = 0;
                JOptionPane.showMessageDialog(null, "Dobrze!");
                points += 15;

            } else {
                mysteryField[randomMystery[foundMystery]] = 0;
                JOptionPane.showMessageDialog(null, "Źle!");
                points -= 5;
                life--;
            }
            foundMystery++;
        }

        /**
         * Gdy gracz dostanie się do mety następuje zmiana poziomu na kolejny
         */
        if (x == 840 && y == 440) {
            nextLevel();
        }

        /**
         * Losowanie zagadek na starcie poziomu
         */
        if (x == 80 && y == 40 && start == false) {
            randomMystery();
        }
    }

    /**
     * Metoda losująca liczbę z zakresu, używana do wylosowania pseudolosowej pozycji zagadki na planszy
     *
     * @param min Zakres od jakiego ma losować (włącznie)
     * @param max Zakres do jakiego ma losować (nie włączając)
     * @return Zwracana jest losowa liczba z podanego zakresu
     */
    public int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Metoda, która po dotarciu przez gracza do Mety następuje wygenerowanie następnego poziomu (zmiana planszy i przywrócenie niektórych zmiennych do stanu początkowego)
     */
    public void nextLevel() {
        Game.changeLevel();
        x = 40;
        y = 40;
        start = false;
        for (int i = 0; i < 16; i++) {
            mysteryField[i] = 0;
        }
        foundMystery = 0;
    }

    /**
     * Metoda odpowiedzialna za losowanie miejsce na planszy, w którym będzie umieszczona zagadka
     */
    public void randomMystery() {
        System.out.println("Poczatek");
        for (int i = 0; i < 16; i++) {
            mysteryField[i] = 0;
        }
        start = true;
        int licznik = 0, liczba = 2;
        int rand;
        for (int i = 0; i < Game.level + 2; i++) {
            while (mysteryField[liczba] == 2) {
                rand = randomNumber(0, 15);
                liczba = rand;
            }
            mysteryField[liczba] = 2;
            randomMystery[licznik] = liczba;
            licznik++;
        }
        randomMystery = sortuj(randomMystery);
    }

    /**
     * Metoda wykorzystująca sortowanie bąbelkowe do posortowania tablicy określającej kolejność występowania zagadek
     *
     * @param tab
     * @return
     */
    public int[] sortuj(int[] tab) {
        int i, j, temp, f;
        for (i = 0; i < Game.level + 1; i++) {
            for (j = 0; j < Game.level + 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                }
            }
        }
        return tab;
    }

    /**
     * Metoda zwracająca aktualny stan tablicy przechowującej mapę gry
     *
     * @return Mapa gry
     */
    int[] getMysteryField() {
        return mysteryField;
    }

    /**
     * Metoda zwracająca aktualny stan punktów gracza
     *
     * @return Liczba punktów gracza
     */
    int getPoints() {
        return points;
    }

    /**
     * Metoda aktualizująca liczbę punktow gracza na aktualną (np. po zakupach w trakcie gry)
     *
     * @param punkt Liczba punktów pozostałych graczowi
     */
    void setPoints(int punkt) {
        points = punkt;
    }

    /**
     * Metoda zwracająca aktualną liczbę żyć gracza
     *
     * @return Aktualna liczba żyć gracza
     */
    int getLife() {
        return life;
    }

    /**
     * Metoda aktualizująca liczbę żyć gracza na aktualną (np. po zakupie żyć w trakcie gry lub straceniu życia)
     *
     * @param life Aktualna liczba żyć gracza
     */
    void setLife(int life) {
        life = life;
    }

}   
