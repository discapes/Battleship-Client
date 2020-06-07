package com.clientfx.game;

import java.io.PrintStream;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

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
	}

	public void onButtonClick()
	{
		System.out.print("X");
	}
}
