package edu.duke.ece651.sallystash;

public class Crazystack implements Shape {
    final char color = 'b';
    private int id;
    private int orientation;
  
    public Crazystack(int id, int ori) {
      this.id = id;
      this.orientation = ori;
    }
  
    @Override
    public int putOnBoard(int x, int y, Board bd) {
      return SUCCESS;
    }
  
  }