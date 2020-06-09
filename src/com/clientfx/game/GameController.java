package com.clientfx.game;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;

import com.clientfx.main.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import sdk.Missile;

public class GameController
{

	@FXML
	private TextArea console;
	@FXML
	private GridPane leftgrid;
	@FXML
	private GridPane rightgrid;
	@FXML
	private AnchorPane missiles;
	private Label[] missileCounts;
	private Button[][] buttons;
	private Button[] missileButtons;

	public void initialize()
	{
		initGrids(Main.getGame().getCols(), Main.getGame().getRows());
		initMissiles(Main.getGame().getMissileSiloType());
		PrintStream ps = new PrintStream(new Console(console));
		System.setOut(ps);
		System.setErr(ps);
	}

	private void initMissiles(String missileSiloType)
	{
		if (missileSiloType.equals("Ordered"))
		{

		} else if (missileSiloType.equals("Unordered"))
		{
			VBox missileList = new VBox();

			missileCounts = new Label[Missile.values().length];
			missileButtons = new Button[Missile.values().length];
			for (Missile missile : Missile.values())
			{
				int i = missile.getValue();
				HBox missileBox = new HBox();
				missileBox.setSpacing(10.f);
				VBox.setVgrow(missileBox, Priority.ALWAYS);
				Button button = new Button(missile.getName());
				missileButtons[i] = button;
				button.setPadding(Insets.EMPTY);
				missileCounts[i] = new Label(Integer.toString(Main.getGame().getNumOfMissile(i)));
				missileCounts[i].setStyle("-fx-text-fill: azure; -fx-font-size: 18.0");
				button.setPrefSize(80, 20.f);
				button.setOnAction((event) -> {
				    Main.getGame().setCurrentMissile(i);
				    for(int j = 0; j < missileButtons.length; j++) {
				    	missileButtons[j].setDisable(false); 
				    }
				    button.setDisable(true);
				});
				missileBox.getChildren().add(button);
				missileBox.getChildren().add(missileCounts[i]);
				missileList.getChildren().add(missileBox);
			}

			AnchorPane.setBottomAnchor(missileList, 0.d);
			AnchorPane.setTopAnchor(missileList, 0.d);
			AnchorPane.setLeftAnchor(missileList, 0.d);
			AnchorPane.setRightAnchor(missileList, 0.d);

			missiles.getChildren().add(missileList);
		} else
		{
			System.out.print("error: " + missileSiloType);
		}
	}

	void initGrids(int cols, int rows)
	{
		buttons = new Button[cols][rows];
		for (int i = 0; i < cols; i++)
		{
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			leftgrid.getColumnConstraints().add(cc);
		}
		for (int i = 0; i < rows; i++)
		{
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			leftgrid.getRowConstraints().add(rc);
		}
		for (int x = 0; x < cols; x++)
		{
			for (int y = 0; y < rows; y++)
			{
				Button button = new Button();
				GridPane.setConstraints(button, x, y);
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				button.setPadding(Insets.EMPTY);
				button.setMinSize(0.f, 0.f);
				leftgrid.getChildren().add(button);
				buttons[x][y] = button;
				button.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0)
					{
						int x = GridPane.getColumnIndex(button);
						int y = GridPane.getRowIndex(button);
						HashSet<int[]> hits = Main.getGame().shootMissile(x, y);
						Iterator<int[]> iter = hits.iterator();
						for (int i = 0; i < hits.size(); i++)
						{
							int[] hit = iter.next();
							buttons[hit[0]][hit[1]].setStyle("-fx-background-color: azure");
						}
						int id = Main.getGame().getCurrentMissileID();
						missileCounts[id].setText(Integer.toString(Main.getGame().getNumOfMissile(id)));
					}
				});
			}
		}

		for (int i = 0; i < cols; i++)
		{
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHgrow(Priority.ALWAYS);
			rightgrid.getColumnConstraints().add(cc);
		}
		for (int i = 0; i < rows; i++)
		{
			RowConstraints rc = new RowConstraints();
			rc.setVgrow(Priority.ALWAYS);
			rightgrid.getRowConstraints().add(rc);
		}
		for (int x = 0; x < cols; x++)
		{
			for (int y = 0; y < rows; y++)
			{
				Button button = new Button();
				button.setDisable(true);
				button.setMinSize(0.f, 0.f);
				button.setPadding(Insets.EMPTY);
				GridPane.setConstraints(button, x, y);
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				rightgrid.getChildren().add(button);
			}
		}
	}
}
