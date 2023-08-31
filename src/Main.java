/*
* Чтобы сделать ход, нужно написать два числа через пробел - координаты клетки.
* Первыми ходят крестики.
* Пример:
* ---------
* | _ _ _ |
* | _ _ _ |
* | _ _ _ |
* ---------
* 2 2
* ---------
* | _ _ _ |
* | _ X _ |
* | _ _ _ |
* ---------
* 1 2
* ---------
* | _ O _ |
* | _ X _ |
* | _ _ _ |
* ---------
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] gameField = {
                {'_', '_', '_' },
                {'_', '_', '_' },
                {'_', '_', '_' }

        };
        print(gameField);
        Scanner scanner = new Scanner(System.in);
        String coordinates;

        int coordinateOne;
        int coordinateTwo;
        int count_ = 9;
        boolean gameNotOver = true;

        while (gameNotOver) {

            coordinates = checkAndSaveCoordinate(scanner, gameField);
            coordinateOne = Character.getNumericValue(coordinates.charAt(0));
            coordinateTwo = Character.getNumericValue(coordinates.charAt(1));

            if (count_ % 2 == 0) {
                makeMove(coordinateOne, coordinateTwo, gameField, 'O');
            } else {
                makeMove(coordinateOne, coordinateTwo, gameField, 'X');
            }
            count_--;
            print(gameField);
            gameNotOver = checkStatus(count_, gameField);
        }
        scanner.close();
    }

    static void print(char[][] gameField) {
        System.out.println("---------");
        System.out.println("| " + gameField[0][0] + " " + gameField[0][1] + " " + gameField[0][2] + " |");
        System.out.println("| " + gameField[1][0] + " " + gameField[1][1] + " " + gameField[1][2] + " |");
        System.out.println("| " + gameField[2][0] + " " + gameField[2][1] + " " + gameField[2][2] + " |");
        System.out.println("---------");

    }

    static boolean checkWin(char[][] gameField, char symbol) {
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][0] == symbol && gameField[i][0] == gameField[i][1] && gameField[i][2] == gameField[i][1] ||
                    gameField[0][i] == symbol && gameField[0][i] == gameField[1][i] && gameField[2][i] == gameField[1][i])
                return true;
        }
        if (gameField[0][2] == symbol && gameField[0][2] == gameField[1][1] && gameField[1][1] == gameField[2][0] ||
                gameField[0][0] == symbol && gameField[0][0] == gameField[1][1] && gameField[1][1] == gameField[2][2])
            return true;
        return false;
    }

    static boolean checkStatus(int count_, char[][] gameField) {
        boolean winX = checkWin(gameField, 'X');
        boolean winO = checkWin(gameField, 'O');

        if (winX && !winO) {
            System.out.println("X победили.");
            return false;
        }
        if (winO && !winX) {
            System.out.println("O победили.");
            return false;
        }
        if (!winO && !winX && count_ == 0) {
            System.out.println("Ничья.");
            return false;
        }
        return true;
    }

    static String checkAndSaveCoordinate(Scanner scanner, char[][] gameField) {
        int coordinateOne = 0;
        int coordinateTwo = 0;

        boolean indicator = true;
        while (indicator) {
            if (scanner.hasNextInt()) {
                coordinateOne = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    coordinateTwo = scanner.nextInt();
                    if (coordinateOne <= 3 && coordinateOne > 0 && coordinateTwo <= 3 && coordinateTwo > 0) {
                        if (gameField[coordinateOne - 1][coordinateTwo - 1] == '_') {
                            indicator = false;
                            scanner.nextLine();
                        } else {
                            indicator = true;
                            scanner.nextLine();
                            System.out.println("Эта клетка занята. Выберите другую.");
                        }
                    } else {
                        scanner.nextLine();
                        System.out.println("Координаты должны быть от 1 до 3.");
                        indicator = true;
                    }
                } else {
                    System.out.println("Вы должны ввести число.");
                    scanner.nextLine();
                    indicator = true;
                }
            } else {
                System.out.println("Вы должны ввести число.");
                scanner.nextLine();
                indicator = true;
            }
        }
        return coordinateOne + Integer.toString(coordinateTwo);
    }

    static void makeMove(int coordinateOne, int coordinateTwo, char[][] gameField, char player) {
        gameField[coordinateOne - 1][coordinateTwo - 1] = player;
    }
}