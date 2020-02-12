package edu.duke.ece651.sallystash;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  final int BOARD_A = 0;
  final int BOARD_B = 1;
  final int PLAYER_A = 0;
  final int PLAYER_B = 1;

  private ArrayList<Board> boards;

  Board board_b;
  int curId;
  public Game() {
    this.boards = new ArrayList<>();
    boards.add(new Board());
    boards.add(new Board());
    this.curId = 1;
  }
  public void placeStash(int player_num, int board_num, String color, int times) {
    String num[] = {"first", "second", "third"};
    String player[] = {"Player A", "Player B"};
    char color_draw;
    if (color == "Green") {
      color_draw = 'G';
    }
    else if (color == "Purple") {
      color_draw = 'P';
    }
    else if (color == "Red") {
      color_draw = 'R';
    }
    else if (color == "Blue") {
      color_draw = 'B';
    }
    else {
      color_draw = ' ';
    }
    for (int i = 0; i < times; i++) {
      System.out.println(player[player_num] + ", where do you want to place the " + num[i] + " " + color + " stash?");
      Scanner scan_i = new Scanner(System.in);
      System.out.println("Enter i");
      int row = -1, col = -1;
      if(scan_i.hasNextInt()) {
        row = scan_i.nextInt();
      }
      Scanner scan_j = new Scanner(System.in);
      System.out.println("Enter j");
      if(scan_j.hasNextInt()) {
        col = scan_j.nextInt();
      }
      Rectangle rec = new Rectangle(this.curId, color_draw, 1, 2);
      rec.putOnBoard(row, col, boards.get(player_num));
      scan_i.close();
      scan_j.close();
    }

  }
  public void initialize() {
    BoardDisplay bdis = new BoardDisplay();
    bdis.display();
    placeStash(PLAYER_A, BOARD_A, "Green", 2);
    placeStash(PLAYER_A, BOARD_A, "Purple", 3);
    placeStash(PLAYER_A, BOARD_A, "Red", 3);
    placeStash(PLAYER_A, BOARD_A, "Blue", 2);
    //bdis.refresh(this.boards);
    bdis.display();
  }

}
