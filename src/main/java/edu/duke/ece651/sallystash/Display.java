package edu.duke.ece651.sallystash;

import java.util.ArrayList;


public class Display {
  final int AS_SELF = 0;
  final int AS_OPP = 1;
  final String player[] = {"Player A", "Player B"};

  private ArrayList<Board> boards;

  //Constructor for the display
  public Display(ArrayList<Board> list) {
    this.boards = list;
  }

  //Helper functions to print the numbers and '|'
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

  //Instructions for placing the stash at the start of the game
  public void displayWelcome(int player_num) {
    StringBuilder sb = new StringBuilder();
    sb.append(player[player_num]);
    sb.append(", you are going place Sally’s stash on the board. Make sure ");
    sb.append(player[1 - player_num]);
    sb.append(" isn’t \n");
    sb.append("looking! For each stack, type the coordinate of the upper left side of the stash,\n");
    sb.append("followed by H, V, U, R, D, L. For example, M4H would place a stack horizontally\n");
    sb.append("starting at M4 and going to the right. You have\n");
    sb.append("2 Green stacks that are 1x2\n");
    sb.append("3 Purple stacks that are 1x3\n");
    sb.append("3 Red stacks\n");
    sb.append("3 Blue stacks\n");
    System.out.println(sb.toString());
  }

  //Display the start of the game
  public void displayStart() {
    System.out.println("=========================================================================");
    System.out.println("                               Game begins                               ");
    System.out.println("=========================================================================");
  }

  //Display the input format is invalid
  public void displayInvalidFormat() {
    System.out.println("=========================================================================");
    System.out.println("                       Invalid input format, try again!                  ");
    System.out.println("=========================================================================");
  }

  //Display the location goes out of grid
  public void displayOutOfGrid(boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                     Location out of grid, try again!                    ");
    System.out.println("=========================================================================");
  }

  //Display the stash collide with others
  public void displayCollide(boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                  Collide with another stack, try again!                 ");
    System.out.println("=========================================================================");
  }

  //Display a single board when placing stash at the beginning
  public void displaySingle(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
    printSingleNum();
    LineDisplay ld = new LineDisplay(boards.get(player_num));
    for (int i = 0; i < 20; i++) {
        ld.displayLine(AS_SELF, i);
        System.out.println();
    }
    printSingleNum();
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display two boards: the current player and its opponent
  public void displayTwo(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
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

  //Display the location to dig
  public void displayWhere(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println(player[player_num] + ", where do you want to dig " + player[1 - player_num] + "'s board?");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display the end of the game and the winner
  public void displayWin(int player_num) {
    System.out.println("=========================================================================");
    System.out.println("                              Game Over!                                 ");
    System.out.println("                            " + player[player_num] + " wins!             ");
    System.out.println("=========================================================================");
  }

  //Display a dig is successful
  public void displayHit(boolean isComputer, int player_num) {
    if (isComputer) {
      System.out.println("=========================================================================");
      System.out.println(player[player_num] + " found your stack!");
      System.out.println("=========================================================================");
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                          You found a stack!                             ");
    System.out.println("=========================================================================");
  }

  //Display a dig fails
  public void displayMiss(boolean isComputer, int player_num) {
    if (isComputer) {
      System.out.println("=========================================================================");
      System.out.println(player[player_num] + " missed!");
      System.out.println("=========================================================================");
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                              You missed!                                ");
    System.out.println("=========================================================================");
  }

  //Display the wrong input directions, e.g. "U" for rectangle
  public void displayWrongDir(boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                 Orientation not compatible with the stack!              ");
    System.out.println("=========================================================================");
  }

  //Display the options: D, M, S
  public void displayOptions(int player_num, int move_remain, int sonar_remain, boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println("Possible actions for " + player[player_num]);
    System.out.println();
    System.out.println("D Dig in a square");
    System.out.println("M Move a stack to another square (" + move_remain + " remaining)");
    System.out.println("S Sonar scan (" + sonar_remain + " remaining)");
    System.out.println();
    System.out.println(player[player_num] + ", what would you like to do?");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display which stash to move
  public void displayWhich(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println(player[player_num] + ", which stack do you want to move?");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display where to move the stash
  public void displayWhereTo(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println(player[player_num] + ", where do you want to move the stack to?");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display the move is invalid
  public void displayInvalidMove(boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println("=========================================================================");
    System.out.println("                              Invalid move!                              ");
    System.out.println("=========================================================================");
  }

  //Display the instruction for Sonar
  public void displaySonar(int player_num, boolean isComputer) {
    if (isComputer) {
      return;
    }
    System.out.println(player[player_num] + ", please choose the center coordinate of the sonar scan.");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Display the result of sonar
  public void displaySonarResult(ArrayList<Integer> list, boolean isComputer, int player_num) {
    if (isComputer) {
      System.out.println("=========================================================================");
      System.out.println(player[player_num] + " used a special action!");
      System.out.println("=========================================================================");
      return;
    }
    System.out.println("Green stacks occupy " + list.get(0) + " squares");
    System.out.println("Purple stacks occupy " + list.get(1) + " squares");
    System.out.println("Red stacks occupy " + list.get(2) + " squares");
    System.out.println("Blue stacks occupy " + list.get(3) + " squares");
    System.out.println("-------------------------------------------------------------------------");
  }

  //Desplay that the computer uses a special action
  public void displaySpecial(int player_num) {
    System.out.println("=========================================================================");
    System.out.println(player[player_num] + " used a special action!");
    System.out.println("=========================================================================");
  }

  //Ask if each player is a computer at the beginning
  public void displayIsComputer(int player_num) {
    System.out.println("-------------------------------------------------------------------------");
    System.out.println(player[player_num] + ", are you a computer(Y/N)?");
    System.out.println("-------------------------------------------------------------------------");
  }





}
