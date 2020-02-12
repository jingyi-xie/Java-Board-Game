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
  public void putOnBoard(int x, int y, Board bd) {
    for (int i = x; i < x + this.height; i++) {
        for (int j = y; j < y + this.length; j++) {
            bd.getCell(i, j).setIsPlaced();
            bd.getCell(i, j).setColor(this.color);
            bd.getCell(i, j).setStashId(this.id);
        }
    }
  }

}
