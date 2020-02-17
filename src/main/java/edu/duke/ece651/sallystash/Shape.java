package edu.duke.ece651.sallystash;

interface Shape {
  final int OCCUPIED = 0;
  final int OUT_OF_GRID = 1;
  final int SUCCESS = 2;

  final int HORIZONTAL = 0;
  final int VERTICAL = 1;
  final int UP = 2;
  final int RIGHT = 3;
  final int DOWN = 4;
  final int LEFT = 5;
  
  int putOnBoard(int x, int y, Board bd);
}