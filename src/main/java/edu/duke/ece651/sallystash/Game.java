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
  private ArrayList<Boolean> isComputer;

  private Scanner scan;
  private RandomGenerator rg;

  private Display bdis;
  private boolean game_over;

  private int curId;

  //Constructor for the game
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

    this.isComputer = new ArrayList<>();
    this.isComputer.add(false);
    this.isComputer.add(false);

    this.scan = new Scanner(System.in);
    this.rg = new RandomGenerator();

    this.game_over = false;
    this.curId = -1;
  }

  //Place the rectangle
  private int placeRectangle(int row, int col, int dir, int old_id, String color, int player_num) {
    char color_draw;
    int length, height;
    //Set length and height according to color
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
    //Create the rectangle according to orientation
    if (dir == VERTICAL) {
      rec = new Rectangle((old_id + 1) % TOTAL_STACK, color_draw, height, length);
    }
    else if (dir == HORIZONTAL){
      rec = new Rectangle((old_id + 1) % TOTAL_STACK, color_draw, length, height);
    }
    else {
      return WRONG_DIR;
    }
    //Put the rectangle on the board
    return rec.putOnBoard(row, col, boards.get(player_num));
  }

  //Place superstack
  private int placeSuper(int row, int col, int dir, int old_id, String color, int player_num) {
    Superstack sup;
    //If the input orientation is correct, create the stack
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      sup = new Superstack((old_id + 1) % TOTAL_STACK, dir);
    }
    else {
      return WRONG_DIR;
    }
    //Put the stack on board
    return sup.putOnBoard(row, col, boards.get(player_num));
  }

  //Place crazystack
  private int placeCrazy(int row, int col, int dir, int old_id, String color, int player_num) {
    Crazystack crazy;
    //If the input orientation is correct, create the stack
    if (dir == UP || dir == DOWN || dir == RIGHT || dir == LEFT) {
      crazy = new Crazystack((old_id + 1) % TOTAL_STACK, dir);
    }
    else {
      return WRONG_DIR;
    }
    //Put the stack on board
    return crazy.putOnBoard(row, col, boards.get(player_num));
  }

  //General function for placing the stash
  private void placeStash(int player_num, String color, int times, Display bdis) {
    String num[] = {"first", "second", "third", "forth", "fifth", "sixth", "seventh", "eighth", "ninth"};
    String player[] = {"Player A", "Player B"};
    int i = 0;
    while (i < times) {
      if (!isComputer.get(player_num)) {
        System.out.println(player[player_num] + ", where do you want to place the " + num[i] + " " + color + " stash?");
        System.out.println("-------------------------------------------------------------------------"); 
      }  
      //If computer, use randomgenerator to generate the input
      String input = isComputer.get(player_num) ? rg.generatePlace() : scan.next();
      InitialParser myParser = new InitialParser(input);
      int row = myParser.getRow();
      int col = myParser.getCol();
      int dir = myParser.getDir();
      int result;
      i++;
      if (myParser.isValidFormat()) {
        //Green and purple for rectangle
        if (color == "Green" || color == "Purple") {
          result = placeRectangle(row, col, dir, this.curId, color, player_num);
        }
        //red for superstack
        else if (color == "Red") {
          result = placeSuper(row, col, dir, this.curId, color, player_num);
        }
        //blue for crazystack
        else {
          result = placeCrazy(row, col, dir, this.curId, color, player_num);
        }
        if (result == OCCUPIED) {
          bdis.displayCollide(isComputer.get(player_num));
          i--;
        }
        else if (result == OUT_OF_GRID) {
          bdis.displayOutOfGrid(isComputer.get(player_num));
          i--;
        }
        else if (result == WRONG_DIR) {
          bdis.displayWrongDir(isComputer.get(player_num));
          i--;
        }
        else if (result == SUCCESS) {
          bdis.displaySingle(player_num, isComputer.get(player_num));
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
    //Display two boards: current player's and its opponent's
    bdis.displayTwo(player_num, isComputer.get(player_num));
    //Display where to dig
    bdis.displayWhere(player_num, isComputer.get(player_num));
    //If computer, use randomgenerator to generate the input
    String input = isComputer.get(player_num) ? rg.generateChoose() : scan.next();
    DigParser myParser = new DigParser(input);
    row = myParser.getRow();
    col = myParser.getCol();
    //If location goes out of grid, display the error message
    if ((row < 0) || (row > 19) || (col < 0) || (col > 9)) {
      bdis.displayInvalidFormat();
      return false;
    }
    if (boards.get(1 - player_num).tryDig(row, col)) {
      stack_remaining.set(1 - player_num, stack_remaining.get(1 - player_num) - 1);
      bdis.displayHit(isComputer.get(player_num), player_num);
    }
    else {
      bdis.displayMiss(isComputer.get(player_num), player_num);
    }
    //If all the stack of the opponents were hit, current player is the winner
    if (stack_remaining.get(1 - player_num) == 0) {
      bdis.displayWin(player_num);
      this.game_over = true;
    }
    return true;
  }

  //Remove all the cells that have the same stashId as the input
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
        }
      }
    }
    return res;
  }

  //Move the hit mark, set it invisible to opponent
  private void moveHit(int row, int col, ArrayList<Integer> list, int id, Board bd) {
    for (int i = Math.max(row - 4, 0); i < Math.min(row + 5, 19); i++) {
      for (int j = Math.max(col - 4, 0); j < Math.min(col + 5, 9); j++) {
        if (bd.getCell(i, j).getStashId() == id && list.contains(bd.getCell(i, j).getOrder())) {
          bd.getCell(i, j).setIsHit();
          bd.getCell(i, j).doNotShowOppo();
        }
      }
    }
  }

  private boolean oneMove(int player_num) {
    //If time limit is reached for move, just return
    if (move_remaining.get(player_num) == 0) {
      return false;
    }
    //Display two boards: current player's and its opponent's
    bdis.displayTwo(player_num, isComputer.get(player_num));
    //Display which stash to move
    bdis.displayWhich(player_num, isComputer.get(player_num));
    //If computer, use randomgenerator to generate the input
    String which = isComputer.get(player_num) ? rg.generateChoose() : scan.next();
    DigParser whichParser = new DigParser(which);
    int old_row = whichParser.getRow();
    int old_col = whichParser.getCol();
    //If invalid format or collide with others, display the error message
    if (!whichParser.isValidFormat() || !boards.get(player_num).getCell(old_row, old_col).getIsPlaced()) {
      bdis.displayInvalidMove(isComputer.get(player_num));
      return false;
    }
    //Display where to move the stash
    bdis.displayWhereTo(player_num, isComputer.get(player_num));
    //If computer, use randomgenerator to generate the input
    String where = isComputer.get(player_num) ? rg.generatePlace() : scan.next();
    InitialParser whereParser = new InitialParser(where);
    if (!whereParser.isValidFormat()) {
      bdis.displayInvalidFormat();
      return false;
    }
    int new_row = whereParser.getRow();
    int new_col = whereParser.getCol();
    int new_dir = whereParser.getDir();
    int old_id = boards.get(player_num).getCell(old_row, old_col).getStashId();
    
    //Place the result of move according to stashId
    if (old_id <= 1) {
      if (placeRectangle(new_row, new_col, new_dir, old_id - 1, "Green", player_num) != SUCCESS) {
        bdis.displayInvalidMove(isComputer.get(player_num));
        return false;
      }
    }
    else if (old_id <= 4) {
      if (placeRectangle(new_row, new_col, new_dir, old_id - 1, "Purple", player_num) != SUCCESS) {
        bdis.displayInvalidMove(isComputer.get(player_num));
        return false;
      }
    }
    else if (old_id <= 7) {
      if (placeSuper(new_row, new_col, new_dir, old_id - 1, "Red", player_num) != SUCCESS) {
        bdis.displayInvalidMove(isComputer.get(player_num));
        return false;
      }
    }
    else {
      if (placeCrazy(new_row, new_col, new_dir, old_id - 1, "Blue", player_num) != SUCCESS) {
        bdis.displayInvalidMove(isComputer.get(player_num));
        return false;
      }
    }
    //Use arraylist to store the order of hit marks
    ArrayList<Integer> hitList = remove(old_row, old_col, boards.get(player_num));
    moveHit(new_row, new_col, hitList, old_id, boards.get(player_num));
    bdis.displayTwo(player_num, isComputer.get(player_num));
    if (isComputer.get(player_num)) {
      bdis.displaySpecial(player_num);
    }
    //Decrease the move limit by 1
    move_remaining.set(player_num, move_remaining.get(player_num) - 1);
    return true;
  }

  private ArrayList<Integer> sonarHelper(int row, int col, Board bd) {
    //USe arraylist to store the result of sonar
    ArrayList<Integer> list = new ArrayList<>(4);
    list.add(0);
    list.add(0);
    list.add(0);
    list.add(0);
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 10; j++) {
        if (Math.abs((i - row)) * 6 + Math.abs((j - col)) * 6 <= 18) {
          Cell curCell = bd.getCell(i, j);
          if (curCell.getColor() == 'g') {
            list.set(0, list.get(0) + 1);
          }
          else if (curCell.getColor() == 'p') {
            list.set(1, list.get(1) + 1);
          }
          else if (curCell.getColor() == 'r') {
            list.set(2, list.get(2) + 1);
          }
          else if (curCell.getColor() == 'b') {
            list.set(3, list.get(3) + 1);
          } 
        }
      }
    }
    return list;
  }

  private boolean oneSonar(int player_num) {
    //IS limit of sonar is reached, return
    if (sonar_remaining.get(player_num) == 0) {
      return false;
    }
    bdis.displayTwo(player_num, isComputer.get(player_num));
    bdis.displaySonar(player_num, isComputer.get(player_num));
    //If computer, use randomgenerator to generate the input
    String where = isComputer.get(player_num) ? rg.generateChoose() : scan.next();
    DigParser whichParser = new DigParser(where);
    int row = whichParser.getRow();
    int col = whichParser.getCol();
    if (!whichParser.isValidFormat()) {
      bdis.displayInvalidFormat();
      return false;
    }
    //Use arraylist to store results of sonar
    ArrayList<Integer> list = sonarHelper(row, col, boards.get(1 - player_num));
    //Display the result of sonar
    bdis.displaySonarResult(list, isComputer.get(player_num), player_num);
    //Decrease the sonar limit by 1
    sonar_remaining.set(player_num, sonar_remaining.get(player_num) - 1);
    return true;
  }

  private void oneTurn(int player_num) {
    boolean valid = false;
    while (!valid) {
      bdis.displayTwo(player_num, isComputer.get(player_num));
      //Display the options: D, M, S
      bdis.displayOptions(player_num, move_remaining.get(player_num), sonar_remaining.get(player_num), isComputer.get(player_num));
      //If computer, use randomgenerator to generate the input
      String input = isComputer.get(player_num) ? rg.generateOptions() : scan.next();
      if (input.equals("D") || input.equals("d")) {
        valid = oneDig(player_num);
      }
      else if (input.equals("M") || input.equals("m")) {
        valid = oneMove(player_num);
      }
      else if (input.equals("S") || input.equals("s")) {
        valid = oneSonar(player_num);
      }
    }
  }

  //Ask each player if computer at the beginning of the game
  public void humanOrComputer() {
    for (int player = PLAYER_A; player <= PLAYER_B; player++) {
      bdis.displayIsComputer(player);
      String ans = scan.next();
      if (ans.equals("y") || ans.equals("Y")) {
        isComputer.set(player, true);
      }
    }
  }

  //Initialize the game by asking each player to place the stash
  public void initialize() {
    for (int player = PLAYER_A; player <= PLAYER_B; player++) {
      if (!isComputer.get(player)) {
        bdis.displaySingle(player, false);
        bdis.displayWelcome(player);
      }
      placeStash(player, "Green", 2, bdis);
      placeStash(player, "Purple", 3, bdis);
      placeStash(player, "Red", 3, bdis);
      placeStash(player, "Blue", 3, bdis);
    }
  }

  //Start the game
  public void start() {
    bdis.displayStart();
    //If a player wins, stop the game
    while (!this.game_over) {
      oneTurn(PLAYER_A);
      if (this.game_over) {
        return;
      }
      oneTurn(PLAYER_B);
    }
  }
}