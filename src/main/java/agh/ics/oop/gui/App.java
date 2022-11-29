package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {
    private GridPane actuallyGridPane;
    private AbstractWorldMap newMap;

    public int width = 40;
    public int height = 40;

    @Override
    public void init() throws Exception{

        try{
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            newMap = new GrassField(20);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            SimulationEngine engineRun = new SimulationEngine(directions, newMap, positions);
            engineRun.run();
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void start(Stage primaryStage){

        actuallyGridPane = new GridPane();
        drawMap();
        Scene scenePrimary = new Scene(actuallyGridPane, 400, 400);
        primaryStage.setScene(scenePrimary);
        primaryStage.show();
    }

    public void drawMap(){
        Label label = new Label("y/x");
        Map<Vector2d, IMapElement> elements = newMap.getAnimalsAndGrass();
        Vector2d low =   newMap.getDownLeft();
        Vector2d high = newMap.getUppRight();
        actuallyGridPane.add(label, 0, 0);
        actuallyGridPane.getRowConstraints().add(new RowConstraints(height));
        actuallyGridPane.getColumnConstraints().add(new ColumnConstraints(width));
        GridPane.setHalignment(label, HPos.CENTER);
        actuallyGridPane.setGridLinesVisible(true);

        for (int i = 1; low.x + i - 1 <= high.x; i++){
            Label numberX = new Label("" +( i + low.x - 1));
            actuallyGridPane.add(numberX,  i , 0);
            actuallyGridPane.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(numberX, HPos.CENTER);
        }

        for (int i = 1; low.y - 1 + i <= high.y; i++){
            Label numberY = new Label("" + (high.y -i + 1));
            actuallyGridPane.add(numberY, 0,i);
            actuallyGridPane.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(numberY, HPos.CENTER);
        }

        for (IMapElement element: elements.values()) {
            Label elem = new Label(element.toString());
            Vector2d pos = element.getPosition();
            actuallyGridPane.add(elem,  pos.x - low.x + 1, high.y - pos.y + 1);
            GridPane.setHalignment(elem, HPos.CENTER);
        }

        if(newMap.getGrass() != null) {
            HashMap<Vector2d, IMapElement> grass = newMap.getGrass();
            for (IMapElement gr : grass.values()) {
                Label elem = new Label(gr.toString());
                Vector2d pos = gr.getPosition();
                actuallyGridPane.add(elem, pos.x - low.x + 1,high.y - pos.y + 1);
                GridPane.setHalignment(elem, HPos.CENTER);
            }
        }
    }

}