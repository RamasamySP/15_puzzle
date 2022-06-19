import java.util.Random;
import java.util.Scanner;
public class shuffle {
    static int shuffle_board (int[][] board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------->> 15 Puzzle <<-------------------\n");
        System.out.println(" Shuffle Settings");
        System.out.println(" 1. Beginner ");
        System.out.println(" 2. Intermediate (Auto Random)");
        System.out.println(" 3. Manual Shuffle ");
        System.out.println(" 4. Exit Shuffle Settings \n");
        System.out.println(" Enter Your choice : ");
        System.out.println();
        int choice = sc.nextInt();
        int zero_row = 3, zero_col = 3;
        switch(choice) {
            case 1 :
                shuffle_easy(zero_row, zero_col, board);
                return choice;
            case 2 :
                System.out.println("Enter Random generation count : ");
                int rand_count = sc.nextInt();
                auto_random(rand_count, board);
                return choice;
            case 3 :
                manual_shuffle(board);
                return choice;
            case 4 :
                return 5;
            default:
                System.out.println(" >>>>>> Invalid Choice <<<<<<");
        }
        return -1;
    }

    private static void manual_shuffle(int[][] board) {
        int zero_row = 3, zero_col = 3;
        System.out.println("Enter Directions to shuffle Randomly : \n(1 - Up, 2-Down, 3-Left, 4-Right, atlast enter a number greater than 4 to terminate)");
        while (true) {
            Scanner sc = new Scanner(System.in);
            int user_in = sc.nextInt();
            if (user_in > 4) {
                break;
            } else {
                if (user_in == 1 && zero_row > 0) {
                    zero_row = swap.swap_up(zero_row, zero_col, board);
                } else if (user_in == 2 && zero_row < board.length-1) {
                    zero_row = swap.swap_down(zero_row, zero_col, board);
                } else if (user_in == 3 && zero_col > 0){
                    zero_col = swap.swap_left(zero_row, zero_col, board);
                } else if (user_in == 4 && zero_col < board.length-1) {
                    zero_col = swap.swap_right(zero_row, zero_col, board);
                }
            }
        }
    }

    private static void auto_random(int rand_count, int[][] board) {
        Random random = new Random();
        int zero_row = 3, zero_col = 3;
        while (rand_count > 0) {
            int dir_count = random.nextInt(4);
            System.out.print(dir_count + " ");
            if (dir_count == 0 && zero_row > 0) {
                zero_row = swap.swap_up(zero_row, zero_col, board);
            } else if (dir_count == 1 && zero_row < board.length-1) {
                zero_row = swap.swap_down(zero_row, zero_col, board);
            } else if (dir_count == 2 && zero_col > 0){
                zero_col = swap.swap_left(zero_row, zero_col, board);
            } else if (dir_count == 3 && zero_col < board.length-1) {
                zero_col = swap.swap_right(zero_row, zero_col, board);
            }
            rand_count--;
        }
    }

    public static void shuffle_easy(int zero_row, int zero_col, int[][] board) {
        for (int shuffle_col = board.length-1; shuffle_col >= 0; shuffle_col--) {
            for (int shuffle_count= board.length; shuffle_count >= 0; shuffle_count--) {
                if (shuffle_col % 2 == 1 && (zero_row > 0)) {
                    zero_row = swap.swap_up(zero_row, zero_col, board);
                } else if (zero_row < board.length-1) {
                    zero_row = swap.swap_down(zero_row, zero_col, board);
                }
            }
            if (zero_col > 0) {
                zero_col = swap.swap_left(zero_row, zero_col, board);
            }
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
