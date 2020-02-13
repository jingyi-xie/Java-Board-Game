package edu.duke.ece651.sallystash;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  final int PLAYER_A = 0;
  final int PLAYER_B = 1;

  final int OCCUPIED = 0;
  final int OUT_OF_GRID = 1;
  final int SUCCESS = 2;

  private ArrayList<Board> boards;

  Board board_b;
  int curId;
  public Game() {
    this.boards = new ArrayList<>();
    boards.add(new Board());
    boards.add(new Board());
    this.curId = 1;
  }
  public void placeStash(int player_num, String color, int times, BoardDisplay bdis) {
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
      Parser myParser = new Parser(input);
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
  public void initialize() {
    BoardDisplay bdis = new BoardDisplay(this.boards);
    for (int player = PLAYER_A; player <= PLAYER_B; player++) {
      bdis.displaySingle(player);
      bdis.displayWelcome(player);
      placeStash(player, "Green", 2, bdis);
      placeStash(player, "Purple", 3, bdis);
      placeStash(player, "Red", 3, bdis);
      placeStash(player, "Blue", 2, bdis);
    }
    //bdis.refresh(this.boards);
  }

}