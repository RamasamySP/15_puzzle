import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class puzzle {
    public static void main(String[] args) {
        int best_score = 999;
        int n = 4;
        int[][] grid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},  {13, 14, 15, 0}};
        int[][] reset = new int[n][n];
        int zero_row = 3;
        int zero_col = 3;
        int moves = 0;
        //Stack<Integer> help = new Stack<>();
        int shuffle_flag = menu.game_menu(best_score, grid);
        if (shuffle_flag == 5) {
            shuffle.shuffle_easy(zero_row, zero_col, grid);
        }
        if (shuffle_flag == 1 || shuffle_flag == 5) {
            zero_row = 3;
            zero_col = 0;
            zero_col = swap.swap_right(zero_row, zero_col, grid);
            zero_row = swap.swap_up(zero_row, zero_col, grid);
            Random random = new Random();
            int rand_count = 4;
            while (rand_count > 0) {
                int dir_count = random.nextInt(4);
                if (dir_count == 0 && zero_row > 0) {
                    zero_row = swap.swap_up(zero_row, zero_col, grid);
                } else if (dir_count == 1 && zero_row < n-1) {
                    zero_row = swap.swap_down(zero_row, zero_col, grid);
                } else if (dir_count == 2 && zero_col > 0){
                    zero_col = swap.swap_left(zero_row, zero_col, grid);
                } else if (dir_count == 3 && zero_col < n-1) {
                    zero_col = swap.swap_right(zero_row, zero_col, grid);
                }
                rand_count--;
            }
        }
        if (shuffle_flag == 2 || shuffle_flag == 3) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid.length; col++) {
                    if (grid[row][col] == 0) {
                        zero_row = row;
                        zero_col = col;
                        break;
                    }
                }
            }
        }
        int reset_zrow = zero_row, reset_zcol = zero_col;
        for (int reset_row = 0; reset_row < reset.length; reset_row++) {
            for (int reset_col = 0; reset_col < reset.length; reset_col++) {
                reset[reset_row][reset_col] = grid[reset_row][reset_col];
            }
        }

        Stack<Integer> undo_stack = new Stack<>();
        String out = " ";
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            clearScreen();
            //System.out.println(swap.help);
            System.out.println("------------ 15 Puzzle ----------------\n");
            System.out.println(" Press numbers to select the direction of 0 \n");
            System.out.println("                                 Moves : " + moves);
            System.out.println(" 1. Up    \t 2. Down ");
            System.out.println(" 3. Left  \t 4. Right");
            System.out.println(" 5. Reset \t 6. Undo ");
            System.out.println(" 7. Help  \t 8. Exit ");
            System.out.println(out);
            out = "";
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] < 10) {
                        System.out.print("  " + grid[row][col] + " ");
                    } else {
                        System.out.print(" " + grid[row][col] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("\n Enter your choice : ");
            int innerchoice = sc.nextInt();
            switch(innerchoice) {
                case 1:
                    if (zero_row > 0) {
                        zero_row = swap.swap_up(zero_row, zero_col, grid);
                        undo_stack.push(2);
                        moves++;
                    }
                    break;
                case 2 :
                    if (zero_row < n-1) {
                        zero_row = swap.swap_down(zero_row, zero_col, grid);
                        undo_stack.push(1);
                        moves++;
                    }
                    break;
                case 3 :
                    if (zero_col > 0) {
                        zero_col = swap.swap_left(zero_row, zero_col, grid);
                        undo_stack.push(4);
                        moves++;
                    }
                    break;
                case 4 :
                    if (zero_col < n-1) {
                        zero_col = swap.swap_right(zero_row, zero_col, grid);
                        undo_stack.push(3);
                        moves++;
                    }
                    break;
                case 5 :
                    for (int row = 0; row < grid.length; row++) {
                        for (int col = 0; col < grid.length; col++) {
                            grid[row][col] = reset[row][col];
                        }
                    }
                    zero_row = reset_zrow;
                    zero_col = reset_zcol;
                    moves = 0;
                    break;
                case 6 :
                    if (undo_stack.empty()) {
                        out = " No more moves to undo";
                    } else {
                        moves--;
                        int temp = undo_stack.pop();
                        if (temp == 1) {
                            zero_row = swap.swap_up(zero_row, zero_col, grid);
                            out = " Undo Completed - Last move : down";
                        } else if (temp == 2) {
                            zero_row = swap.swap_down(zero_row, zero_col, grid);
                            out = "Undo Completed - Last move : up";
                        } else if (temp == 3) {
                            zero_col = swap.swap_left(zero_row, zero_col, grid);
                            out = "Undo Completed - Last move : right";
                        } else {
                            zero_col =swap.swap_right(zero_row, zero_col, grid);
                            out = "Undo Completed - Last move : left";
                        }
                    }
                    break;
                case 7 :
                    out = " Help : " + Integer.toString(swap.help.peek()).charAt(0);
                    break;
                case 8 :
                    loop = false;
                    System.out.println(" Thank You !");
                    sc.close();
                    break;
                default :
                    System.out.println(">>>>> Invalid Input! Try Again! <<<<<");
            }
            if (zero_row == n-1 && zero_col == n-1) {
                if (terminate_condition(grid)) {
                    clearScreen();
                    System.out.println("------------ 15 Puzzle ----------------\n");
                    print_matrix(grid);
                    System.out.println("   *****  You Won! :)   *****   \n");
                    System.out.println("\t 1 . Menu");
                    System.out.println("\t 2. Exit");
                    System.out.println("Enter Your Choice : ");
                    if (sc.nextInt() == 1) {
                        best_score = Math.min(moves, best_score);
                        puzzle.main(null);
                    } else {
                        System.exit(0);
                    }
                    loop = false;
                }
            }
        }
    }

    private static boolean terminate_condition(int[][] grid) {
        int count = 1;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] == count) {
                    count++;
                }
            }
        }
        return (count == grid.length*grid.length);
    }

    public static void print_matrix(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] < 10) {
                    System.out.print("  " + grid[row][col] + " ");
                } else {
                    System.out.print(" " + grid[row][col] + " ");
                }
            }
            System.out.println();
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
