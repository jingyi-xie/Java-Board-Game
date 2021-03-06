package edu.duke.ece651.sallystash;

public class Crazystack implements Shape {
    final char color = 'b';
    private int id;
    private int orientation;
  
    //Constructor for crazystack
    public Crazystack(int id, int ori) {
      this.id = id;
      this.orientation = ori;
    }
  
    //Check if the coordinate (x, y) goes out of boundary
    private int checkBoundary(int x, int y, Board bd) {
        if (this.orientation == UP) {
            if ((x < 0) || (x > 15) || (y < 0) || (y > 8)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == DOWN) {
            if ((x < 0) || (x > 15) || (y < 1) || (y > 9)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == RIGHT) {
            if ((x < 1) || (x > 19) || (y < 0) || (y > 5)) {
                return OUT_OF_GRID;
            }
        }
        else if (this.orientation == LEFT) {
            if ((x < 0) || (x > 18) || (y < 0) || (y > 5)) {
                return OUT_OF_GRID;
            }
        }
        return -1;
    }

    private boolean occupiedHelper(int x, int y, Board bd) {
        return bd.getCell(x, y).getIsPlaced();
    }
    //Check if the new stash will collide with others
    private boolean checkOccupied(int x, int y, Board bd) {
        if (this.orientation == UP) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x + 1, y, bd) || 
            occupiedHelper(x + 2, y, bd) || occupiedHelper(x + 2, y + 1, bd) || 
            occupiedHelper(x + 3, y + 1, bd) || occupiedHelper(x + 4, y + 1, bd);
        }
        else if (this.orientation == DOWN) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x + 1, y, bd) || 
            occupiedHelper(x + 2, y, bd) || occupiedHelper(x + 2, y - 1, bd) || 
            occupiedHelper(x + 3, y - 1, bd) || occupiedHelper(x + 4, y - 1, bd);
        }
        else if (this.orientation == RIGHT) {
            return occupiedHelper(x, y, bd) || occupiedHelper(x, y + 1, bd) || 
            occupiedHelper(x, y + 2, bd) || occupiedHelper(x - 1, y + 2, bd) || 
            occupiedHelper(x - 1, y + 3, bd) || occupiedHelper(x - 1, y + 4, bd);
        }
        else {
            return occupiedHelper(x, y, bd) || occupiedHelper(x, y + 1, bd) || 
            occupiedHelper(x, y + 2, bd) || occupiedHelper(x + 1, y + 2, bd) || 
            occupiedHelper(x + 1, y + 3, bd) || occupiedHelper(x + 1, y + 4, bd);
        }
    }

    //Helper function to place the new stash
    private void placeHelper(int x, int y, Board bd, int order) {
        Cell curCell = bd.getCell(x, y);
        curCell.setIsPlaced();
        curCell.setColor(this.color);;
        curCell.setOrder(order);
        curCell.setStashId(this.id);
    }

    //Put the current crazystask on board, according to its orientation
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
            placeHelper(x + 1, y, bd, 2);
            placeHelper(x + 2, y, bd, 3);
            placeHelper(x + 2, y + 1, bd, 4);
            placeHelper(x + 3, y + 1, bd, 5);
            placeHelper(x + 4, y + 1, bd, 6);
        }
        else if (this.orientation == DOWN) {
            placeHelper(x + 4, y - 1, bd, 1);
            placeHelper(x + 3, y - 1, bd, 2);
            placeHelper(x + 2, y - 1, bd, 3);
            placeHelper(x + 2, y, bd, 4);
            placeHelper(x + 1, y, bd, 5);
            placeHelper(x, y, bd, 6);
        }
        else if (this.orientation == RIGHT) {
            placeHelper(x, y, bd, 1);
            placeHelper(x, y + 1, bd, 2);
            placeHelper(x, y + 2, bd, 3);
            placeHelper(x - 1, y + 2, bd, 4);
            placeHelper(x - 1, y + 3, bd, 5);
            placeHelper(x - 1, y + 4, bd, 6);
        }
        else if (this.orientation == LEFT) {
            placeHelper(x + 1, y + 4, bd, 1);
            placeHelper(x + 1, y + 3, bd, 2);
            placeHelper(x + 1, y + 2, bd, 3);
            placeHelper(x, y + 2, bd, 4);
            placeHelper(x, y + 1, bd, 5);
            placeHelper(x, y, bd, 6);
        }
        return SUCCESS;
    }
  
  }