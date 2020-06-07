package com.clientfx.main;

import java.io.IOException;

import com.clientfx.confirmbox.ConfirmBox;
import com.clientfx.intro.Intro;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

	private static final String TITLE = "Battleship";

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws IOException
	{
		mainStage.setTitle(TITLE);
		ConfirmBox confirmBox = new ConfirmBox();
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			try
			{
				if (confirmBox.ask("Exit?", "Are you sure you want to exit?"))
					mainStage.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		});

		Intro intro = new Intro();
		intro.setScene(mainStage);

		mainStage.show();
	}
}
