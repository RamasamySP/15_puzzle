import java.util.*;
public class menu {
    static int game_menu(int best_score, int[][] board) {
        int shuffle_flag = 5;
        Scanner sc = new Scanner(System.in);
        clearScreen();
        System.out.println("-------------->> 15 Puzzle <<-------------------\n");
        System.out.println(" Game Menu : ");
        System.out.println("\t1. New Game");
        System.out.println("\t2. Shuffle");
        System.out.print("\t3. Best Score : ");//need to add best score
        if (best_score == 999) {
            System.out.println("--");
        } else  {
            System.out.println(best_score);
        }
        System.out.println("\t4. Exit \n");
        System.out.println(" Enter Your Choice : ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 :
                //goes back to main method execution
                return shuffle_flag;
            case 2 :
                clearScreen();
                shuffle_flag = shuffle.shuffle_board(board);
                return shuffle_flag;
            case 3:
                System.out.println("-------------->> 15 Puzzle <<-------------------\n");
                System.out.println(" Score Settings : ");
                System.out.println("\t 1. Reset Score");
                System.out.println("\t 2. Exit Game");
                System.out.println("Enter Your choice : ");
                if (sc.nextInt() == 1) {
                    best_score = 999;
                    menu.game_menu(best_score, board);
                } else {
                    System.exit(0);
                }
                break;
            case 4 :
                System.out.println(" Thank You !");
                sc.close();
                System.exit(0);
            default:
                System.out.println(">>>>> Invalid Choice <<<<< ");
        }
        return -1;
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
