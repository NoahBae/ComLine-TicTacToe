package TicTacToe;

import java.util.*;

public class ComLine {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);
        Scanner sc = new Scanner(System.in);
        boolean notDone = true;
            while(notDone == true) {
                System.out.print("Enter where you would like to place your 'x' in positions 1-9");
                int position = sc.nextInt();
                while(playerPositions.contains(position) || cpuPositions.contains(position)) {
                    System.out.println("Position taken. TRY AGAIN!");
                    printGameBoard(gameBoard);
                    position = sc.nextInt();
                }
                posBoard(gameBoard, position, "Player");

                String result = checkWinner();
                if(result.length() > 0) {
                    System.out.println(result);
                    printGameBoard(gameBoard);
                    break;
                }

                Random rand = new Random();
                int cpuPosition = rand.nextInt(9) + 1;
                while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                    cpuPosition = rand.nextInt(9) + 1;
                }
                posBoard(gameBoard, cpuPosition, "CPU");
                printGameBoard(gameBoard);

                result = checkWinner();
                if(result.length() > 0) {
                    System.out.println(result);
                    printGameBoard(gameBoard);
                    break;
                }
            }
    }
    public static void posBoard(char[][] inBoard, int x, String player) {
        char symbol;
        if(player.equals("Player")) {
            symbol = 'X';
            playerPositions.add(x);
        } else {
            symbol = 'O';
            cpuPositions.add(x);
        }
        switch(x) {
            case 1:
                inBoard[0][0] = symbol;
                break;
            case 2:
                inBoard[0][2] = symbol;
                break;
            case 3:
                inBoard[0][4] = symbol;
                break;
            case 4:
                inBoard[2][0] = symbol;
                break;
            case 5:
                inBoard[2][2] = symbol;
                break;
            case 6:
                inBoard[2][4] = symbol;
                break;
            case 7:
                inBoard[4][0] = symbol;
                break;
            case 8:
                inBoard[4][2] = symbol;
                break;
            case 9:
                inBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningCon = new ArrayList<List>();
        winningCon.add(topRow);
        winningCon.add(midRow);
        winningCon.add(botRow);
        winningCon.add(leftCol);
        winningCon.add(midCol);
        winningCon.add(rightCol);
        winningCon.add(cross1);
        winningCon.add(cross2);

        for(List l : winningCon) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you have slain the competition.";
            } else if(cpuPositions.containsAll(l)) {
                return "You lost... Reflect on your incompetence.";
            } else if (playerPositions.size() + cpuPositions.size() == 9 && (!playerPositions.contains(l) || !cpuPositions.contains(l))) {
                return "CAT!";
            }
        }
        return "";
    }
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
