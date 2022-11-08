package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final List<Animal> animalList;
    private IWorldMap map;
    private MoveDirection[] directions;

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
