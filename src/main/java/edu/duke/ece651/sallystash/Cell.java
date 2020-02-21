package edu.duke.ece651.sallystash;

public class Cell {
  private char color;
  private char old_color; //useful after move, opponent won't gain extra info
  private boolean isPlaced;
  private boolean isHit;
  private boolean isMiss;
  private boolean wasHit; //useful after move
  private boolean wasMiss; //useful after move
  private boolean showOpp; //if a hit mark is moved,opponent will not see the new mark

  private int stashId; //the id the stash that the cell resides in
  private int order; //the order of each cell in the stack, useful when move hit mark

  //Constructor for Cell
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
  //Set hit: opponent can now see the hit mark, clear wasHit and wasMiss
  public void setIsHit() {
    this.showOpp = true;
    this.wasHit = false;
    this.wasMiss = false;
    this.isHit = true;
  }
  public boolean getIsHit() {
    return this.isHit;
  }
  //Set Miss: opponent can now see the miss mark, clear wasHit and wasMiss
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
  //Remove the current cell, set wasHit, old_color and wasMiss when necessary
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