package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final List<Animal> animalList;
    private IWorldMap map;
<<<<<<< HEAD
    private MoveDirection[] directions;
=======
    private final MoveDirection[] directions;
>>>>>>> 0885df9b0d919f74c4f5c34e4259b39412c9e183

    public Vector2d[] positions;
    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions) {
        this.animalList = new ArrayList<>();
        this.map = map;
        this.directions = directions;
        for(Vector2d position:positions) {
            Animal animalek = new Animal(map, position);
            if(this.map.place(animalek)){
                this.animalList.add(animalek);
            }
        }
    }

    @Override
    public void run() {
        int n = animalList.size();
        for(int i = 0; i < directions.length;i++){
            this.animalList.get(i%n).move(this.directions[i]);
        }
    }
}
