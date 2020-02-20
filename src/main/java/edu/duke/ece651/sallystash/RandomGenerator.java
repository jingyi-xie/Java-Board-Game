package edu.duke.ece651.sallystash;

import java.util.Random;

public class RandomGenerator {
    final String dirs[] = {"H", "V", "U", "R", "D", "L"};
    final String options[] = {"D", "M", "S"};
    private String generateRow() {
        Random rnd = new Random();
        char row = (char) (rnd.nextInt(20) + 'a');
        return Character.toString(row);
    }
    private String generateCol() {
        Random rnd = new Random();
        int col = rnd.nextInt(10);
        return Integer.toString(col);
    }
    private String generateDir() {
        Random rnd = new Random();
        int index = rnd.nextInt(6);
        return this.dirs[index];
    }
    public String generatePlace() {
        return generateRow() + generateCol() + generateDir();
    }
    public String generateChoose() {
        return generateRow() + generateCol();
    }
    public String generateOptions() {
        Random rnd = new Random();
        int index = rnd.nextInt(3);
        return this.options[index];
    }
}
