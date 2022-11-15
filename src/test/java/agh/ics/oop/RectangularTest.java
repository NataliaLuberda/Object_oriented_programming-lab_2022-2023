package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangularTest {
    @Test
    void placeAnimalTest(){//testuje metodę place
        RectangularMap map= new RectangularMap();
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        assertTrue(map.place(animal));
        assertEquals(map.animals.size(),1);
    }
    @Test
    void AtAnimalTest(){ //testuje metodę objectAt
        RectangularMap map= new RectangularMap();
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        map.place(animal);
        assertEquals( map.objectAt(pos), animal);
    }
    @Test
    void isOccupiedAnimal(){ //testuje metodę isOccupied
        RectangularMap map= new RectangularMap();
        Vector2d positione=new Vector2d(2,2);
        Animal animal = new Animal(map,positione);
        map.place(animal);
        assertTrue(map.isOccupied(positione));
    }
    @Test
    void canMoveToAnimal(){ //testuje metodę canMoveTo
        RectangularMap map= new RectangularMap();
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        map.place(animal);
        assertFalse(map.canMoveTo(pos));
        assertTrue(map.canMoveTo(new Vector2d(7, 8)));
    }
}
