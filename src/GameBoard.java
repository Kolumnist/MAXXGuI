/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.math.*;
public class GameBoard extends JFrame {
	private char[] playerName = {'w','b','r','y'};
	private Player[] playerArray;
	private Field[][] boardFields;
	private static int count;

	public GameBoard(int pPlayerNumber){
		setSize(500, 500);
		setTitle("MaXX" + (count++));



		for(int j = 0; j < 8; j++){
			for(int k = 0; k < 8; k++){
				if((j==2&&k==2) || (j==2&&k==5) || (j==5&&k==2) || (j==5&&k==5)){

				}else{
					boardFields[j][k] = new Field(j, k);
					add(boardFields[j][k]);
				}


			}
		}

	}

	public void createPlayer(int pNumber){
		for(int i = 0; i < pNumber; i++){
			playerArray[i] = new Player(playerName[i]);
		}
	}
}
