package com.example.passwordbank.utilities;

import java.io.IOException;

import com.example.passwordbank.App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneManager {
    
    private Scene screen;

    public Scene loadScreen(String name) {
        
        try {
            FXMLLoader screenLoader = new FXMLLoader(App.class.getResource("views/" + name + ".fxml"));
            Parent rootScreen = screenLoader.load();
            screen = new Scene(rootScreen);
            return screen;

        } catch (IOException e) {
            System.out.println("ERRO: Erro ao carregar a tela... ");
            System.out.println(e.getMessage() + "\n\n");
            e.printStackTrace();
        }
        return screen;
    }
}
