import java.util.Random;
import java.util.Scanner;

public class RpsGame {
    private final Scanner scanner;
    private final Random random;
    private final String[] moves = {"kamień", "papier", "nożyce"};
    private final int roundsToWin;
    private int playerWins;
    private int computerWins;

    public RpsGame(int roundsToWin) {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.roundsToWin = roundsToWin;
        this.playerWins = 0;
        this.computerWins = 0;
    }

    public void startGame() {
        System.out.println("Witaj w grze Kamień, Papier, Nożyce!");
        System.out.println("Podaj swoje imię:");
        String playerName = scanner.nextLine();
        System.out.println("Podaj liczbę rund potrzebną do wygranej:");
        System.out.println("Instrukcja:");
        System.out.println("klawisz 1 – zagranie \"kamień\"");
        System.out.println("klawisz 2 – zagranie \"papier\"");
        System.out.println("klawisz 3 – zagranie \"nożyce\"");
        System.out.println("klawisz x – zakończenie gry");
        System.out.println("klawisz n – rozpoczęcie nowej gry");

        boolean end = false;
        while (!end) {
            System.out.println("Runda: " + (playerWins + computerWins + 1));
            System.out.println("Twój ruch (" + playerName + "):");
            int playerMove = Integer.parseInt(scanner.nextLine());
            int computerMove = random.nextInt(3) + 1;
            System.out.println("Ruch komputera: " + moves[computerMove - 1]);
            determineWinner(playerMove, computerMove);
            System.out.println("Aktualny wynik:");
            System.out.println(playerName + ": " + playerWins);
            System.out.println("Komputer: " + computerWins);
            if (playerWins == roundsToWin || computerWins == roundsToWin) {
                endGame(playerName);
                System.out.println("Czy chcesz zakończyć grę? (x - zakończ / n - nowa gra)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("x")) {
                    end = true;
                } else if (choice.equalsIgnoreCase("n")) {
                    resetGame();
                }
            }
        }
        scanner.close();
    }

    private void determineWinner(int playerMove, int computerMove) {
        if (playerMove == computerMove) {
            System.out.println("Remis!");
        } else if ((playerMove == 1 && computerMove == 3) ||
                (playerMove == 2 && computerMove == 1) ||
                (playerMove == 3 && computerMove == 2)) {
            System.out.println("Gracz wygrywa!");
            playerWins++;
        } else {
            System.out.println("Komputer wygrywa!");
            computerWins++;
        }
    }

    private void endGame(String playerName) {
        if (playerWins > computerWins) {
            System.out.println("Gratulacje " + playerName + "! Wygrałeś grę!");
        } else {
            System.out.println("Komputer wygrał grę. Spróbuj ponownie!");
        }
    }

    private void resetGame() {
        playerWins = 0;
        computerWins = 0;
        System.out.println("Rozpoczynamy nową grę!");
    }

    public static void main(String[] args) {
        RpsGame game = new RpsGame(3); // Ustawienie liczby rund potrzebnych do wygranej
        game.startGame();
    }
}