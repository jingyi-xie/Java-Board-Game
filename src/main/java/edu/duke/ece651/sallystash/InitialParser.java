package edu.duke.ece651.sallystash;

public class InitialParser extends Parser{
    private boolean is_verticle;
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
                this.is_verticle = true;
            }
            else if (dir == 'h' || dir == 'H') {
                this.is_verticle = false;
            }
            else {
                this.is_validFormat = false;
            }   
        }
          
    }
    public boolean getDir() {
        return this.is_verticle;
    }
  }

