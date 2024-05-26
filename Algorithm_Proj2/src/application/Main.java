package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	
	//========================================= start method ==============================================================
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Font font = Font.font("Times New Roman", FontWeight.BOLD, 14);
		
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: grey; -fx-border-color: red; -fx-border-radius: 5;");

		Button compress = new Button("Compress a File");
		compress.setPrefSize(180, 50);
		compress.setStyle("-fx-background-color: white; -fx-border-color: blue; -fx-border-radius: 5; -fx-background-radius: 20em;");
		pane.getChildren().add(compress);
		compress.setTranslateX(70);
		compress.setTranslateY(50);
		compress.setFont(font);
		compress.setTextFill(Color.BLUE);

		Button decompress = new Button("Decompress a File");
		decompress.setPrefSize(180, 50);
		decompress.setStyle("-fx-background-color: white; -fx-border-color: blue; -fx-border-radius: 5; -fx-background-radius: 20em;");
		pane.getChildren().add(decompress);
		decompress.setTranslateX(330);
		decompress.setTranslateY(50);
		decompress.setFont(font);
		decompress.setTextFill(Color.BLUE);

		TextArea ta = new TextArea();
		ta.setPrefSize(440, 300);
		pane.getChildren().add(ta);
		ta.setTranslateX(70);
		ta.setTranslateY(120);
		ta.setFont(new Font(15));
		
	//======================================= Actions Buttons =============================================================

		compress.setOnAction(e -> { // Button Compress 
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			try {
				Huffman.compress(file);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}

			Button statistics = new Button("Show statistics");
			statistics.setPrefSize(180, 50);
			statistics.setStyle("-fx-background-color: white; -fx-border-color: blue; -fx-border-radius: 5; -fx-background-radius: 20em;");
			pane.getChildren().add(statistics);
			statistics.setTranslateX(250);
			statistics.setTranslateY(430);
			statistics.setFont(font);

			statistics.setOnAction(s -> {
				ta.appendText("File path: " + file.getPath() + "\nCompressed file path: " + Huffman.outFileName
						+ "\n\nASCII\tCharacter\t\tFrequency\tHuffCode\n");
				
				for (int k = 0; k < Huffman.huffCodeArray.length; k++) {
					if((int)Huffman.huffCodeArray[k].character==10 || (int)Huffman.huffCodeArray[k].character==9)
						continue;
					ta.appendText(String.valueOf((int) Huffman.huffCodeArray[k].character) + "\t\t  "
							+ Huffman.huffCodeArray[k].character + "\t\t\t"
							+ String.valueOf(Huffman.huffCodeArray[k].counter) + "\t\t\t"
							+ Huffman.huffCodeArray[k].huffCode+"\n");
				}
			});
		});
		
		decompress.setOnAction(e->{ // Button DeCompress
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			Huffman.deCompress(file);
		});

		Scene scene = new Scene(pane, 600, 500);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Huffman Project");
		stage.show();
	}

	//================================================= main method =======================================================
	
	public static void main(String[] args) {
		launch(args);
	}
}