package edu.duke.ece651.sallystash;

abstract class Parser {
    int row;
    int col;
    boolean is_validFormat;
    
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
