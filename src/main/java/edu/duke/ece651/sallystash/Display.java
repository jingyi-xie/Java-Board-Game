package edu.duke.ece651.sallystash;

import java.util.ArrayList;


public class Display {
  final int AS_SELF = 0;
  final int AS_OPP = 1;
  final String player[] = {"Player A", "Player B"};

  private ArrayList<Board> boards;
  public Display(ArrayList<Board> list) {
    this.boards = list;
  }

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

  public void displayWelcome(int player_num) {
    StringBuilder sb = new StringBuilder();
    sb.append(player[player_num]);
    sb.append(", you are going place Sally’s stash on the board. Make sure ");
    sb.append(player[1 - player_num]);
    sb.append(" isn’t \n");
    sb.append("looking! For each stack, type the coordinate of the upper left side of the stash,\n");
    sb.append("followed by either H (for horizontal) or V (for vertical). For example, M4H would \n");
    sb.append("place a stack horizontally starting at M4 and going to the right. You have\n");
    sb.append("2 Green stacks that are 1x2\n");
    sb.append("3 Purple stacks that are 1x3\n");
    sb.append("3 Red stacks that are 1x4\n");
    sb.append("3 Blue stacks that are 1x6\n");
    System.out.println(sb.toString());
  }

  public void displayStart() {
    System.out.println("=========================================================================");
    System.out.println("                               Game begins                               ");
    System.out.println("=========================================================================");
  }

  public void displayInvalidFormat() {
    System.out.println("=========================================================================");
    System.out.println("                       Invalid input format, try again!                  ");
    System.out.println("=========================================================================");
  }

  public void displayOutOfGrid() {
    System.out.println("=========================================================================");
    System.out.println("                     Location out of grid, try again!                    ");
    System.out.println("=========================================================================");
  }

  public void displayCollide() {
    System.out.println("=========================================================================");
    System.out.println("                  Collide with another stack, try again!                 ");
    System.out.println("=========================================================================");
  }

  public void displaySingle(int player_num) {
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
    System.out.println(player[player_num] + "'s turn:");
    System.out.print("      Your tree                ");
    System.out.print("                      ");
    System.out.print(player[1 - player_num] + "'s tree\n");
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

  public void displayWhere(int player_num) {
    System.out.println(player[player_num] + ", where do you want to dig " + player[1 - player_num] + "'s board?");
    System.out.println("-------------------------------------------------------------------------");
  }
  public void displayWin(int player_num) {
    System.out.println("=========================================================================");
    System.out.println("                              Game Over!                                 ");
    System.out.println("                            " + player[player_num] + " wins!             ");
    System.out.println("=========================================================================");
  }

  public void displayHit() {
    System.out.println("=========================================================================");
    System.out.println("                          You found a stack!                             ");
    System.out.println("=========================================================================");
  }

  public void displayMiss() {
    System.out.println("=========================================================================");
    System.out.println("                              You missed!                                ");
    System.out.println("=========================================================================");
  }

  public void displayWrongDir() {
    System.out.println("=========================================================================");
    System.out.println("                 Orientation not compatible with the stack!              ");
    System.out.println("=========================================================================");
  }

  public void displayOptions(int player_num, int move_remain, int sonar_remain) {
    System.out.println("Possible actions for " + player[player_num]);
    System.out.println();
    System.out.println("D Dig in a square");
    System.out.println("M Move a stack to another square (" + move_remain + " remaining)");
    System.out.println("S Sonar scan (" + sonar_remain + " remaining)");
    System.out.println();
    System.out.println(player[player_num] + ", what would you like to do?");
    System.out.println("-------------------------------------------------------------------------");
  }

  public void displayWhich(int player_num) {
    System.out.println(player[player_num] + ", which stack do you want to move?");
    System.out.println("-------------------------------------------------------------------------");
  }

  public void displayWhereTo(int player_num) {
    System.out.println(player[player_num] + ", where do you want to move the stack to?");
    System.out.println("-------------------------------------------------------------------------");
  }

  public void displayInvalidMove() {
    System.out.println("=========================================================================");
    System.out.println("                              Invalid move!                              ");
    System.out.println("=========================================================================");
  }

  public void displaySonar(int player_num) {
    System.out.println(player[player_num] + ", please choose the center coordinate of the sonar scan.");
    System.out.println("-------------------------------------------------------------------------");
  }

  public void displaySonarResult(ArrayList<Integer> list) {
    System.out.println("Green stacks occupy " + list.get(0) + " squares");
    System.out.println("Purple stacks occupy " + list.get(1) + " squares");
    System.out.println("Red stacks occupy " + list.get(2) + " squares");
    System.out.println("Blue stacks occupy " + list.get(3) + " squares");
    System.out.println("-------------------------------------------------------------------------");
  }





}
