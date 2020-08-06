package mainPackage;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Alert {
	public static void massage(String title, String content) {
		Stage window = new Stage();
		
		Label massage = new Label(content);
		
		Button okButton = new Button("OK");
		okButton.setOnAction(e-> window.close());
		
		VBox layout = new VBox();
		layout.getChildren().addAll(massage,okButton);
		layout.setAlignment(Pos.CENTER);
		VBox.setMargin(massage, new Insets(5,5,5,5));
		VBox.setMargin(okButton, new Insets(5,5,5,5));
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
