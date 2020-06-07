package com.clientfx.game;

import java.io.IOException;

import com.clientfx.intro.Intro;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game
{
	private Scene scene;

	public Game() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Intro.class.getResource("/com/clientfx/game/Game.fxml"));
		VBox root = loader.load();
		scene = new Scene(root);
	}

	public void setScene(Stage stage) throws IOException
	{
		stage.setScene(scene);
	}
}