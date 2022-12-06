package agh.ics.oop;
import agh.ics.oop.gui.App;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{

    private final List<Animal> animalList = new ArrayList<>();
    private App app;
    private int moveDelay;
    private IWorldMap map;

    private MoveDirection[] directions;

    public Vector2d[] positions;


    public SimulationEngine(MoveDirection[] directions,IWorldMap map,Vector2d[] positions) {
        this.positions = positions;
        this.map = map;
        this.directions = directions;

        this.numberAnimals();
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, App app, int moveDelay){
        this(directions, map, positions);
        this.app = app;
        this.moveDelay = moveDelay;
        for (Animal animal: animalList){
            animal.addObserver(app);
        }
    }

    private void numberAnimals() {
        for (Vector2d position : positions) {
            Animal animalek = new Animal(this.map, position);
            if (this.map.place(animalek)) {
                this.animalList.add(animalek);
            }
        }
    }

    @Override
    public void run(){
        System.out.println("Thread started.");
        int numberOfAnimalsOnMap = this.animalList.size();
        try{
            Thread.sleep(moveDelay);
            for(int i = 0; i < directions.length;i++){
                this.animalList.get(i%numberOfAnimalsOnMap).move(this.directions[i]);
                Thread.sleep(moveDelay);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setMoves(MoveDirection[] directions) {
        this.directions = directions;
    }
}
