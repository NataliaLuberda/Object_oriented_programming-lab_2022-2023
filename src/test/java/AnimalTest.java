package agh.ics.oop;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    void moveTest() {
        Animal Olaf = new Animal();
        assertEquals("(2,2),Północ",Olaf.toString());
        MoveDirection[] moves=new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.BACKWARD};
        String[] correct={"(2,3),Północ","(2,3),Wschód","(1,3),Wschód","(0,3),Wschód","(0,3),Wschód","(0,3),Północ", "(0,3),Zachód", "(0,3),Zachód", "(1,3),Zachód", "(1,3),Południe","(1,4),Południe", "(1,4),Południe"};
        int i = 0;
        while (i<correct.length){
            Olaf.move(moves[i]);
            assertEquals(correct[i],Olaf.toString());
            i++;
        }
    }
}

