package com.amirheshmati.javaproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stg stg;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        handle();
        stg.start("hello-view.fxml");

    }


    interface Stg{
        void start(String name) throws IOException;
    }


    void handle(){
        stg=new Stg() {
            @Override
            public void start(String name) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            }
        };
    }



    public static void main(String[] args) {
        launch();
    }
}