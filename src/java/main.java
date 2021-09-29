import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        boradPane pane = new boradPane(60);
        primaryStage.setTitle("British Chess");
        pane.setOnMouseClicked(new control(pane));
        primaryStage.setScene(new Scene(pane,480, 480));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
