package edu.duke.ece651.sallystash;

public class DigParser extends Parser{
    public DigParser(String input) {
        this.is_validFormat = (input.length() == 2);
        if (this.is_validFormat) {
            this.row = (int) Character.toUpperCase(input.charAt(0)) - 65;
            this.col = tryParseInt(input.charAt(1));
            if (this.col == -1) {
                this.is_validFormat = false;
            }
        }  
    }
}

