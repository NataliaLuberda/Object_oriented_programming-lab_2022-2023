package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World{

    public static void main(String[] args) {
        Application.launch(App.class,args);
    };

    static MoveDirection[] changer(String[] arr){
        MoveDirection[] directions = new MoveDirection[arr.length];
        for(int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case "f" -> directions[i] = MoveDirection.FORWARD;
                case "b" -> directions[i] = MoveDirection.BACKWARD;
                case "l" -> directions[i] = MoveDirection.LEFT;
                case "r" -> directions[i] = MoveDirection.RIGHT;
                default -> directions[i] = null;
            }
        }
        return directions;
    }

    static void run(MoveDirection[] tab) {
        for (MoveDirection direction : tab) {
            if (direction != null) {
                switch (direction) {
                    case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                    case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                    case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                    case LEFT -> System.out.println("Zwierzak idzie w lewo");
                }
            }
        }
    }
}
