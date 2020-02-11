package edu.duke.ece651.sallystash;
import java.util.Arrays;

public class Board {
  private Cell[][] board;
//   private Cell[][] board_b;
  public Board() {
    board = new Cell[20][10];
    // board_b = new Cell[20][10];
    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 10; j++) {
            board[i][j] = new Cell();
        }
    } 
  }  

  public Cell getCell(int i, int j) {
      return board[i][j];
  }
//   public void printBoard() {
//     char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
//     System.out.println(col_index);
//     for (int i = 0; i < 20; i++) {
//       int letter = (char)65 + i;
//       System.out.print((char)letter + " ");
//       for (int j =0; j < 10; j++) {
//         System.out.print(board_a[i][j].getColor());
//         if (j == 9) {
//             continue;
//         }
//         System.out.print("|");
//       }
//       System.out.println(" " + (char)letter);
//     }
//     System.out.println(col_index);
//   }
}
