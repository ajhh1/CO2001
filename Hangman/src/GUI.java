

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI extends Application{

	private String wordToGuess = "";
	private Threading t = new Threading();
	public boolean parallel1 = false;
	private Label wordLength = new Label("");
	private String lettersGuessed = "";
	private Stage stage;
	private int numberOfIncorrectGuesses = 0;
	public Image image;
	public StringBuilder changer = new StringBuilder("");
	public boolean guess = false;
	
	public void setNewGame(){
		wordToGuess = "";
		t = new Threading();
		parallel1 = false;
		wordLength = new Label("");
		lettersGuessed = "";
		numberOfIncorrectGuesses = 0;
		changer = new StringBuilder("");
		guess = false;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		
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
		
		for(int x =0;x<wordToGuess.length();x++){
			changer.append("_ ");
		}
		wordLength.setUserData(changer);
	}
	
	private Scene openingScreen() {
		GridPane root = new GridPane();
		FlowPane buttons = new FlowPane();
		root.getStylesheets().add("file:style.css");
		root.setPrefHeight(200);
		
		Button parallel = new Button("Parallel");
		Button sequential = new Button("Sequential");
		Label opening = new Label("Alastair's Hangman Game!");
		Label writing = new Label();
		writing.setPrefHeight(200);
		writing.setText("Please select below how you would like the files to be read: ");
		
		
		buttons.getChildren().addAll(parallel,sequential);
		
		parallel.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent a){
				try {
					t.ParallelRun();
					parallel1 = true;
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
		root.getStylesheets().add("file:style.css");
		root.setPrefWidth(550);
		root.setPrefHeight(250);
		
		Label difficulty = new Label("Difficulty Selection:");
		new Label("Please select a difficulty: ");

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
				wordToGuess = t.fh.returnTheWord(Integer.parseInt(diff), parallel1);
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
		root.getStylesheets().add("file:style.css");
		CharSequence ch = "_ ";
		root.setPrefWidth(500);
		root.setPrefHeight(700);
		TextField tField1 = new TextField();
		guess = false;
		Button b = new Button("Play again?");
		
		b.setOnAction(e -> { 
				setNewGame();
				stage.setScene(openingScreen());
 		  });
		
		wordLength.setText(changer.toString());
	    GridPane gp1 = new GridPane();
	    tField1.setOnKeyReleased(e -> {
	    	guess = false;
	    	String isTheWordDifferent = wordLength.getUserData().toString();
	    	String g = tField1.getText().toLowerCase();
	        tField1.setText("");
	        ImageView man1 = new ImageView();
	        lettersGuessed += g;
	        wordLength.setText(fillingWord());
	        wordLength.setUserData(fillingWord());
	        
	        if(!wordLength.getUserData().toString().equals(isTheWordDifferent))
	        {
	        	guess = true;
	        }
	        
	        if(!guess){
	        	numberOfIncorrectGuesses++;
	        	loadImage();
	        	
	        	
	        	man1.setImage(image);
	        	root.setConstraints(man1, 1,3);
	    	    root.getChildren().add(man1);
	        }
	        
	    	  if(!wordLength.getText().contains(ch)){
		        	stage.setScene(winningScreen());
		        }
		        
	    	  if(numberOfIncorrectGuesses==9)
	        	{
	        		wordLength.setText("The word was " +wordToGuess);
	        		tField1.setVisible(false);
	        		gp1.setConstraints(b, 1,2);
	        		gp1.getChildren().add(b);
	        	}
		       
	       
	    });
	    		gp1.setConstraints(wordLength,1,1);
	    		gp1.setConstraints(tField1,1,2);
	    		gp1.getChildren().addAll(wordLength,tField1);
	    		root.setConstraints(gp1,1,1);
	    		root.getChildren().add(gp1);
	    return new Scene(root);
		}


	  public String fillingWord() {
	        String rValue = "";
	        for (char l: wordToGuess.toCharArray()) {
	            if (lettersGuessed.contains(Character.toString(l))) {
	                rValue += l + "";
	            } else {
	                rValue += "_ ";
	            }
	        }
	        return rValue;
	    }
	  
	  public Scene winningScreen(){
		  GridPane root = new GridPane();
		  root.getStylesheets().add("file:style.css");
		  ImageView win = new ImageView();
		  image = new Image("file:win.png");
		  Button b = new Button();
		  b.setText("Play again?");
		  b.setOnAction(e -> { 
			  stage.setScene(openingScreen());
		  });
		  win.setImage(image);
		  Text t = new Text();
		  t.setText(wordLength.getUserData().toString());
		  root.setConstraints(win,2,2);
		  root.setConstraints(wordLength,2,1);
		  root.setConstraints(b,3,1);
		  root.getChildren().addAll(win,wordLength);
		  return new Scene(root);
		  
	  }
	public static void main(String[]args)
	{
		launch();
	}
	 //1,3,5,7,9
    //0,1,2,3,4
/*    for (int y =1; y<wordLength.getUserData().toString().length();y=y+2) {
    	
        	if (wordToGuess.charAt(y-difference) == letterGuessed.charAt(0))
        	{
        		word.setCharAt(y, letterGuessed.charAt(0));
        		
        	}
        		
        	difference++;
        	
        } */
    
   // System.out.println(wordLength.getUserData().toString().length());
    //System.out.println(wordToGuess.toString().length());
      

}
