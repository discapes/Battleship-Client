package com.clientfx.intro;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Intro
{

	private Scene scene;
	private IntroController controller;

	public Intro() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(Intro.class.getResource("/com/clientfx/intro/Intro.fxml"));
		VBox root = loader.load();
		scene = new Scene(root);
		controller = loader.<IntroController>getController();
	}

	public void setScene(Stage stage) throws IOException
	{
		controller.setStage(stage);
		stage.setScene(scene);
	}

}