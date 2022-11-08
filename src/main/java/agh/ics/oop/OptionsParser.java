package agh.ics.oop;

public class OptionsParser{
    public static MoveDirection[] parse(String[] s){
        int i = 0;
        int tmp = 0;
        while (tmp<s.length){
            switch (s[tmp]) {
                case "f","forward"-> i++;
                case "b","backward" -> i++;
                case "r","right" -> i++;
                case "l","left" -> i++;
            };
            tmp++;
        }
        MoveDirection[] newTab = new MoveDirection[i];
        int j = 0;
        tmp = 0;
        while (j<s.length){
            switch (s[j]) {
                case "f","forward"-> newTab[tmp] = MoveDirection.FORWARD;
                case "b","backward" -> newTab[tmp] = MoveDirection.BACKWARD;
                case "r","right" -> newTab[tmp] = MoveDirection.RIGHT;
                case "l","left" -> newTab[tmp] = MoveDirection.LEFT;
                default -> tmp--;
            };
            j++;
            tmp++;
        }
        return newTab;
    }

}
