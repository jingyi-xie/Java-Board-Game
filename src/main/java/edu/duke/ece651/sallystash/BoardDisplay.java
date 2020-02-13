package edu.duke.ece651.sallystash;

import java.util.ArrayList;


public class BoardDisplay {
  final int AS_SELF = 0;
  final int AS_OPP = 1;

  private ArrayList<Board> boards;
  public BoardDisplay(ArrayList<Board> list) {
    // this.board_a = new Board();
    // this.board_b = new Board();
    this.boards = list;
  }
  // public void refresh(ArrayList<Board> list) {
  //   this.board_a = list.get(0);
  //   this.board_b = list.get(1);
  // }

  private void printTwoNum() {
    char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
    System.out.print(col_index);
    System.out.print("                          ");
    System.out.print(col_index);
    System.out.println();
  }
  private void printSingleNum() {
    char[] col_index = {' ', ' ','0', '|', '1', '|', '2', '|', '3', '|', '4', '|', '5', '|', '6', '|', '7', '|', '8', '|', '9', ' ', ' '};
    System.out.println(col_index);
  }
  public void displaySingle(int player_num) {
    System.out.println("-------------------------------------------------------------------------");
    printSingleNum();
    LineDisplay ld = new LineDisplay(boards.get(player_num));
    for (int i = 0; i < 20; i++) {
        ld.displayLine(AS_SELF, i);
        System.out.println();
    }
    printSingleNum();
    System.out.println("-------------------------------------------------------------------------");
  }

  public void displayTwo(int player_num) {
    System.out.println("-------------------------------------------------------------------------");
    printTwoNum();
    LineDisplay ld_a = new LineDisplay(boards.get(player_num));
    LineDisplay ld_b = new LineDisplay(boards.get(1-player_num));
    for (int i = 0; i < 20; i++) {
        ld_a.displayLine(AS_SELF, i);
        System.out.print("                          ");
        ld_b.displayLine(AS_OPP, i);
        System.out.println();
    }
    printTwoNum();
    System.out.println("-------------------------------------------------------------------------");
  }
}
