package edu.duke.ece651.sallystash;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

public class SallyTest {
  @Test
  public void test_booard() {
      Board bd = new Board();
      assertFalse(bd.tryDig(5, 5));
      Cell cur = bd.getCell(5, 5);
      assertTrue(cur.getIsMiss());
      Cell[] cellArray = bd.getLine(5);
      assertEquals(cellArray[5], cur);
      Cell anotherCell = bd.getCell(6, 6);
      anotherCell.setIsPlaced();
      assertTrue(bd.tryDig(6, 6));
  }

  @Test
  public void test_cell() {
      Cell c = new Cell();
      c.setColor('g');
      assertTrue(c.getColor() == 'g');
      c.setIsPlaced();
      assertTrue(c.getIsPlaced());
      c.setIsHit();
      assertTrue(c.getIsPlaced());
      c.setIsHit();
      assertTrue(c.getIsHit());
      c.setIsMiss();
      assertTrue(c.getIsMiss());
      c.setStashId(5);
      assertTrue(c.getStashId() == 5);
  }

  @Test
  public void test_linedisplay() {
      Board b = new Board();
      b.getCell(1, 1).setIsHit();
      b.getCell(1, 2).setIsMiss();
      LineDisplay ld = new LineDisplay(b);
      ld.displayLine(0, 1);
      ld.displayLine(1, 1);
  }

  @Test
  public void test_initialparser() {
    InitialParser ip1 = new InitialParser("A3h");
    assertTrue(ip1.getRow() == 0);
    assertTrue(ip1.getCol() == 3);
    //assertFalse(ip1.getDir());
    InitialParser ip2 = new InitialParser("b4H");
    assertTrue(ip2.getRow() == 1);
    assertTrue(ip2.getCol() == 4);
    //assertFalse(ip2.getDir());
    InitialParser ip3 = new InitialParser("c5V");
    assertTrue(ip3.getRow() == 2);
    assertTrue(ip3.getCol() == 5);
    //assertTrue(ip3.getDir());
    InitialParser ip4 = new InitialParser("D6V");
    assertTrue(ip4.getRow() == 3);
    assertTrue(ip4.getCol() == 6);
    //assertTrue(ip4.getDir());
    InitialParser ip5 = new InitialParser("aaaaaaa");
    assertFalse(ip5.isValidFormat());
    InitialParser ip6 = new InitialParser("aah");
    assertFalse(ip6.isValidFormat());
    InitialParser ip7 = new InitialParser("m5y");
    assertFalse(ip7.isValidFormat());
  }

  @Test
  public void test_digparser() {
    DigParser dp1 = new DigParser("A3h");
    assertFalse(dp1.isValidFormat());
    DigParser dp2 = new DigParser("b4");
    assertTrue(dp2.getRow() == 1);
    assertTrue(dp2.getCol() == 4);
    DigParser dp3 = new DigParser("c5");
    assertTrue(dp3.getRow() == 2);
    assertTrue(dp3.getCol() == 5);
    DigParser dp4 = new DigParser("D6");
    assertTrue(dp4.getRow() == 3);
    assertTrue(dp4.getCol() == 6);
    DigParser dp5 = new DigParser("aa");
    assertFalse(dp5.isValidFormat());
  } 

  @Test
  public void test_twoComputer() throws FileNotFoundException {
    System.setIn(new FileInputStream("./src/test/resources/twocomputers.txt"));
    Launcher l = new Launcher();
    l.main(null);
  }

  @Test
  public void test_Person() throws FileNotFoundException {
    System.setIn(new FileInputStream("./src/test/resources/person.txt"));
    Launcher l = new Launcher();
    l.main(null);
  }
}