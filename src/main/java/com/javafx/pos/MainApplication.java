package com.javafx.pos;

import com.javafx.pos.pojo.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.hibernate.Session;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        System.out.println("about to load db");
        loadDB();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/pos/Pos.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


    private void loadDB()
    {
        Session s = SingletonDatabase.getSession();
        s.beginTransaction();
        Product p1,p2,p3;
        p1 = new Product(1,"Lolipop",23,12);
        p2 = new Product(2,"Doritos",2,12);
        p3 = new Product(3,"Chocolate",3,12);
        s.save(p1);
        s.save(p2);
        s.save(p3);
        s.getTransaction().commit();

    }
}