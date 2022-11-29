package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.HashMap;
import java.util.Set;

public class World{
    public static void main(String[] args) {
        Application.launch(App.class, args);
    };

    static Direction[] changer(String[] arr){
        Direction[] directions = new Direction[arr.length];
        for(int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case "f" -> directions[i] = Direction.FORWARD;
                case "b" -> directions[i] = Direction.BACKWARD;
                case "l" -> directions[i] = Direction.LEFT;
                case "r" -> directions[i] = Direction.RIGHT;
                default -> directions[i] = null;
            }
        }
        return directions;
    }

    static void run(Direction[] tab) {
        for (Direction direction : tab) {
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
