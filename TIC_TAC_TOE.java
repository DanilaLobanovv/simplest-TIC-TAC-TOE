import java.util.Scanner;
import java.util.Random;
public class Main {

    public static final char SIGN_X = 'X';
    public static final char SIGN_O = 'O';
    public static final char SIGN_EMPTY = '_';
    public static char[][] field = new char[3][3];

    public static void fillField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = SIGN_EMPTY;
            }
        }
    }

    public static void printField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(field[i][j] + " ");
            System.out.println();
        }
    }

    public static boolean isWin(char dot) {
        for (int i = 0; i < 3; i++) {
            if ((field[i][0] == dot && field[i][1] == dot && field[i][2] == dot) || (field[0][i] == dot && field[1][i] == dot && field[2][i] == dot))
                return true;
            else if ((field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) || (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot))
                return true;
        }
        return false;
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || y >= 3  || x >= 3 || field[x][y] != SIGN_EMPTY)
            return false;
        else
            return true;
    }

    public static boolean isFieldFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (field[i][j] == SIGN_EMPTY)
                    return false;
        return true;
    }

    public static void turnHuman() {
        int x, y;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите координеаты Х и У (1,2,3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isValid(x, y));
        field[x][y] = SIGN_X;
    }

    public static void turnRobot() {
        int x, y;
        System.out.println("Ход робота");
        Random random = new Random();
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isValid(x, y));
        field[x][y] = SIGN_O;
    }

    public static void main(String[] args) {
        fillField();
        while (true) {
            printField();
            turnHuman();
            if (isWin(SIGN_X)) {
                System.out.println("Мальчик победил");
                break;
            } else if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            } else {
                printField();
                turnRobot();
                if (isWin(SIGN_O)) {
                    System.out.println("Робот победил");
                    break;
                } else if (isFieldFull()) {
                    System.out.println("Ничья");
                    break;
                }
            }
        }
    }
}