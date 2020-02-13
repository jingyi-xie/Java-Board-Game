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
  public boolean putOnBoard(int x, int y, Board bd) {
    assert x >= 0 : "x should >= 0"; 
    assert x <= 19 : "x should <= 19"; 
    assert y >= 0 : "y should >= 0"; 
    assert y <= 9 : "y should <= 9"; 
    if ((x + height > 19) || (y + length > 9)) {
      return false;
    }
    for (int i = x; i < x + this.height; i++) {
      for (int j = y; j < y + this.length; j++) {
          Cell curCell = bd.getCell(i, j);
          if (curCell.getIsPlaced()) {
            return false;
          }
      }
    }
    for (int i = x; i < x + this.height; i++) {
        for (int j = y; j < y + this.length; j++) {
            Cell curCell = bd.getCell(i, j);
            curCell.setIsPlaced();
            curCell.setColor(this.color);
            curCell.setStashId(this.id);
        }
    }
    return true;
  }

}