package edu.duke.ece651.sallystash;

//Make a new game and start it
public class Launcher {
  public static void main(String[] args) {
    Game game = new Game();
    game.humanOrComputer();
    game.initialize();
    game.start();
  }
}
