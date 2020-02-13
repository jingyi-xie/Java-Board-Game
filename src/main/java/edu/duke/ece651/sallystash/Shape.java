package edu.duke.ece651.sallystash;

interface Shape {
  final int OCCUPIED = 0;
  final int OUT_OF_GRID = 1;
  final int SUCCESS = 2;
  int putOnBoard(int x, int y, Board bd);
}
