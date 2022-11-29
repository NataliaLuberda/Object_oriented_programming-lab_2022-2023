package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private SortedSet<Vector2d> xSet = new TreeSet<>((a, b) -> {
        if(a.x == b.x)
            return a.y - b.y;
        return a.x - b.x;
    });
    private SortedSet<Vector2d> ySet = new TreeSet<>((a, b) -> {
        if(a.y == b.y)
            return a.x - b.x;
        return a.y - b.y;
    });
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.xSet.remove(oldPosition);
        this.ySet.remove(oldPosition);
        this.xSet.add(newPosition);
        this.ySet.add(newPosition);
    }

    public void addEl(Vector2d newPosition){
        this.xSet.add(newPosition);
        this.ySet.add(newPosition);
    }

    public void removeEl(Vector2d oldPosition){
        this.xSet.remove(oldPosition);
        this.ySet.remove(oldPosition);
    }

    public Vector2d uppRight() {//Bierzemy największe x i y z naszego posortowanego setu
        Vector2d yUpp = this.ySet.last();
        Vector2d xUpp = this.xSet.last();
        return new Vector2d(xUpp.x, yUpp.y);
    }

    public Vector2d downLeft() {//biezremy najmniejsze x i y naszego posortowanego setu
        Vector2d yDown = this.ySet.first();
        Vector2d xDown = this.xSet.first();
        return new Vector2d(xDown.x, yDown.y);
    }
}
