package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorbackground;

    @FXML
    private AnchorPane anchorfrontground;

    @FXML
    private Label emaillabel;

    @FXML
    private TextField emailtextfield;

    @FXML
    private Button loginbutton;

    @FXML
    private Label loginlabel;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label passwordlabel;

    @FXML
    private Button signupbutton;

    @FXML
    private Label signuplabel;

    @FXML
    void initialize() {
        signupbutton.setOnAction(event -> {
            newScene("/com/example/courselast/sign-up.fxml");
        });

        loginbutton.setOnAction(event -> {
            String email_text = emailtextfield.getText().trim();
            String password_text = passwordfield.getText().trim();

            if(!email_text.equals("") && !password_text.equals("")) {
                userLogin(email_text, password_text);
            }
            else {
                System.out.println("Error");
            }
        });
    }

    private void userLogin(String emailText, String passwordText) {
        DatabaseHandler handler = new DatabaseHandler();
        User user = new User();
        user.setEmail(emailText);
        user.setPassword(passwordText);
        ResultSet resultSet = handler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1) {
            newScene("/com/example/courselast/main-window.fxml");
        }
    }

    public void newScene(String window) {
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        signupbutton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(window));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();

            if (window == "/com/example/courselast/main-window.fxml") {
                stage.setWidth(screenWidth);
                stage.setHeight(screenHeight);
            }

            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
