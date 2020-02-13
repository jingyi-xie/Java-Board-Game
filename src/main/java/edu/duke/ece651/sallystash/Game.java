package edu.duke.ece651.sallystash;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  final int PLAYER_A = 0;
  final int PLAYER_B = 1;
  final int TOTAL_STACK = 37;

  final int OCCUPIED = 0;
  final int OUT_OF_GRID = 1;
  final int SUCCESS = 2;

  private ArrayList<Board> boards;
  private ArrayList<Integer> remaining;
  private Display bdis;
  private boolean game_over;

  int curId;
  public Game() {
    this.boards = new ArrayList<>();
    boards.add(new Board());
    boards.add(new Board());
    this.bdis = new Display(this.boards);
    this.remaining = new ArrayList<>();
    remaining.add(TOTAL_STACK);
    remaining.add(TOTAL_STACK);
    this.game_over = false;
    this.curId = 1;
  }
  private void placeStash(int player_num, String color, int times, Display bdis) {
    String num[] = {"first", "second", "third"};
    String player[] = {"Player A", "Player B"};
    char color_draw;
    int length, height;
    if (color == "Green") {
      color_draw = 'G';
      length = 2;
      height = 1;
    } 
    else if (color == "Purple") {
      color_draw = 'P';
      length = 3;
      height = 1;
    }
    else if (color == "Red") {
      color_draw = 'R';
      length = 4;
      height = 1;
    }
    else {
      color_draw = 'B';
      length = 6;
      height = 1;
    }
    int i = 0;
    while (i < times) {
      Scanner scan = new Scanner(System.in);
      System.out.println(player[player_num] + ", where do you want to place the " + num[i] + " " + color + " stash?");
      System.out.println("-------------------------------------------------------------------------");
      String input = scan.next();
      InitialParser myParser = new InitialParser(input);
      i++;
      if (myParser.isValidFormat()) {
        int row = myParser.getRow();
        int col = myParser.getCol();
        Rectangle rec;
        if (myParser.getDir()) {
          rec = new Rectangle(this.curId, color_draw, height, length);
        }
        else {
          rec = new Rectangle(this.curId, color_draw, length, height);
        }
        this.curId++;
        int result = rec.putOnBoard(row, col, boards.get(player_num));
        if (result == OCCUPIED) {
          bdis.displayCollide();
          i--;
        }
        else if (result == OUT_OF_GRID) {
          bdis.displayOutOfGrid();
          i--;
        }
      }
      else {
        bdis.displayInvalidFormat();
        i--;
      }
      bdis.displaySingle(player_num);
    }
  }

  private void oneDig(int player_num) {
    boolean valid = false;
    int row = -1;
    int col = -1;
    bdis.displayTwo(player_num);
    while (!valid) {
      Scanner scan = new Scanner(System.in);
      bdis.displayWhere(player_num);
      String input = scan.next();
      DigParser myParser = new DigParser(input);
      row = myParser.getRow();
      col = myParser.getCol();
      if ((row < 0) || (row > 19) || (col < 0) || (col > 9)) {
        bdis.displayInvalidFormat();
        continue;
      }
      valid = true;
    }
    if (boards.get(1 - player_num).tryDig(row, col)) {
      remaining.set(1 - player_num, remaining.get(1 - player_num) - 1);
      bdis.displayHit();
    }
    else {
      bdis.displayMiss();
    }
    if (remaining.get(1 - player_num) == 0) {
      bdis.displayWin(player_num);
      this.game_over = true;
    }
  }
  public void initialize() {
    for (int player = PLAYER_A; player <= PLAYER_B; player++) {
      bdis.displaySingle(player);
      bdis.displayWelcome(player);
      placeStash(player, "Green", 2, bdis);
      placeStash(player, "Purple", 3, bdis);
      placeStash(player, "Red", 3, bdis);
      placeStash(player, "Blue", 2, bdis);
    }
  }

  public void start() {
    bdis.displayStart();
    while (!this.game_over) {
      oneDig(PLAYER_A);
      oneDig(PLAYER_B);
    }
  }
}