package edu.duke.ece651.sallystash;

public class BoardDisplay {
  final int AS_SELF = 0;
  final int AS_OPP = 1;

  private Board board_a;
  private Board board_b;
  public BoardDisplay() {
    this.board_a = new Board();
    this.board_b = new Board();
  }
  public void updateA(Board a) {
    this.board_a = a;
  }
  public void updateB(Board b) {
    this.board_b = b;
  }

  private void printNum() {
    char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
    System.out.print(col_index);
    System.out.print("                          ");
    System.out.print(col_index);
    System.out.println();
  }
 public void display() {
    printNum();
    LineDisplay ld_a = new LineDisplay(board_a);
    LineDisplay ld_b = new LineDisplay(board_b);
    for (int i = 0; i < 20; i++) {
        ld_a.displayLine(AS_SELF, i);
        System.out.print("                          ");
        ld_b.displayLine(AS_OPP, i);
        System.out.println();
    }
    printNum();
  }
}
