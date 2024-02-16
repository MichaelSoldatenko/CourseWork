package com.example.courselast;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorbackground;

    @FXML
    void initialize() {
        assert anchorbackground != null : "fx:id=\"anchorbackground\" was not injected: check your FXML file 'main-window.fxml'.";

    }

}