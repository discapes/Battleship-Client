package com.clientfx.game;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

class Console extends OutputStream
{
	private TextArea output;

	Console(TextArea ta)
	{
		this.output = ta;
	}

	@Override
	public void write(int i) throws IOException
	{
		output.appendText(String.valueOf((char) i));
	}

}