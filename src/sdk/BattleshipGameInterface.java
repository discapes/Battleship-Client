package sdk;

import java.util.HashSet;

public interface BattleshipGameInterface
{
	HashSet<int[]> shootMissile(int x, int y);

	String getCurrentMissileName();
	String getMissileNameAt(int index);
	int getNumOfMissile(int missileType);
	void setCurrentMissile(int missileType);
	int getTotalNumOfMissiles();
	
	String getMissileSiloType();

	int getMissileIDAt(int index);

	int getCurrentMissileID();
}
