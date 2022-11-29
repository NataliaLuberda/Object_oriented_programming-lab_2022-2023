package agh.ics.oop;


import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import java.lang.Math;
public class GrassField extends AbstractWorldMap implements IWorldMap,IPositionChangeObserver{

    final int grassSample;
    private final HashMap<Vector2d, IMapElement> grassSampleList = new HashMap<>();;
    private final ArrayList<Vector2d> positionsOnTheField = new ArrayList<>();//wolne miejsca na mapie
    private final MapBoundary listMap;


    public GrassField(int grassSample){//konstruktor
        this.listMap = new MapBoundary();
        this.grassSample = grassSample;
        for (int i = 0; i <= ((int) Math.sqrt(10 * grassSample)); i++){
            for (int j = 0; j <= ((int) Math.sqrt(10 * grassSample)); j++){
                positionsOnTheField.add(new Vector2d(i,j));//inicjowanie mapy
            }
        }
        putGrass(positionsOnTheField);//metoda dodawania  kępków trawy
    }

    public void putGrass(ArrayList<Vector2d> positionsOnTheField){//położenie trawy w losowych miejscach
        Random randomSamplePlace = new Random();
        int currentGrass = 0;
        do {
            int random =  randomSamplePlace.nextInt(positionsOnTheField.size()-1);//losuje indeks
            Vector2d grassPlace = positionsOnTheField.get(random);//wolne randomowe miejsce w zakresie tablicy
            this.grassSampleList.put(grassPlace,new Grass(grassPlace));//dodaje twrawe do hashmapy
            this.listMap.addEl(grassPlace);
            positionsOnTheField.remove(grassPlace);//usuwam zajęte miejsce
            currentGrass++;
        } while (currentGrass<this.grassSample);
    }

    public HashMap<Vector2d, IMapElement> getGrass(){
        return grassSampleList;//zwaracanie listy traw
    };

    @Override
    public Vector2d getDownLeft() {
        return this.listMap.downLeft();
    }

    @Override
    public Vector2d getUppRight() {
        return this.listMap.uppRight();
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        Object object = this.objectAt(position);
        return !(object instanceof Animal);//mogę się ruszyć jeśli nie jest zwierzakiem
    }
    @Override
    public boolean place(Animal animal) {//kwestia położenia na mapie zwierzęcia
        if (!isOccupied(animal.getPosition())) {//jeżeli nie zajęte to kładę
            this.animals.put(animal.getPosition(),animal);
            this.listMap.addEl(animal.getPosition());
            animal.addObserver(this);
            positionsOnTheField.remove(animal.getPosition());//usuwam zajęte miejsce
            return true;
        } else {
            if (moveAnimalOnGrass(animal.getPosition())){//zwierzak zjada trawe a ta rośnie gdzie indziej
                this.animals.put(animal.getPosition(),animal);//jeżeli tam była trawa to zwierzak ją zjadł i zostaje na jej miejscu
                animal.addObserver(this);
                return true;
            }
        }
        throw new IllegalArgumentException(animal.getPosition() + " is not available position");
    }

    @Override
    public Object objectAt(Vector2d position)
    {
        if(this.animals.get(position) != null) {
            return this.animals.get(position);
        } else if(this.grassSampleList.get(position) != null){
            return this.grassSampleList.get(position);
        }
        return null;
    }
    public boolean moveAnimalOnGrass(Vector2d position){
        Object ob = objectAt(position);//sprawdzam czy animal czy trawa czy null
        if(ob instanceof Grass){//jeżeli trawa
            Random randomSamplePlace = new Random();//losuje nowe miejsce dla trawy
            int random =  randomSamplePlace.nextInt(positionsOnTheField.size());
            Vector2d grassPlace = positionsOnTheField.get(random);
            this.grassSampleList.remove(((Grass) ob).getPosition());//usuwam poprzednią pozycję i trawę
            this.grassSampleList.put(grassPlace,new Grass(grassPlace));//nowe miejsce dla trawy
            this.listMap.addEl(grassPlace);
            positionsOnTheField.remove(grassPlace);//usuwam je z dostępnych miejsc
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (canMoveTo(newPosition)){
            if(objectAt(newPosition) == null){//jeżeli niczego tam nie ma
                IMapElement puszek = animals.get(oldPosition);
                this.animals.remove(oldPosition);
                this.animals.put(newPosition,puszek);//dodaje  zwierze na innej pozycji
                this.listMap.positionChanged(oldPosition,newPosition);
                positionsOnTheField.add(oldPosition);//starą dodaje do dostepnych
                positionsOnTheField.remove(newPosition);//nową odejmuje z dostępnych miejs
            }
            else {//jeżeli tam jest trawa
                IMapElement puszek = animals.get(oldPosition);
                this.animals.remove(oldPosition);
                positionsOnTheField.add(oldPosition);//dodaje starą pozycje
                this.listMap.removeEl(oldPosition);//nowej nie dodaje bo jest gdyż była na niej trawa
                moveAnimalOnGrass(newPosition);//zmieniam pozycje zjedzonej trawy
                this.animals.put(newPosition,puszek);
            }
        }

    }
}