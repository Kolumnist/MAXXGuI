/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 1 21.04.2022
 */
import java.util.*;
import java.math.*;
public class GameBoard {
	private char[] playerName = {'w','b','r','y'};
	private Player[] playerArray;

	public void createPlayer(int pNumber){
		for(int i = 0; i < pNumber; i++){
			playerArray[i] = new Player(playerName[i]);
		}
	}
}
