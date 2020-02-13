package edu.duke.ece651.sallystash;

public class LineDisplay {
    final int AS_SELF = 0;
    final int AS_OPP = 1;

    private Board board;
    public LineDisplay(Board b) {
      this.board = b;
    }
    public void displayLine(int CASE, int index) {
      int letter = (char)65 + index;
      System.out.print((char)letter + " ");
      for (int i =0; i < 10; i++) {
        Cell cur = board.getCell(index, i);
        if (cur.getIsHit() == true && CASE == AS_SELF) {
          System.out.print('*');
        }
        else if (CASE == AS_SELF) {
          System.out.print(cur.getColor());
        }
        else if (cur.getIsHit() == true && CASE == AS_OPP){
          System.out.print(cur.getColor());
        }
        else if (cur.getIsMiss() == true && CASE == AS_OPP){
          System.out.print('X');
        }
        else {
          System.out.print(' ');
        }

        if (i == 9) {
          continue;
        }
        System.out.print("|");
      }
      System.out.print(" " + (char)letter);
    }
  }

