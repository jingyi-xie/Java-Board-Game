package edu.duke.ece651.sallystash;

public class Rectangle implements Shape {
  private int id;
  private char color;
  private int length;
  private int height;

  public Rectangle(int id, char color, int len, int ht) {
    this.id = id;
    this.color = color;
    this.length = len;
    this.height = ht;
  }

  @Override
  public int putOnBoard(int x, int y, Board bd) {
    if ((x < 0) || (x > 19) || (x + height > 20) || (y < 0) || (y > 9) || (y + length > 10)) {
      return OUT_OF_GRID;
    }
    for (int i = x; i < x + this.height; i++) {
      for (int j = y; j < y + this.length; j++) {
          Cell curCell = bd.getCell(i, j);
          if (curCell.getIsPlaced()) {
            return OCCUPIED;
          }
      }
    }
    int curOrder = 1;
    for (int i = x; i < x + this.height; i++) {
        for (int j = y; j < y + this.length; j++) {
            Cell curCell = bd.getCell(i, j);
            curCell.setOrder(curOrder);
            curOrder++;
            curCell.setIsPlaced();
            curCell.setColor(this.color);
            curCell.setStashId(this.id);
        }
    }
    return SUCCESS;
  }

}