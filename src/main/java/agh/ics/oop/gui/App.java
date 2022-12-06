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
