package com.example.passwordbank.utilities;

import java.io.IOException;

import com.example.passwordbank.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class SceneManager {
    
    private Scene screen;

    public Scene loadScreen(String name) {
        
        try {
            FXMLLoader screenLoader = new FXMLLoader(App.class.getResource("views/" + name + ".fxml"));
            Parent rootScreen = screenLoader.load();
            screen = new Scene(rootScreen);
            screen.setFill(Color.rgb(0, 0, 0, 0.01));
            return screen;

        } catch (IOException e) {
            System.out.println("ERROR: There was an error trying to load the desired file... ");
            System.out.println(e.getMessage() + "\n\n");
            e.printStackTrace();
        }
        return screen;
    }
}
