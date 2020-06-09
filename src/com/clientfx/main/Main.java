package com.clientfx.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.clientfx.confirmbox.ConfirmBox;
import com.clientfx.intro.Intro;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import sdk.BattleshipGame;
import sdk.BattleshipGameInterface;

public class Main extends Application
{

	private static final String TITLE = "Battleship";
	private static BattleshipGameInterface game;
	public static BattleshipGameInterface getGame() { return game; }
	private Socket socket;
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws IOException
	{
		socket = new Socket("localhost", 4444);
		PrintWriter stringOut = new PrintWriter(socket.getOutputStream(), true);
		ObjectInputStream oIn = new ObjectInputStream(socket.getInputStream());
		
		game = new BattleshipGame(stringOut, oIn);
		mainStage.setTitle(TITLE);
		mainStage.setMinHeight(337);
		mainStage.setMinWidth(337);
		mainStage.setHeight(900);
		mainStage.setWidth(900);
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
	
	@Override
	public void stop() throws IOException {
		socket.close();
	}
}
