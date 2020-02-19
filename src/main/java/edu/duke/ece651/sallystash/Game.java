package edu.duke.ece651.sallystash;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
  final int PLAYER_A = 0;
  final int PLAYER_B = 1;
  final int TOTAL_CELL = 43;
  final int TOTAL_STACK = 11;

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

  private int curId;

  public Game() {
    this.boards = new ArrayList<>();
    this.boards.add(new Board());
    this.boards.add(new Board());
    this.bdis = new Display(this.boards);

    this.stack_remaining = new ArrayList<>();
    this.stack_remaining.add(TOTAL_CELL);
    this.stack_remaining.add(TOTAL_CELL);

    this.move_remaining = new ArrayList<>();
    this.move_remaining.add(SPECIAL_LIMIT);
    this.move_remaining.add(SPECIAL_LIMIT);

    this.sonar_remaining = new ArrayList<>();
    this.sonar_remaining.add(SPECIAL_LIMIT);
    this.sonar_remaining.add(SPECIAL_LIMIT);

    this.scan = new Scanner(System.in);

    this.game_over = false;
    this.curId = -1;
  }

  private int placeRectangle(int row, int col, int dir, int old_id, String color, int player_num) {
    char color_draw;
    int length, height;
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
    if (dir == VERTICAL) {
      rec = new Rectangle((old_id + 1) % TOTAL_STACK, color_draw, height, length);
    }
    else if (dir == HORIZONTAL){
      rec = new Rectangle((old_id + 1) % TOTAL_STACK, color_draw, length, height);
    }
    else {
      return WRONG_DIR;
    }
    return rec.putOnBoard(row, col, boards.get(player_num));
  }

  private int placeSuper(int row, int col, int dir, int old_id, String color, int player_num) {
    Superstack sup;
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      sup = new Superstack((old_id + 1) % TOTAL_STACK, dir);
    }
    else {
      return WRONG_DIR;
    }
    return sup.putOnBoard(row, col, boards.get(player_num));
  }

  private int placeCrazy(int row, int col, int dir, int old_id, String color, int player_num) {
    Crazystack crazy;
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      crazy = new Crazystack((old_id + 1) % TOTAL_STACK, dir);
    }
    else {
      return WRONG_DIR;
    }
    return crazy.putOnBoard(row, col, boards.get(player_num));
  }

  private void placeStash(int player_num, String color, int times, Display bdis) {
    String num[] = {"first", "second", "third", "forth", "fifth", "sixth", "seventh", "eighth", "ninth"};
    String player[] = {"Player A", "Player B"};
    int i = 0;
    while (i < times) {
      System.out.println(player[player_num] + ", where do you want to place the " + num[i] + " " + color + " stash?");
      System.out.println("-------------------------------------------------------------------------"); 
      if (!scan.hasNext()) {
        return;
      }
      String input = scan.next();
      InitialParser myParser = new InitialParser(input);
      int row = myParser.getRow();
      int col = myParser.getCol();
      int dir = myParser.getDir();
      int result;
      i++;
      if (myParser.isValidFormat()) {
        if (color == "Green" || color == "Purple") {
          result = placeRectangle(row, col, dir, this.curId, color, player_num);
        }
        else if (color == "Red") {
          result = placeSuper(row, col, dir, this.curId, color, player_num);
        }
        else {
          result = placeCrazy(row, col, dir, this.curId, color, player_num);
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
        else if (result == SUCCESS) {
          bdis.displaySingle(player_num);
          this.curId++;
        }
      }
      else {
        bdis.displayInvalidFormat();
        i--;
      }
    }
  }

  private boolean oneDig(int player_num) {
    int row = -1;
    int col = -1;
    bdis.displayTwo(player_num);
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

  private ArrayList<Integer> remove(int row, int col, Board bd) {
    int removeId = bd.getCell(row, col).getStashId();
    ArrayList<Integer> res = new ArrayList<>();
    for (int i = Math.max(row - 4, 0); i < Math.min(row + 5, 19); i++) {
      for (int j = Math.max(col - 4, 0); j < Math.min(col + 5, 9); j++) {
        if (bd.getCell(i, j).getStashId() == removeId && bd.getCell(i, j).getIsPlaced()) {
          Cell curCell = bd.getCell(i, j);
          if (curCell.getIsHit()) {
            res.add(curCell.getOrder());
          }
          curCell.remove();
          //To-do: add was_hit, was_remove
        }
      }
    }
    return res;
  }

  private void moveHit(int row, int col, ArrayList<Integer> list, int id, Board bd) {
    for (int i = Math.max(row - 4, 0); i < Math.min(row + 5, 19); i++) {
      for (int j = Math.max(col - 4, 0); j < Math.min(col + 5, 9); j++) {
        if (bd.getCell(i, j).getStashId() == id && list.contains(bd.getCell(i, j).getOrder())) {
          bd.getCell(i, j).setIsHit();
          //add show to opponent
        }
      }
    }
  }

  private boolean oneMove(int player_num) {
    if (move_remaining.get(player_num) == 0) {
      return false;
    }
    bdis.displayTwo(player_num);
    bdis.displayWhich(player_num);
    if (!scan.hasNext()) {
      return false;
    }
    String which = scan.next();
    DigParser whichParser = new DigParser(which);
    int old_row = whichParser.getRow();
    int old_col = whichParser.getCol();
    if (!whichParser.isValidFormat() || !boards.get(player_num).getCell(old_row, old_col).getIsPlaced()) {
      bdis.displayInvalidMove();
      return false;
    }
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
    int old_id = boards.get(player_num).getCell(old_row, old_col).getStashId();
    
    if (old_id <= 1) {
      if (placeRectangle(new_row, new_col, new_dir, old_id - 1, "Green", player_num) != SUCCESS) {
        bdis.displayInvalidMove();
        return false;
      }
    }
    else if (old_id <= 4) {
      if (placeRectangle(new_row, new_col, new_dir, old_id - 1, "Purple", player_num) != SUCCESS) {
        bdis.displayInvalidMove();
        return false;
      }
    }
    else if (old_id <= 7) {
      if (placeSuper(new_row, new_col, new_dir, old_id - 1, "Red", player_num) != SUCCESS) {
        bdis.displayInvalidMove();
        return false;
      }
    }
    else {
      if (placeCrazy(new_row, new_col, new_dir, old_id - 1, "Blue", player_num) != SUCCESS) {
        bdis.displayInvalidMove();
        return false;
      }
    }
    ArrayList<Integer> hitList = remove(old_row, old_col, boards.get(player_num));
    moveHit(new_row, new_col, hitList, old_id, boards.get(player_num));
    bdis.displayTwo(player_num);
    move_remaining.set(player_num, move_remaining.get(player_num) - 1);
    return true;
  }

  private void oneTurn(int player_num) {
    boolean valid = false;
    while (!valid) {
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