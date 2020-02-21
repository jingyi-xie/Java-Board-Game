package edu.duke.ece651.sallystash;

public class Board {
  private Cell[][] board;
  //Constructor for board, initialize all the Cells in it
  public Board() {
    board = new Cell[20][10];
    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 10; j++) {
            board[i][j] = new Cell();
        }
    } 
  }
  //Dig: if successful(there is a stack in it), return true  
  public boolean tryDig(int i, int j) {
    if (board[i][j].getIsPlaced()) {
      board[i][j].setIsHit();
      return true;
    }
    //Dig fails: set miss
    board[i][j].setIsMiss();
    return false;
  }

  //Get the cell at (i, j)
  public Cell getCell(int i, int j) {
      return board[i][j];
  }

  //Get the ith line in the board
  public Cell[] getLine(int i) {
      return board[i];
  }
}
