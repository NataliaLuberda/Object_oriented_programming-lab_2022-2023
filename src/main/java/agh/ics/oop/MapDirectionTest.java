package agh.ics.oop;

import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    void testNext1() {
// given:
        MapDirection x = MapDirection.NORTH;
// when:
        MapDirection result = x.next();
// then:
        assertEquals(MapDirection.EAST , result);
    }
    @Test
    void testNext2() {
// given:
        MapDirection x = MapDirection.EAST;
// when:
        MapDirection result = x.next();
// then:
        assertEquals(MapDirection.SOUTH , result);
    }
    @Test
    void testNext3() {
// given:
        MapDirection x = MapDirection.SOUTH;
// when:
        MapDirection result = x.next();
// then:
        assertEquals(MapDirection.WEST , result);
    }
    @Test
    void testNext4() {
// given:
        MapDirection x = MapDirection.WEST;
// when:
        MapDirection result = x.next();
// then:
        assertEquals(MapDirection.NORTH, result);
    }
    @Test
    void testPrevious1() {
// given:
        MapDirection x = MapDirection.NORTH;
// when:
        MapDirection result = x.previous();
// then:
        assertEquals(MapDirection.WEST , result);
    }
    @Test
    void testPrevious2() {
// given:
        MapDirection x = MapDirection.WEST;
// when:
        MapDirection result = x.previous();
// then:
        assertEquals(MapDirection.SOUTH , result);
    }
    @Test
    void testPrevious3() {
// given:
        MapDirection x = MapDirection.SOUTH;
// when:
        MapDirection result = x.previous();
// then:
        assertEquals(MapDirection.EAST, result);
    }
    @Test
    void testPrevious4() {
// given:
        MapDirection x = MapDirection.EAST;
// when:
        MapDirection result = x.previous();
// then:
        assertEquals(MapDirection.NORTH, result);
    }
}
