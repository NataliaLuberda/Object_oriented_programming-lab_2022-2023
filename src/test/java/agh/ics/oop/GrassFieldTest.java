package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void placeAnimalOnFieldTest(){//testuje metodę place
        GrassField map= new GrassField(10);
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        assertTrue(map.place(animal));
        assertEquals(map.animals.size(),1);
    }
    @Test
    void AtAnimalOnFieldTest(){ //testuje metodę objectAt
        GrassField map= new GrassField(10);
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        map.place(animal);
        assertEquals( map.objectAt(pos), animal);
    }
    @Test
    void isOccupiedOnFieldAnimal(){ //testuje metodę isOccupied
        GrassField map= new GrassField(10);
        Vector2d positione=new Vector2d(2,2);
        Animal animal = new Animal(map,positione);
        map.place(animal);
        assertTrue(map.isOccupied(positione));
    }
    @Test
    void canMoveToAnimalOnAPlace(){ //testuje metodę canMoveTo
        GrassField map= new GrassField(10);
        Vector2d pos=new Vector2d(2,2);
        Animal animal = new Animal(map,pos);
        map.place(animal);
        assertFalse(map.canMoveTo(pos));
        assertTrue(map.canMoveTo(new Vector2d(7, 8)));
    }
}
