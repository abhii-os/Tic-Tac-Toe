import java.util.Scanner;

public class TicTacToe {
    static char[][] board = { {'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'} };
    static char currentPlayer = 'X'; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String playAgain;

        // The game continues until the player says no
        do {
            resetBoard();
            playGame(sc); 
            System.out.println("Play again? (yes/no): ");
            playAgain = sc.next(); // Ask if players want to play another round
        } while (playAgain.equalsIgnoreCase("yes")); 

        sc.close(); 
    }

    // Method that controls the game
    static void playGame(Scanner sc) {
        boolean gameWon = false; 

        for (int turn = 0; turn < 9 && !gameWon; turn++) {
            printBoard(); 
            playerMove(sc); 
            gameWon = checkWin(); 

            if (gameWon) {
                printBoard(); 
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        if (!gameWon) {
            printBoard();
            System.out.println("It's a draw!");
        }
    }

    // Method to handle a player's move
    static void playerMove(Scanner sc) {
        int move;
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter a number (1-9): ");
            move = sc.nextInt(); 
            // Check if the move is valid
            if (move >= 1 && move <= 9 && isValidMove(move)) {
                updateBoard(move); 
                break; 
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Method to check if the move is valid (not already taken)
    static boolean isValidMove(int move) {
        return board[(move - 1) / 3][(move - 1) % 3] == (char) (move + '0');
    }

    // Method to update the board with the player's move
    static void updateBoard(int move) {
        board[(move - 1) / 3][(move - 1) % 3] = currentPlayer;
    }

    // Method to check if the current player has won
    static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true; 
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }


    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Method to reset the board for a new game
    static void resetBoard() {
        currentPlayer = 'X'; 
        char num = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = num++; 
            }
        }
    }
}
