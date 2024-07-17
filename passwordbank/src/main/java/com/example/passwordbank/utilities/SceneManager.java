package com.example.passwordbank.utilities;

import java.io.IOException;

import com.example.passwordbank.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class SceneManager {
    
    private Scene scene;

    public Scene loadPage(String name) {
        
        try {
            FXMLLoader sceneLoader = new FXMLLoader(App.class.getResource("views/" + name + ".fxml"));
            Parent rootScene = sceneLoader.load();
            scene = new Scene(rootScene);
            scene.setFill(Color.rgb(0, 0, 0, 0.01));
            return scene;

        } catch (IOException e) {
            System.out.println("ERROR: There was an error trying to load the desired file... ");
            System.out.println(e.getMessage() + "\n\n");
            e.printStackTrace();
        }
        return scene;
    }
}
