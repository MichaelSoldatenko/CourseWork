package com.example.courselast;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorbackground;

    @FXML
    private AnchorPane anchorfrontground;

    @FXML
    private PasswordField confirmpasswordfield;

    @FXML
    private Label confirmpasswordlabel;

    @FXML
    private Label countrylabel;

    @FXML
    private TextField countrytextfield;

    @FXML
    private Label emaillabel;

    @FXML
    private TextField emailtextfield;

    @FXML
    private Label namelabel;

    @FXML
    private TextField nametextfield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label passwordlabel;

    @FXML
    private Button signupbutton2;

    @FXML
    private Button backtologinbutton;

    @FXML
    private Label signuplabel;

    @FXML
    private Label surnamelabel;

    @FXML
    private TextField surnametextfield;

    @FXML
    void initialize() {
        backtologinbutton.setOnAction(this::backToLogin);

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        signupbutton2.setOnAction(event -> {
            signUpNewUser();

            signupbutton2.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/main-window.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();

            stage.setWidth(screenWidth);
            stage.setHeight(screenHeight);
            stage.setTitle("Вікно Управління");

            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void backToLogin(ActionEvent event) {
        backtologinbutton.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/courselast/hello-view.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Авторизація");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void signUpNewUser() {
        DatabaseHandler handler = new DatabaseHandler();

        String email = emailtextfield.getText();
        String password = passwordfield.getText();
        String name = nametextfield.getText();
        String surname = surnametextfield.getText();
        String country = countrytextfield.getText();

        User user = new User(email, password, name, surname, country);

        handler.SignUpUser(user);
    }
}