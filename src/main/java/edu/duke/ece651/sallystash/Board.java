package edu.duke.ece651.sallystash;

public class Board {
  private Cell[][] board;
  public Board() {
    board = new Cell[20][10];
    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 10; j++) {
            board[i][j] = new Cell();
        }
    } 
  }  
  public boolean tryDig(int i, int j) {
    if (board[i][j].getIsPlaced()) {
      board[i][j].setIsHit();
      return true;
    }
    board[i][j].setIsMiss();
    return false;
  }

  public Cell getCell(int i, int j) {
      return board[i][j];
  }

  public Cell[] getLine(int i) {
      return board[i];
  }
}
