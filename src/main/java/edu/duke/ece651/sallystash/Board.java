package edu.duke.ece651.sallystash;
import java.util.Arrays;

public class Board {
  private char[][] board_a;
  private char[][] board_b;
  public Board() {
    board_a = new char[20][10];
    board_b = new char[20][10];
    for(char[] row_a : board_a) {
      Arrays.fill(row_a, ' ');
    }
    for(char[] row_b : board_b) {
      Arrays.fill(row_b, ' ');
    }
  }  
  public void printBoard() {
    char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
    System.out.println(col_index);
    for (int i = 0; i < 20; i++) {
      int letter = (char)65 + i;
      System.out.print((char)letter + " ");
      for (int j =0; j < 10; j++) {
        System.out.print(board_a[i][j]);
        if (j == 9) {
            continue;
        }
        System.out.print("|");
      }
      System.out.println(" " + (char)letter);
    }
    System.out.println(col_index);
  }
}
