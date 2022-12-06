package agh.ics.oop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    final static int IMAGE_SIZE = 20;
    private final VBox varticalBox;

    public GuiElementBox(IMapElement element){
        ImageView image1;
        try{
            Image image = new Image(new FileInputStream(element.getPng()));
            image1 = new ImageView(image);
            image1.setFitWidth(IMAGE_SIZE);
            image1.setFitHeight(IMAGE_SIZE);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        Label elemPos;
        if (element instanceof Animal) {
            elemPos = new Label(element.getPosition().toString());
        }
        else  {
            elemPos = new Label("Trawa");
        }
        this.varticalBox = new VBox();
        this.varticalBox.getChildren().addAll(image1, elemPos);
        this.varticalBox.setAlignment(Pos.CENTER);
    }

    public VBox getElementContainer(){
        return this.varticalBox;
    }


}
