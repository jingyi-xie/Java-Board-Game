package edu.duke.ece651.sallystash;

public class Cell {
  private char color;
  private boolean isPlaced;
  private boolean isHit;
  private boolean isMiss;
  private int stashId;

  public Cell() {
    this.color = ' ';
    this.isPlaced = false;
    this.isHit = false;
    this.isMiss = false;
    this.stashId = -1;
  }
  public void setColor(char c) {
    this.color = c;
  }
  public char getColor() {
    return this.color;
  }
  public void setIsPlaced() {
    this.isPlaced = true;
  }
  public boolean getIsPlaced() {
    return this.isPlaced;
  }
  public void setIsHit() {
    this.isHit = true;
  }
  public boolean getIsHit() {
    return this.isHit;
  }
  public void setIsMiss() {
    this.isMiss = true;
  }
  public boolean getIsMiss() {
    return this.isMiss;
  }
  public void setStashId(int id) {
    this.stashId = id;
  }
  public int getStashId() {
    return this.stashId;
  }
}
