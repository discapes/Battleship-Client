package com.clientfx.game;

import java.io.PrintStream;

import com.clientfx.main.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class GameController
{

	@FXML private TextArea console;
	@FXML private GridPane leftgrid;
	@FXML private GridPane rightgrid;

	public void initialize()
	{
		PrintStream ps = new PrintStream(new Console(console));
		System.setOut(ps);
		System.setErr(ps);
		initGrids(5, 5);
	}

	void initGrids(int cols, int rows) {
		for (int i = 0; i < cols; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			leftgrid.getColumnConstraints().add(cc);
		}
		for (int i = 0; i < rows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			leftgrid.getRowConstraints().add(rc);
		}
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				Button button = new Button();
				GridPane.setConstraints(button, x, y);
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				leftgrid.getChildren().add(button);
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent arg0) {
						int x = GridPane.getColumnIndex(button);
						int y = GridPane.getRowIndex(button);
						System.out.println(x + "," + y);
						if(Main.getGame().shootMissile(x, y).size() > 0) {
							button.setStyle("-fx-background-color: grey");
						}
					}
				});
			}
		}
		
		
		for (int i = 0; i < cols; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			rightgrid.getColumnConstraints().add(cc);
		}
		for (int i = 0; i < rows; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			rightgrid.getRowConstraints().add(rc);
		}
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				Button button = new Button();
				button.setDisable(true);
				GridPane.setConstraints(button, x, y);
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				rightgrid.getChildren().add(button);
			}
		}
	}
	
	public void onButtonClick()
	{
		System.out.print("X");
	}
}
