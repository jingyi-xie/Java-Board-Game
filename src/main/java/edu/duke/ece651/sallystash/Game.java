package edu.duke.ece651.sallystash;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  final int PLAYER_A = 0;
  final int PLAYER_B = 1;
  final int TOTAL_STACK = 43;
  final int SPECIAL_LIMIT = 3;


  final int OCCUPIED = 0;
  final int OUT_OF_GRID = 1;
  final int SUCCESS = 2;
  final int WRONG_DIR = 3;

  final int HORIZONTAL = 0;
  final int VERTICAL = 1;
  final int UP = 2;
  final int RIGHT = 3;
  final int DOWN = 4;
  final int LEFT = 5;

  private ArrayList<Board> boards;
  private ArrayList<Integer> stack_remaining;
  private ArrayList<Integer> move_remaining;
  private ArrayList<Integer> sonar_remaining;

  private Scanner scan;

  private Display bdis;
  private boolean game_over;

  int curId;
  public Game() {
    this.boards = new ArrayList<>();
    this.boards.add(new Board());
    this.boards.add(new Board());
    this.bdis = new Display(this.boards);

    this.stack_remaining = new ArrayList<>();
    this.stack_remaining.add(TOTAL_STACK);
    this.stack_remaining.add(TOTAL_STACK);

    this.move_remaining = new ArrayList<>();
    this.move_remaining.add(SPECIAL_LIMIT);
    this.move_remaining.add(SPECIAL_LIMIT);

    this.sonar_remaining = new ArrayList<>();
    this.sonar_remaining.add(SPECIAL_LIMIT);
    this.sonar_remaining.add(SPECIAL_LIMIT);

    this.scan = new Scanner(System.in);

    this.game_over = false;
    this.curId = 1;
  }

  private int placeRectangle(InitialParser myParser, String color, int player_num) {
    char color_draw;
    int length, height;
    int row = myParser.getRow();
    int col = myParser.getCol();
    if (color == "Green") {
      color_draw = 'g';
      length = 2;
      height = 1;
    } 
    else {
      color_draw = 'p';
      length = 3;
      height = 1;
    }
    Rectangle rec;
    if (myParser.getDir() == VERTICAL) {
      rec = new Rectangle(this.curId, color_draw, height, length);
    }
    else if (myParser.getDir() == HORIZONTAL){
      rec = new Rectangle(this.curId, color_draw, length, height);
    }
    else {
      return WRONG_DIR;
    }
    this.curId++;
    return rec.putOnBoard(row, col, boards.get(player_num));
  }

  private int placeSuper(InitialParser myParser, String color, int player_num) {
    int row = myParser.getRow();
    int col = myParser.getCol();
    int dir = myParser.getDir();
    Superstack sup;
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      sup = new Superstack(this.curId, dir);
    }
    else {
      return WRONG_DIR;
    }
    this.curId++;
    return sup.putOnBoard(row, col, boards.get(player_num));
  }

  private int placeCrazy(InitialParser myParser, String color, int player_num) {
    int row = myParser.getRow();
    int col = myParser.getCol();
    int dir = myParser.getDir();
    Crazystack crazy;
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      crazy = new Crazystack(this.curId, dir);
    }
    else {
      return WRONG_DIR;
    }
    this.curId++;
    return crazy.putOnBoard(row, col, boards.get(player_num));
  }

  private void placeStash(int player_num, String color, int times, Display bdis) {
    String num[] = {"first", "second", "third", "forth", "fifth", "sixth", "seventh", "eighth", "ninth"};
    String player[] = {"Player A", "Player B"};
    int i = 0;
    while (i < times) {
      // Scanner scan = new Scanner(System.in);
      System.out.println(player[player_num] + ", where do you want to place the " + num[i] + " " + color + " stash?");
      System.out.println("-------------------------------------------------------------------------"); 
      if (!scan.hasNext()) {
        return;
      }
      String input = scan.next();
      InitialParser myParser = new InitialParser(input);
      int result;
      i++;
      if (myParser.isValidFormat()) {
        if (color == "Green" || color == "Purple") {
          result = placeRectangle(myParser, color, player_num);
        }
        else if (color == "Red") {
          result = placeSuper(myParser, color, player_num);
        }
        else {
          result = placeCrazy(myParser, color, player_num);
        }
        if (result == OCCUPIED) {
          bdis.displayCollide();
          i--;
        }
        else if (result == OUT_OF_GRID) {
          bdis.displayOutOfGrid();
          i--;
        }
        else if (result == WRONG_DIR) {
          bdis.displayWrongDir();
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

  private boolean oneDig(int player_num) {
    int row = -1;
    int col = -1;
    bdis.displayTwo(player_num);
    // Scanner scan = new Scanner(System.in);
    bdis.displayWhere(player_num);
    if (!scan.hasNext()) {
      return false;
    }
    String input = scan.next();
    DigParser myParser = new DigParser(input);
    row = myParser.getRow();
    col = myParser.getCol();
    if ((row < 0) || (row > 19) || (col < 0) || (col > 9)) {
      bdis.displayInvalidFormat();
      return false;
    }
    if (boards.get(1 - player_num).tryDig(row, col)) {
      stack_remaining.set(1 - player_num, stack_remaining.get(1 - player_num) - 1);
      bdis.displayHit();
    }
    else {
      bdis.displayMiss();
    }
    if (stack_remaining.get(1 - player_num) == 0) {
      bdis.displayWin(player_num);
      this.game_over = true;
    }
    return true;
  }

  private boolean oneMove(int player_num) {
    bdis.displayTwo(player_num);
    bdis.displayWhich(player_num);
    if (!scan.hasNext()) {
      return false;
    }
    String which = scan.next();
    DigParser whichParser = new DigParser(which);
    if (!whichParser.isValidFormat()) {
      bdis.displayInvalidFormat();
      return false;
    }
    int old_row = whichParser.getRow();
    int old_col = whichParser.getCol();
    bdis.displayWhereTo(player_num);
    if (!scan.hasNext()) {
      return false;
    }
    String where = scan.next();
    InitialParser whereParser = new InitialParser(where);
    if (!whereParser.isValidFormat()) {
      bdis.displayInvalidFormat();
      return false;
    }
    int new_row = whereParser.getRow();
    int new_col = whereParser.getCol();
    int new_dir = whereParser.getDir();

    return true;
  }

  private void oneTurn(int player_num) {
    boolean valid = false;
    while (!valid) {
      // Scanner scan = new Scanner(System.in);
      bdis.displayTwo(player_num);
      bdis.displayOptions(player_num, move_remaining.get(player_num), sonar_remaining.get(player_num));
      if (!scan.hasNext()) {
        return;
      }
      String input = scan.next();
      if (input.equals("D") || input.equals("d")) {
        valid = oneDig(player_num);
      }
      else if (input.equals("M") || input.equals("m")) {
        valid = oneMove(player_num);
      }
      // else if (input.equals("S") || input.equals("s")) {
      //   valid = oneSonar(player_num);
      // }
    }
  }

  public void initialize() {
    for (int player = PLAYER_A; player <= PLAYER_B; player++) {
      bdis.displaySingle(player);
      bdis.displayWelcome(player);
      placeStash(player, "Green", 2, bdis);
      placeStash(player, "Purple", 3, bdis);
      placeStash(player, "Red", 3, bdis);
      placeStash(player, "Blue", 3, bdis);
    }
  }

  public void start() {
    bdis.displayStart();
    while (!this.game_over) {
      // oneDig(PLAYER_A);
      // oneDig(PLAYER_B);
      oneTurn(PLAYER_A);
      oneTurn(PLAYER_B);
    }
  }
}