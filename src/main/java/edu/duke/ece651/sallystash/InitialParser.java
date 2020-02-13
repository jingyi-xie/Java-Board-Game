package edu.duke.ece651.sallystash;

public class InitialParser extends Parser{
    private boolean is_verticle;

    private int tryParseInt(String input) {
        try {
          return Integer.parseInt(String.valueOf(input.charAt(1)));
        } catch (NumberFormatException e) {
          return -1;
        }
    }
    public InitialParser(String input) {
        this.is_validFormat = (input.length() == 3);
        this.row = (int) Character.toUpperCase(input.charAt(0)) - 65;
        // if (this.row < 0 || this.row > 19) {
        //     this.is_validFormat = false;
        // }
        this.col = tryParseInt(input);
        if (this.col == -1) {
            this.is_validFormat = false;
        }
        // if (this.col < 0 || this.col > 9) {
        //     this.is_validFormat = false;
        // }
        char dir = input.charAt(2);
        if (dir == 'v' || dir == 'V') {
            this.is_verticle = true;
        }
        else if (dir == 'h' || dir == 'H') {
            this.is_verticle = false;
        }
        else {
            this.is_validFormat = false;
        }     
    }
    public boolean getDir() {
        return this.is_verticle;
    }
  }

