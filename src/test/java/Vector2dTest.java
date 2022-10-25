package agh.ics.oop;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {
    @Test
    void TestEquals(){
        Vector2d position1 = new Vector2d(0, 2);
        Vector2d position2 = new Vector2d(0, 2);
        boolean x = position1.equals(position2);

        assertEquals(x, true);

    }
    @Test
    void TestEquals2(){
        Vector2d position1 = new Vector2d(0, 2);
        Vector2d position2 = new Vector2d(1, 2);
        boolean x = position1.equals(position2);

        assertEquals(x, false);

    }

    @Test
    void TestToString() {
        Vector2d position1 = new Vector2d(1, 2);

        String x = position1.toString();

        assertEquals(x, "(1,2)");
    }

    @Test
    void TestPrecedes1() {
        Vector2d position1 = new Vector2d(0, 2);
        Vector2d position2 = new Vector2d(1, 4);
        boolean x = position1.precedes(position2);

        assertEquals(x, true);
    }

    @Test
    void TestPrecedes2() {
        Vector2d position1 = new Vector2d(5, 2);
        Vector2d position2 = new Vector2d(1, 4);
        boolean x = position1.precedes(position2);

        assertEquals(x, false);
    }

    @Test
    void TestFollows1() {
        Vector2d position1 = new Vector2d(1, 8);
        Vector2d position2 = new Vector2d(1, 4);
        boolean x = position1.follows(position2);

        assertEquals(x, true);
    }

    @Test
    void TestFollows2() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d position2 = new Vector2d(1, 4);
        boolean x = position1.follows(position2);

        assertEquals(x, false);
    }

    @Test
    void TestUpperRight() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d position2 = new Vector2d(1, 4);
        Vector2d nowy = position1.upperRight(position2);

        assertEquals(nowy.toString(), "(1,8)");
    }

    @Test
    void TestLowerLeft() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d position2 = new Vector2d(1, 4);
        Vector2d nowy = position1.lowerLeft(position2);

        assertEquals(nowy.toString(), "(0,4)");
    }

    @Test
    void TestAdd() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d position2 = new Vector2d(1, 4);
        Vector2d nowy = position1.add(position2);

        assertEquals(nowy.toString(), "(1,12)");
    }

    @Test
    void TestSubtract() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d position2 = new Vector2d(1, 4);
        Vector2d nowy = position1.subtract(position2);

        assertEquals(nowy.toString(), "(-1,4)");

    }

    @Test
    void TestOpposite() {
        Vector2d position1 = new Vector2d(0, 8);
        Vector2d nowy = position1.opposite();

        assertEquals(nowy.toString(), "(0,-8)");

    }
}
