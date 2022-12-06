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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class App extends Application implements IPositionChangeObserver{
    private GridPane actuallyGridPane = new GridPane();
    private AbstractWorldMap newMap;
    private Button button;
    public final int width = 40;
    public final int height = 40;
    private TextField text;
    private SimulationEngine engine;
    private Stage stage;
    private Vector2d low;
    private Vector2d high;

    @Override
    public void start(Stage primaryStage){
        this.button = new Button("Start");
        this.text = new TextField();
        HBox input = new HBox(text,button);
        input.setAlignment(Pos.CENTER);


        button.setOnAction(event -> {
            MoveDirection[] directions = OptionsParser.parse(text.getText().split(" "));
            this.engine.setMoves(directions);
            Thread thread = new Thread(this.engine);
            thread.start();
        });



        this.drawMap();

        VBox mainBox = new VBox(input, this.actuallyGridPane);

        Scene scene = new Scene(mainBox, this.width*(this.high.x-this.low.x+4), this.height*(this.high.y-this.low.y+4));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() throws IllegalArgumentException {


        String[] args = getParameters().getRaw().toArray(new String[0]);

        try {
            this.newMap = new GrassField(10);
            MoveDirection[] directions = OptionsParser.parse(args);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            int moveDelay = 300;
            this.engine = new SimulationEngine(directions,newMap, positions, this, moveDelay);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(){
        Label label = new Label("y/x");
        Map<Vector2d, IMapElement> elements = newMap.getAnimalsAndGrass();
        this.low =   newMap.getDownLeft();
        this.high = newMap.getUppRight();
        actuallyGridPane.add(label, 0, 0);
        actuallyGridPane.getRowConstraints().add(new RowConstraints(height));
        actuallyGridPane.getColumnConstraints().add(new ColumnConstraints(width));
        GridPane.setHalignment(label, HPos.CENTER);


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

        addpositions(elements.values(), (HashMap<Vector2d, IMapElement>) newMap.getAnimalsAndGrass());

        if(newMap.getGrass() != null) {
            HashMap<Vector2d, IMapElement> grass = newMap.getGrass();
            addpositions(grass.values(), grass);
        }

        actuallyGridPane.setGridLinesVisible(true);
    }

    private void addpositions(Collection<IMapElement> values, HashMap<Vector2d, IMapElement> grass) {
        for (IMapElement gr : values) {
            VBox elemntPng = new GuiElementBox(gr).getElementContainer();
            Vector2d pos = gr.getPosition();
            actuallyGridPane.add(elemntPng, pos.x - low.x + 1,high.y - pos.y + 1);
            GridPane.setHalignment(elemntPng, HPos.CENTER);
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)  {
        Platform.runLater(() -> {
            this.actuallyGridPane.getChildren().clear();
            this.actuallyGridPane.getColumnConstraints().clear();
            this.actuallyGridPane.getRowConstraints().clear();
            this.actuallyGridPane.setGridLinesVisible(false);
            this.drawMap();
        });
    }
}
/*

public class App extends Application {

    private AbstractWorldMap map;
    private GridPane gridPane = new GridPane();
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private SimulationEngine simulationEngine;


    @Override
    public void init() throws IllegalArgumentException{

        String[] args = getParameters().getRaw().toArray(new String[0]);

        try{
            MoveDirection[] directions = OptionsParser.parse(args);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            simulationEngine = new SimulationEngine(directions, map, positions, this, 100);

        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }



    public void updateBounds(){

        xMin = map.getDownLeft().x;
        yMin = map.getDownLeft().y;
        xMax = map.getUppRight().x;
        yMax = map.getUppRight().y;
    }


    public void xyLabel(){

        GridPane.setHalignment(new Label("y/x"), HPos.CENTER);
        gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
        gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));
        gridPane.add(new Label("y/x"), 0, 0);
    }

    public void columnsFunction(){

        for (int i = xMin; i <= xMax; i++){
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.getColumnConstraints().add(new ColumnConstraints(WIDTH));
            gridPane.add(label, i-xMin+1, 0);
        }
    }

    public void rowsFunction(){

        for (int i = yMax; i >= yMin; i--){
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.getRowConstraints().add(new RowConstraints(HEIGHT));
            gridPane.add(label, 0, yMax -i + 1);
        }
    }

    public void addElements() throws FileNotFoundException {

        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMax; y >= yMin; y--) {
                Vector2d currentPosition = new Vector2d(x,y);
                if (map.isOccupied(currentPosition)) {
                    Object object = map.objectAt(currentPosition);
                    GuiElementBox guiElementBox = new GuiElementBox((IMapElement) object);
                    VBox verticalBox = guiElementBox.getElementContainer();
                    gridPane.add(verticalBox, x - xMin + 1, yMax -y + 1);
                    GridPane.setHalignment(verticalBox, HPos.CENTER);
                }
            }}

    }

    public void prepareScene() throws FileNotFoundException {
        this.updateBounds();
        this.xyLabel();
        this.columnsFunction();
        this.rowsFunction();
        this.addElements();
        gridPane.setGridLinesVisible(true);
    }


    public void refreshMap(){
        Platform.runLater(() ->{
            gridPane.getChildren().clear();
            gridPane.setGridLinesVisible(false);
            gridPane.getColumnConstraints().clear();
            gridPane.getRowConstraints().clear();

            try {
                prepareScene();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
    };

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Button button = new Button("start");
        GridPane.setHalignment(button, HPos.CENTER);
        TextField textField = new TextField();
        HBox hbox = new HBox(textField, button); //pojemnik na buttony

        button.setOnAction(event -> {
            String[] args = textField.getText().split(" ");
            MoveDirection[] directions = OptionsParser.parse(args);
            simulationEngine.setMoves(directions);
            Thread thread = new Thread(this.simulationEngine);
            thread.start();
        });





        prepareScene();
        VBox mapAndButtons = new VBox(hbox, gridPane);

        Scene scene = new Scene(mapAndButtons, WIDTH * ((xMax - xMin) + 2 ), HEIGHT * ((yMax - yMin) + 3));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


 */