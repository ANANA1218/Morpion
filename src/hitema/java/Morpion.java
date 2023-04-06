package hitema.java;

import hitema.java.display.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

import static hitema.java.domain.CheckWinner.checkWinner;
import static hitema.java.modeles.PrintBoard.printBoard;

public class Morpion {

    public static String[] board;
    public static String turn;
    static String player1;
    static String player2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board = new String[9];
            turn = "X";
            String winner = null;

            for (int a = 0; a < 9; a++) {
                board[a] = String.valueOf(a + 1);
            }

            System.out.println("Bienvenue sur le morpion");
            System.out.println("Entrer le Player 1:");
            player1 = in.nextLine();
            System.out.println("Entrer le Player 2:");
            player2 = in.nextLine();
            System.out.println(player1 + " sera le x, et " + player2 + " sera le o.");
            printBoard();

            System.out.println(player1 + " commence. Veuillez saisir un numéro d'emplacement pour placer x:");

            while (winner == null) {
                int numInput;

                // Exception handling.
                // numInput will take input from user like from 1 to 9.
                // If it is not in range from 1 to 9.
                // then it will show you an error "Invalid input."
                try {
                    numInput = in.nextInt();
                    if (!(numInput > 0 && numInput <= 9)) {
                        System.out.println("Invalide ; re-entrer un numéro:");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalide ; re-entrer un numéro:");
                    in.nextLine(); //clear scanner input buffer
                    continue;
                }

                // This game has two player x and O.
                // Here is the logic to decide the turn.
                if (board[numInput - 1].equals(String.valueOf(numInput))) {
                    board[numInput - 1] = turn;

                    if (turn.equals("X")) {
                        turn = "O";
                    } else {
                        turn = "X";
                    }

                    printBoard();
                    winner = checkWinner();
                } else {
                    System.out.println(" emplacement indisponible; re-entrer un numéro:");
                }
            }

            // If no one win or lose from both player x and O.
            // then here is the logic to print "draw".
            if (winner.equalsIgnoreCase("égalité")) {
                System.out.println(" égalité! Merci d'avoir joué.");
            }

            // For winner -to display Congratulations! message.
            else {
                System.out.println("Félicitation! " + winner + " a gagné! Merci d'avoir joué.");
            }


            System.out.println("Voulez-vous jouer à nouveau? (o/n)");
            try {
                String playAgainAnswer = in.next();
                if (playAgainAnswer.equalsIgnoreCase("n")) {
                    playAgain = false;
                } else {
                    playAgain = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalide ; re-entrer votre choix:");
                in.nextLine(); // clear scanner input buffer
                continue;
            }

// clear scanner input buffer
            in.nextLine();

        }

        Display.displayConsole();

        in.close();
    }
}
