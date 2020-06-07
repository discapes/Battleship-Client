package com.clientfx.intro;

import java.io.IOException;

import com.clientfx.game.Game;

import javafx.stage.Stage;

public class IntroController
{

	private Stage stage;

	void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public void introButtonClicked() throws IOException
	{
		Game game = new Game();
		game.setScene(stage);
	}
}
