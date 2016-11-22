

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GUI extends Application implements EventHandler<ActionEvent>{

	//ToggleGroup group = new ToggleGroup();

	private Label label;
	//private Button a,b,c;
	private FlowPane box;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		System.out.println(primaryScreenBounds);
		
		label = new Label("Hangman");
		label.setFont(new Font(20));
		label.setAlignment(Pos.TOP_CENTER);
		
		/*Hyperlink link = new Hyperlink("Open a window");
		link.setFont(new Font(50));
		
		Image imageOk = new Image("http://www.skerrigan.co.uk/images/banner.png");*/
		/*
		a = new Button("Ok");
		b = new Button("Cancel");
		c = new Button("Change");
		
		a.setOnAction(this);
		
		b.setOnAction(this);
		*/
		//c.setOnAction(this);
		
		//box = new StackPane();
		
		ToggleGroup group = new ToggleGroup();
		RadioButton a = new RadioButton("Parallel");
		a.setToggleGroup(group);
		a.setSelected(true);
		
		RadioButton b = new RadioButton("Sequential");
		b.setToggleGroup(group);
		
		Button c = new Button("Submit");
		c.setOnAction(this);
		
		box = new FlowPane(a,b,c);
		box.setAlignment(Pos.CENTER);
		
		
		
		Scene scene = new Scene(box);
		
		stage.setTitle("Alastair's Hangman Game");
		
		stage.setWidth(400);
		stage.setHeight(400);
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void main(String[]args)
	{
		launch();
	}


	@Override
	public void handle(ActionEvent event) {
		
		Button pressedButton = (Button)event.getSource();
		
		if(pressedButton.getText().contentEquals("Submit")){
			System.out.println("Parallel was selected");
		}
		if(pressedButton.getText().equals("Sequential")){
			System.out.println("Sequential was pressed");
			
		}
		
	}
}



//
