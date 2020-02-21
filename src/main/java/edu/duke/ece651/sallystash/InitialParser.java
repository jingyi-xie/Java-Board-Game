package edu.duke.ece651.sallystash;

public class InitialParser extends Parser{
    final int HORIZONTAL = 0;
    final int VERTICAL = 1;
    final int UP = 2;
    final int RIGHT = 3;
    final int DOWN = 4;
    final int LEFT = 5;

    private int orientation;

    //Constructor for initialParser: row + col + orientation
    public InitialParser(String input) {
        this.is_validFormat = (input.length() == 3);
        if (this.is_validFormat) {
            this.row = (int) Character.toUpperCase(input.charAt(0)) - 65;
            this.col = tryParseInt(input.charAt(1));
            if (this.col == -1) {
                this.is_validFormat = false;
            }
            char dir = input.charAt(2);
            if (dir == 'v' || dir == 'V') {
                this.orientation = VERTICAL;
            }
            else if (dir == 'h' || dir == 'H') {
                this.orientation = HORIZONTAL;
            }
            else if (dir == 'u' || dir == 'U') {
                this.orientation = UP;
            }
            else if (dir == 'd' || dir == 'D') {
                this.orientation = DOWN;
            }
            else if (dir == 'l' || dir == 'L') {
                this.orientation = LEFT;
            }
            else if (dir == 'r' || dir == 'R') {
                this.orientation = RIGHT;
            }
            else {
                this.is_validFormat = false;
            }   
        }
          
    }

    //Get the orientation
    public int getDir() {
        return this.orientation;
    }
  }

