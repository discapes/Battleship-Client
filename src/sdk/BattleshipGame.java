package sdk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashSet;

public class BattleshipGame implements BattleshipGameInterface
{
	private PrintWriter stringOut;
   	private ObjectInputStream oIn;
	
	public BattleshipGame(PrintWriter stringOut, ObjectInputStream oIn)
	{
		this.stringOut = stringOut;
		this.oIn = oIn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashSet<int[]> shootMissile(int x, int y)
	{
	   	stringOut.println("shootMissile int:"+x+" int:"+y);
	   	HashSet<int[]> hits = new HashSet<int[]>();
		try
		{
			hits = (HashSet<int[]>) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		if (hits == null) { 
			System.out.println("null");
			return new HashSet<int[]>();
		}
	   	return hits;
	}

	@Override
	public String getCurrentMissileName()
	{
	   	stringOut.println("getCurrentMissileName");
	   	String missile = "error";
		try
		{
			missile = (String) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return missile;
	}

	@Override
	public String getMissileNameAt(int index)
	{
	   	stringOut.println("getMissileNameAt int:" + index);
	   	String missile = "error";
		try
		{
			missile = (String) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return missile;
	}

	@Override
	public int getNumOfMissile(int missileType)
	{
	   	stringOut.println("getNumOfMissile int:" + missileType);
	   	int num = -1;
		try
		{
			num = (int) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return num;
	}
	
	@Override
	public int getMissileIDAt(int index)
	{
	   	stringOut.println("getMissileIDAt int:" + index);
	   	int num = -1;
		try
		{
			num = (int) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return num;
	}
	
	@Override
	public int getCurrentMissileID()
	{
	   	stringOut.println("getCurrentMissileID");
	   	int num = -1;
		try
		{
			num = (int) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return num;
	}

	@Override
	public void setCurrentMissile(int missileType)
	{
	   	stringOut.println("setCurrentMissile int:" + missileType);
		try
		{
			oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalNumOfMissiles()
	{
	   	stringOut.println("getTotalNumOfMissiles");
	   	int num = -1;
		try
		{
			num = (int) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return num;
	}

	@Override
	public String getMissileSiloType()
	{
	   	stringOut.println("getMissileSiloType");
	   	String type = "error";
		try
		{
			type = (String) oIn.readObject();
		} catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
	   	return type;
	}

}
