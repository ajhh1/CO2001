

import java.awt.Checkbox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GUI extends Application implements EventHandler<ActionEvent>{

	//ToggleGroup group = new ToggleGroup();

	private Label label;
	private Button a,b,c;
	private FlowPane box;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		System.out.println(primaryScreenBounds);
		
		label = new Label("Hello world!");
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
		
		//ToggleGroup group = new ToggleGroup();
		a = new Button("Parallel");
		//button1.setToggleGroup(group);
		//button1.setSelected(true);
		
		b= new Button("Sequential");
		/*button2.setToggleGroup(group);*/
		
		a.setOnAction(this);
		b.setOnAction(this);
		
		
		/*a.setPrefSize(100, 40);
		b.setPrefSize(100, 40);
		c.setPrefSize(100, 40);
		
		a.setMinSize(100, 40);
		b.setMinSize(100, 40);
		c.setMinSize(100, 40);*/
		
		box = new FlowPane();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(a, b);
		
		
				
		box.getChildren().add(0, label);
		
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
		Threading t = new Threading();
		
		if(pressedButton.getText().contentEquals("Parallel")){
			try {
				t.ParallelRun();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(pressedButton.getText().equals("Sequential")){
			try {
				t.SequentialRun();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
