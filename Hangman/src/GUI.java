

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GUI extends Application{

	private String wordToGuess = "";
	private Threading t = new Threading();
	public boolean parallel = false;
	private Label yourWord = new Label("Your word: ");
	private Label wordLength = new Label();
	private String lettersGuessed = "";
	private Stage stage;
	private boolean guess;
	private int numberOfIncorrectGuesses = 0;
	public Image image;
	

	@Override
	public void start(Stage stage) throws Exception {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		System.out.println(primaryScreenBounds);
		
		this.stage = stage;
		Scene scene = openingScreen();
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void loadImage(){
		switch (numberOfIncorrectGuesses){
			case 1:
					image = new Image("file:1.png");
					break;
			case 2:
					image = new Image("file:2.png");
					break;
			case 3: 
					image = new Image("file:3.png");
					break;
			case 4:
					image = new Image("file:4.png");
					break;
			case 5:
					image = new Image("file:5.png");
					break;
			case 6:
					image = new Image("file:6.png");
					break;
			case 7:
					image = new Image("file:7.png");
					break;
			case 8:
					image = new Image("file:8.png");
					break;
			case 9:
					image = new Image("file:9.png");
					break;
		}
	}
	
	public void setWordLength(){
		for(char l: wordToGuess.toCharArray()){
			wordLength.getText().concat("_ ");
		}
	}
	
	private Scene openingScreen() {
		GridPane root = new GridPane();
		FlowPane buttons = new FlowPane();
		
		root.setPrefHeight(200);
		
		Button parallel = new Button("Parallel");
		Button sequential = new Button("Sequential");
		Label opening = new Label("Alastair's Hangman Game!");
		Label writing = new Label();
		writing.setPrefWidth(400);
		writing.setPrefHeight(190);
		writing.setText("Please select below how you would like the files to be read: ");
		
		
		buttons.getChildren().addAll(parallel,sequential);
		
		parallel.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent a){
				try {
					t.ParallelRun();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.setScene(selectDifficulty());
			}
		});
		
		sequential.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent a){
				try {
					t.SequentialRun();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.setScene(selectDifficulty());
			}
		});
		
		root.setConstraints(opening,5,1);
		root.setConstraints(writing,5,3);
		root.setConstraints(parallel, 4, 5);
		root.setConstraints(sequential, 6, 5);
		root.getChildren().addAll(opening,parallel,writing,sequential);
		return new Scene(root);
	}
	

	private Scene selectDifficulty() {
		GridPane root = new GridPane();
		
		root.setPrefWidth(400);
		
		Label difficulty = new Label("Difficulty Selection:");
		Label select = new Label("Please select a difficulty: ");
		/*final TextField userField = new TextField();*/

		ToggleGroup group = new ToggleGroup();
		RadioButton easy = new RadioButton("Easy");
		easy.setToggleGroup(group);
		easy.setUserData(1);
		easy.setSelected(true);
		
		RadioButton medium = new RadioButton("Medium");
		medium.setUserData(2);
		medium.setToggleGroup(group);
		
		RadioButton hard = new RadioButton("Hard");
		hard.setUserData(3);
		hard.setToggleGroup(group);
		
		RadioButton master = new RadioButton("Master");
		master.setUserData(4);
		master.setToggleGroup(group);
		
		Button confirm = new Button("Confirm");
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				String diff = group.getSelectedToggle().getUserData().toString();
				System.out.println(diff);
				wordToGuess = t.fh.returnTheWord(Integer.parseInt(diff), false);
				System.out.println("Difficulty selected: " + diff);
				System.out.println("Word: " + wordToGuess);
				setWordLength();
				stage.setScene(gamePlay());
			}
		});
		
		root.setConstraints(difficulty,2,1);
		//root.setConstraints(userField,2,2);
		root.setConstraints(easy,1,3);
		root.setConstraints(medium,1,4);
		root.setConstraints(hard,1,5);
		root.setConstraints(master,1,6);
		root.setConstraints(confirm,3,7);
		
		root.getChildren().addAll(difficulty,easy,medium,hard,master,/*userField,*/confirm);
		return new Scene(root);
	}
	
	private Scene gamePlay(){
		
		GridPane root = new GridPane();
		CharSequence ch = "_ ";
		root.setPrefWidth(1000);
		root.setPrefHeight(1000);
		TextField tField1 = new TextField();
	    VBox vbox1 = new VBox(50);
	    setWordLength();
	    vbox1.getChildren().addAll(wordLength, tField1);
	    tField1.setOnKeyReleased(e -> {
	        String g = tField1.getText().toLowerCase();
	        tField1.setText("");
	        ImageView man1 = new ImageView();
	        lettersGuessed += g;
	        if(wordLength.getText().contains(ch)){
	        	wordLength.setText(fillingWord());
	        }
	        
	        if(!guess)
	        	System.out.println("guess");
	        	numberOfIncorrectGuesses++;
	        	loadImage();
	        	man1.setImage(image);
	        	root.setConstraints(man1, 2,2);
	    	    root.getChildren().add(man1);
	        	
	        	
	       
	    });
	   
	    		root.setConstraints(vbox1,1,1);
	    		root.getChildren().add(vbox1);
	    return new Scene(root);
		}

	  public String fillingWord() {
        String returnWord = "";
        guess = false;
      //  setWordLength();
        for (char l: wordToGuess.toCharArray()) {
        	
            if (lettersGuessed.contains(Character.toString(l))) 
            {
                returnWord += l + " ";
                guess = true;
            } 
            else {
                returnWord += "_ ";
            }
        }
               
        return returnWord;
    }

	public static void main(String[]args)
	{
		launch();
	}

}
