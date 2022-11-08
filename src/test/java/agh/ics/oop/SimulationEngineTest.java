package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {

    @Test
    public void occupiedTest(){ //sprawdzam czy pole jest zajęte
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    public void outsideTest(){ // sprawdza czy zwierze wychodzi za mape
        String[] args = {"f", "f", "r", "f", "f","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(1,1)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    public void theSameSpotTest(){ // dwa na tym samym polu zaczynają
        String[] args = { "f", "l", "r", "f" };
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = { new Vector2d(1,1), new Vector2d(1, 1)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(1, 3)));
        assertFalse(map.isOccupied(new Vector2d(0, 1)));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
    }


    @Test
    public void meetAtTheSpotTest(){ // jeden chcę wejść zajęte pole przez innego
        String[] args = {"f", "f", "r", "r", "f", "f", "l", "l","l","f","l","l","l","f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(4, 5);
        Vector2d[] positions = { new Vector2d(0,1), new Vector2d(1, 0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }
}