import java.util.Stack;

public class swap {
     static Stack<Integer> help = new Stack<>();
    static int swap_up(int zero_row, int zero_col, int[][] grid) {
        grid[zero_row][zero_col] = grid[zero_row-1][zero_col];
        grid[zero_row-1][zero_col] = 0;
        if (!help.empty() && help.peek() == 1) {
            help.pop();
        } else {
            help.push(2);
        }
        return zero_row -1;
    }

    static int swap_down(int zero_row, int zero_col, int[][] grid) {
        grid[zero_row][zero_col] = grid[zero_row+1][zero_col];
        grid[zero_row+1][zero_col] = 0;
        if (!help.empty() && help.peek() == 2) {
            help.pop();
        } else {
            help.push(1);
        }
        return zero_row+1;
    }

    static int swap_left(int zero_row, int zero_col, int[][] grid) {
        grid[zero_row][zero_col] = grid[zero_row][zero_col-1];
        grid[zero_row][zero_col-1] = 0;
        if (!help.empty() && help.peek() == 3) {
            help.pop();
        } else {
            help.push(4);
        }
        return zero_col-1;
    }
    static int swap_right(int zero_row, int zero_col, int[][] grid) {
        grid[zero_row][zero_col] = grid[zero_row][zero_col+1];
        grid[zero_row][zero_col+1] = 0;
        if (!help.empty() && help.peek() == 4) {
            help.pop();
        } else {
            help.push(3);
        }
        return zero_col+1;
    }
}
