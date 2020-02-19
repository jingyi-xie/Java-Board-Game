package edu.duke.ece651.sallystash;

public class Superstack implements Shape {
    final char color = 'r';
    private int id;
    private int orientation;
  
    public Superstack(int id, int ori) {
      this.id = id;
      this.orientation = ori;
    }
  
    private int checkBoundary(int x, int y, Board bd) {
        if (this.orientation == UP) {
            if ((x < 0) || (x > 18) || (y < 1) || (y > 8)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == DOWN) {
            if ((x < 0) || (x > 18) || (y < 0) || (y > 7)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == RIGHT) {
            if ((x < 0) || (x > 17) || (y < 0) || (y > 8)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == LEFT) {
            if ((x < 0) || (x > 17) || (y < 1) || (y > 9)) {
                return OUT_OF_GRID;
            }
        }
        return -1;
    }

    private boolean occupiedHelper(int x, int y, Board bd) {
        return bd.getCell(x, y).getIsPlaced();
    }
    private boolean checkOccupied(int x, int y, Board bd) {
        if (this.orientation == UP) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x + 1, y - 1, bd) || 
            occupiedHelper(x + 1, y, bd) || occupiedHelper(x + 1, y + 1, bd);
        }
        else if (this.orientation == DOWN) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x, y + 1, bd) || 
            occupiedHelper(x, y + 2, bd) || occupiedHelper(x + 1, y + 1, bd);
        }
        else if (this.orientation == RIGHT) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x + 1, y, bd) || 
            occupiedHelper(x + 2, y, bd) || occupiedHelper(x + 1, y + 1, bd);
        }
        else if (this.orientation == LEFT) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x + 1, y, bd) || 
            occupiedHelper(x + 2, y, bd) || occupiedHelper(x + 1, y - 1, bd);
        }
        return false;
    }

    private void placeHelper(int x, int y, Board bd, int order) {
        Cell curCell = bd.getCell(x, y);
        curCell.setIsPlaced();
        curCell.setColor(this.color);
        curCell.setOrder(order);
        curCell.setStashId(this.id);
    }

    @Override
    public int putOnBoard(int x, int y, Board bd) {
        if (checkBoundary(x, y, bd) == OUT_OF_GRID) {
            return OUT_OF_GRID;
        }
        if (checkOccupied(x, y, bd)) {
            return OCCUPIED;
        }
        if (this.orientation == UP) {
            placeHelper(x, y, bd, 1);
            placeHelper(x + 1, y - 1, bd, 2); 
            placeHelper(x + 1, y, bd, 3);
            placeHelper(x + 1, y + 1, bd, 4);
        }
        else if (this.orientation == DOWN) {
            placeHelper(x + 1, y + 1, bd, 1);
            placeHelper(x, y + 2, bd, 2);
            placeHelper(x, y + 1, bd, 3);
            placeHelper(x, y, bd, 4);
        }
        else if (this.orientation == RIGHT) {
            placeHelper(x + 1, y + 1, bd, 1);
            placeHelper(x, y, bd, 2);
            placeHelper(x + 1, y, bd, 3);
            placeHelper(x + 2, y, bd, 4);
        }
        else if (this.orientation == LEFT) {
            placeHelper(x + 1, y - 1, bd, 1);
            placeHelper(x + 2, y, bd, 2);
            placeHelper(x + 1, y, bd, 3);
            placeHelper(x, y, bd, 4);
        }
        return SUCCESS;
    }
  
  }