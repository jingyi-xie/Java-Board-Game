package edu.duke.ece651.sallystash;

//Abstract class for initialParser and digParser
abstract class Parser {
    int row;
    int col;
    boolean is_validFormat;
    
    int tryParseInt(char input) {
        try {
          return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
          return -1;
        }
    }
    int getRow() {
        return this.row;
    }
    int getCol() {
        return this.col;
    }
    boolean isValidFormat() {
        return this.is_validFormat;
    }
}