import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BirdWatcherApp extends Application {

    private BirdDatabase birdDatabase = new BirdDatabase();

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Button addBirdButton = new Button("Add Bird");
        Button observeBirdButton = new Button("Add Observation");
        Button viewAllButton = new Button("View All Birds");
        Button deleteBirdButton = new Button("Delete Bird");
        Button searchBirdButton = new Button("Search Bird");
        Button quitButton = new Button("Quit");

        layout.getChildren().addAll(addBirdButton, observeBirdButton, viewAllButton, deleteBirdButton, searchBirdButton, quitButton);

        addBirdButton.setOnAction(event -> addBird());
        observeBirdButton.setOnAction(event -> observeBird());
        viewAllButton.setOnAction(event -> viewAllBirds());
        deleteBirdButton.setOnAction(event -> deleteBird());
        searchBirdButton.setOnAction(event -> searchBird());
        quitButton.setOnAction(event -> primaryStage.close());

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setTitle("Birdwatcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addBird() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Add Bird");
        nameDialog.setHeaderText("Add a New Bird");
        nameDialog.setContentText("Name:");
        String name = nameDialog.showAndWait().orElse("");

        TextInputDialog latinNameDialog = new TextInputDialog();
        latinNameDialog.setTitle("Add Bird");
        latinNameDialog.setHeaderText("Add a New Bird");
        latinNameDialog.setContentText("Latin Name:");
        String latinName = latinNameDialog.showAndWait().orElse("");

        if (!name.isEmpty() && !latinName.isEmpty()) {
            birdDatabase.addBird(name, latinName);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Bird added successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Name or Latin name cannot be empty!");
        }
    }

    private void observeBird() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Observe Bird");
        dialog.setHeaderText("Add an Observation");
        dialog.setContentText("Bird Name:");
        String name = dialog.showAndWait().orElse("");

        if (!name.isEmpty()) {
            boolean success = birdDatabase.observeBird(name);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Observation added!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Bird not found!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Bird name cannot be empty!");
        }
    }

    private void viewAllBirds() {
        ArrayList<Bird> birds = birdDatabase.getBirds();
        StringBuilder allBirds = new StringBuilder();

        for (Bird bird : birds) {
            allBirds.append(bird).append("\n");
        }

        showAlert(Alert.AlertType.INFORMATION, "All Birds", allBirds.toString());
    }

    private void deleteBird() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Bird");
        dialog.setHeaderText("Delete a Bird");
        dialog.setContentText("Bird Name:");
        String name = dialog.showAndWait().orElse("");

        if (!name.isEmpty()) {
            boolean success = birdDatabase.deleteBird(name);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Bird deleted successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Bird not found!");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Bird name cannot be empty!");
        }
    }

    private void searchBird() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Bird");
        dialog.setHeaderText("Search for a Bird");
        dialog.setContentText("Name or Latin Name");
        String name = dialog.showAndWait().orElse("");

        if (!name.isEmpty()) {
            for (Bird bird : birdDatabase.getBirds()) {
                if (bird.getName().equalsIgnoreCase(name) || bird.getLatinName().equalsIgnoreCase(name)) {
                    showAlert(Alert.AlertType.INFORMATION, "Bird Found", bird.toString());
                    return;
                }
            }
            showAlert(Alert.AlertType.ERROR, "Error", "Bird not found!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Search term cannot be empty!");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
