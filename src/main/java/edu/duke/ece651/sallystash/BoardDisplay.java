package edu.duke.ece651.sallystash;

public class BoardDisplay {
  private Board board;
  public BoardDisplay(Board b) {
      this.board = b;
  }
  public void refresh(Board b) {
      this.board = b;
  }
  public void display() {
    char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
    System.out.println(col_index);
    for (int i = 0; i < 20; i++) {
      int letter = (char)65 + i;
      System.out.print((char)letter + " ");
      for (int j =0; j < 10; j++) {
        System.out.print(board.getCell(i, j).getColor());
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
