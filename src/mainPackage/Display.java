package mainPackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Display extends Application {
	Stage mainWindow;
	
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		mainWindow = arg0;
		
		//--> All vectors will be drown on this layout.
		Group canvas = new Group(); 
		
		TextField inputfield = new TextField();
		inputfield.setText("0");
		
		Button drawButton = new Button("Draw");
		Slider rotator = new Slider();
		rotator.setPrefSize(200, 40);
		rotator.getValue();
		rotator.setShowTickLabels(true);
		rotator.setShowTickMarks(true);
		rotator.setMin(-180);
		rotator.setMax(180);
		rotator.setValue(0);
		rotator.valueProperty().addListener(e-> exportValue(inputfield,canvas,rotator));
		
		draw(canvas, -1,rotator);
		drawButton.setOnAction(e->{
			//--> checking if the entered value can be converted to an integer
			exportValue(inputfield,canvas,rotator);
		});
		
		
		//--> This layout contains all controllers
		HBox controlLayout = new HBox(); 
		controlLayout.getChildren().add(inputfield);
		controlLayout.getChildren().add(drawButton);
		controlLayout.getChildren().add(rotator);
		HBox.setMargin(rotator, new Insets(0,0,0,30));
		HBox.setMargin(inputfield, new Insets(0,0,0,3));
		HBox.setMargin(drawButton, new Insets(0,0,0,3));
		//controlLayout.setAlignment(rotator,Pos.CENTER_RIGHT);
		
		//--> This layout contains both layouts: the controlLayout as well as the canvas; in other words, its the "parent-Layouts".
		BorderPane mainLayout = new BorderPane();
		mainLayout.setCenter(canvas);
		mainLayout.setBottom(controlLayout);
		
		Scene scene = new Scene(mainLayout, 500,500);
		mainWindow.setScene(scene);
		mainWindow.setHeight(600);
		mainWindow.show();
	}
	
	public void exportValue(TextField inputfield, Group canvas,Slider rotator) {
		if(isInteger(inputfield.getText())) { 
			int inputValue = Integer.parseInt(inputfield.getText())-1;
			if(inputValue < -1) {
				Alert.massage("", "Illigal input: negative values are not allowed!");
			}else if(inputValue > 6){
				Alert.massage("", "Illigal input: Input value is very heigh, this could cause a program crash! Try a value that is smaller than 8.");
			}else {
				draw(canvas,inputValue,rotator);
			}
		}else {
			if(inputfield.getText().equals("")) {
				Alert.massage("", "You have not entered any value!");
			}else {
				Alert.massage("", "Illigal input: Input value have to be an Integer");
			}
		}	
	}
	
	public boolean isInteger(String input) {
		char[] characters = input.toCharArray();
		for(int i = 0; i< characters.length; i++) {
			if(!(characters[i] >= 48 && characters[i] <= 57 )) {
				return false;
			}
		}
		return true;
	}
	
	private void draw(Group canvas, int rounds, Slider rotator) { 
		canvas.getChildren().clear();
		
		//--> producing vector objects:
		Vector v1= new Vector (new Point(10,400,null),400,60+rotator.getValue());
		Vector v2= new Vector (new Point(v1.endPoint.x,v1.endPoint.y,null),400,-60+rotator.getValue());
		Vector v3= new Vector (new Point(v2.endPoint.x,v2.endPoint.y,null),400,-180+rotator.getValue());
		
		//--> checking whether there should be any recursion
		if(rounds > -1) { 
			v1.buildRecursion(canvas, rounds);
			v2.buildRecursion(canvas, rounds);
			v3.buildRecursion(canvas, rounds);
		}else {
			//--> drawing vectors:
			v1.draw(canvas);
			v2.draw(canvas);
			v3.draw(canvas);
		}
	}

}
