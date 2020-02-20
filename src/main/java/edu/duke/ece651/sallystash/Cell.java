package edu.duke.ece651.sallystash;

public class Cell {
  private char color;
  private char old_color;
  private boolean isPlaced;
  private boolean isHit;
  private boolean isMiss;
  private boolean wasHit;
  private boolean wasMiss;
  private boolean showOpp;

  private int stashId;
  private int order;

  public Cell() {
    this.color = ' ';
    this.old_color = ' ';
    this.isPlaced = false;
    this.isHit = false;
    this.isMiss = false;
    this.wasHit = false;
    this.wasMiss = false;
    this.showOpp = true;
    this.stashId = -1;
    this.order = -1;
  }
  public void setColor(char c) {
    this.old_color = ' ';
    this.color = c;
  }
  public char getColor() {
    return this.color;
  }
  public char getOldColor() {
    return this.old_color;
  }
  public void setIsPlaced() {
    this.isPlaced = true;
  }
  public boolean getIsPlaced() {
    return this.isPlaced;
  }
  public void setIsHit() {
    this.showOpp = true;
    this.wasHit = false;
    this.wasMiss = false;
    this.isHit = true;
  }
  public boolean getIsHit() {
    return this.isHit;
  }
  public void setIsMiss() {
    this.showOpp = true;
    this.wasHit = false;
    this.wasMiss = false;
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
  public void setOrder(int order) {
    this.order = order;
  }
  public int getOrder() {
    return this.order;
  }
  public void setWasHit() {
    this.wasHit = true;
  }
  public boolean getWasHit() {
    return this.wasHit;
  }
  public void setWasMiss() {
    this.wasMiss = true;
  }
  public boolean getWasMiss() {
    return this.wasMiss;
  }
  public void doNotShowOppo() {
    this.showOpp = false;
  }
  public boolean getShowOppo() {
    return this.showOpp;
  }
  public void remove() {
    this.isPlaced = false;
    if (this.isHit) {
      this.wasHit = true;
      this.old_color = this.color;
    }
    if (this.isMiss) {
      this.wasMiss = true;
    }
    this.color = ' ';
    this.isHit = false;
    this.isMiss = false;
    this.showOpp = true;
    this.stashId = -1;
    this.order = -1;
  }


}