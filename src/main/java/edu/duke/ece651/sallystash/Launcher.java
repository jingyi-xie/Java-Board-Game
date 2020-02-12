package edu.duke.ece651.sallystash;

import java.util.Scanner;

public class Launcher {
  public static void main(String[] args) {
    Board brd = new Board();
    BoardDisplay bd = new BoardDisplay(brd);
    bd.display();
    Scanner scan_i = new Scanner(System.in);
    System.out.println("Enter i");
    int i = scan_i.nextInt();
    Scanner scan_j = new Scanner(System.in);
    System.out.println("Enter j");
    int j = scan_j.nextInt();
    Rectangle rec = new Rectangle(1, 'g', 1, 2);
    rec.putOnBoard(i, j, brd);
    bd.refresh(brd);
    bd.display();
    scan_i.close();
    scan_j.close();
  }
}
