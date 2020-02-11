package edu.duke.ece651.sallystash;

public class Launcher {
  public static void main(String[] args) {
    Board brd = new Board();
    BoardDisplay bd = new BoardDisplay(brd);
    bd.display();
  }
}
