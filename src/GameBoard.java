package MAXX;
/**This is the game board for MAXX
 *
 * @version 1 20.12.2021
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 */
import java.util.*;
import java.math.*;
public class GameBoard {

	private int x = 8;	//Length of the game board in x direction
	private int y = 8;	//Length of the game board in y direction
	private int playerPosX = -1; //Player position
	private int playerPosY = -1;
	private BigInteger numerator, denominator; //Variable for the fractions
	private boolean notDoubleFraction;
	private Random rnd = new Random();	//Random generator to generate numbers for the fractions
	private Fraction fraction = new Fraction(new BigInteger("0"),new BigInteger("1"));	//Fraction object
	private Fraction[][] fractionBoard;	//Initialization of the board as an two dimensional Fraction array
	private String[][] board;	//Initialization of the game board as an two dimensional String array

	public GameBoard() {}	//default constructor

	//constructor with variables to define the size
	public GameBoard(int pX, int pY) {	
		x = pX;
		y = pY;
	}

	//constructor with x & y variables and player array to directly create a board
	public GameBoard(int pX, int pY, Player[] pPlayer) {	
		x = pX;
		y = pY;
		createBoard(pPlayer);
	}

	//constructor with x & y variables and two players to directly create a board
	public GameBoard(int pX, int pY, Player pPlayer1, Player pPlayer2) {	
		x = pX;
		y = pY;
		createBoard(pPlayer1, pPlayer2);
	}

	//constructor with x & y variables and two players to directly create a board
	public GameBoard(int pX, int pY, Player pPlayer1, Player pPlayer2, Player pPlayer3, Player pPlayer4) {	
		x = pX;
		y = pY;
		createBoard(pPlayer1, pPlayer2, pPlayer3, pPlayer4);
	}

	//Creates the board with fractions and the players using an player array
	public void createBoard(Player[] pPlayer) {
		fractionBoard = new Fraction [x] [y];
		board = new String [x] [y];

		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				while(notDoubleFraction == false) {
					numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the numerator
					denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the denominator

					fraction = new Fraction(numerator, denominator);
					fraction = fraction.shorten();	//Shorting the fraction

					BigInteger comp = new BigInteger("9");
					if(fraction.nenner.compareTo(comp) > 0 && fraction.zaehler.compareTo(comp) > 0) {
						//Testing if the fraction is an integer
						if(fraction.isInteger() == false) {
							fractionBoard[i][j] = fraction;	//Filling fractionBoard with the generated fractions
							board[i][j] = fraction.toString(); //Filling board with the generated fractions as String
							notDoubleFraction = true; //Canceling of the while loop
						}
					}
				}
				notDoubleFraction = false;
			}
		}
		//Drawing the players into the board
		fraction = new Fraction(new BigInteger("0"),new BigInteger("1"));
		for(int k = 0; k < pPlayer.length; k++) {
			board[pPlayer[k].getX_pos()][pPlayer[k].getY_pos()] = pPlayer[k].toString();
			fractionBoard[pPlayer[k].getX_pos()][pPlayer[k].getY_pos()] = fraction;
		}
	}

	//Creates the board with fractions and two players
	public void createBoard(Player pPlayer1, Player pPlayer2) {
		fractionBoard = new Fraction [x] [y];
		board = new String [x] [y];

		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				while(notDoubleFraction == false) {
					numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the numerator
					denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the denominator

					fraction = new Fraction(numerator, denominator);
					fraction = fraction.shorten();	//Shorting the fraction

					BigInteger comp = new BigInteger("9");
					if(fraction.nenner.compareTo(comp) > 0 && fraction.zaehler.compareTo(comp) > 0) {
						//Testing if the fraction is an integer
						if(fraction.isInteger() == false) {
							fractionBoard[i][j] = fraction;	//Filling fractionBoard with the generated fractions
							board[i][j] = fraction.toString(); //Filling board with the generated fractions as String
							notDoubleFraction = true; //Canceling of the while loop
						}
					}
				}
				notDoubleFraction = false;
			}
		}
		//Drawing the players into the board
		fraction = new Fraction(new BigInteger("0"),new BigInteger("1"));
		board[pPlayer1.getX_pos()][pPlayer1.getY_pos()] = pPlayer1.toString();
		fractionBoard[pPlayer1.getX_pos()][pPlayer1.getY_pos()] = fraction;
		board[pPlayer2.getX_pos()][pPlayer2.getY_pos()] = pPlayer2.toString();
		fractionBoard[pPlayer2.getX_pos()][pPlayer2.getY_pos()] = fraction;
	}

	//Creates the board with fractions and four players
	public void createBoard(Player pPlayer1, Player pPlayer2, Player pPlayer3, Player pPlayer4) {
		fractionBoard = new Fraction [x] [y];
		board = new String [x] [y];

		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				while(notDoubleFraction == false) {
					numerator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the numerator
					denominator = BigInteger.valueOf(rnd.nextInt(10, 1000));	//Random number for the denominator

					fraction = new Fraction(numerator, denominator);
					fraction = fraction.shorten();	//Shorting the fraction

					BigInteger comp = new BigInteger("9");
					if(fraction.nenner.compareTo(comp) > 0 && fraction.zaehler.compareTo(comp) > 0) {
						//Testing if the fraction is an integer
						if(fraction.isInteger() == false) {
							fractionBoard[i][j] = fraction;	//Filling fractionBoard with the generated fractions
							board[i][j] = fraction.toString(); //Filling board with the generated fractions as String
							notDoubleFraction = true; //Canceling of the while loop
						}
					}
				}
				notDoubleFraction = false;
			}
		}
		//Drawing the players into the board
		fraction = new Fraction(new BigInteger("0"),new BigInteger("1"));
		board[pPlayer1.getX_pos()][pPlayer1.getY_pos()] = pPlayer1.toString();
		fractionBoard[pPlayer1.getX_pos()][pPlayer1.getY_pos()] = fraction;
		
		board[pPlayer2.getX_pos()][pPlayer2.getY_pos()] = pPlayer2.toString();
		fractionBoard[pPlayer2.getX_pos()][pPlayer2.getY_pos()] = fraction;
		
		board[pPlayer3.getX_pos()][pPlayer3.getY_pos()] = pPlayer3.toString();
		fractionBoard[pPlayer3.getX_pos()][pPlayer3.getY_pos()] = fraction;
		
		board[pPlayer4.getX_pos()][pPlayer4.getY_pos()] = pPlayer4.toString();
		fractionBoard[pPlayer4.getX_pos()][pPlayer4.getY_pos()] = fraction;
	}

	//Sets the player on his new position on the board and adds the fraction to his value
	public void setPlayer(Player player) {
		playerPosY = player.getY_pos();
		playerPosX = player.getX_pos();

		board[playerPosX][playerPosY] = player.toString();
		player.player_value = player.player_value.add(fractionBoard[playerPosX][playerPosY]);
		fractionBoard[playerPosX][playerPosY] = fraction;

		board[playerPosX - player.getX_v()][playerPosY - player.getY_v()] = " x";
	}

	//Prints the board in the console
	public void drawBoard() {
		BigInteger comp1 = new BigInteger("100");
		BigInteger comp2 = new BigInteger("1");
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				if(fractionBoard[i][j].nenner.compareTo(comp2) == 0 && fractionBoard[i][j].zaehler.compareTo(comp2) == -1) {
					System.out.print("   " + board[i][j] + "   ");
				}else if(fractionBoard[i][j].nenner.compareTo(comp1) == -1  && fractionBoard[i][j].zaehler.compareTo(comp1) == -1) {
					System.out.print("  " + board[i][j] + " ");
				}else if(fractionBoard[i][j].nenner.compareTo(comp1) == -1  && fractionBoard[i][j].zaehler.compareTo(comp1) == 1) {
					System.out.print(" " + board[i][j] + " ");
				}else if(fractionBoard[i][j].nenner.compareTo(comp1) == 1  && fractionBoard[i][j].zaehler.compareTo(comp1) == -1) {
					System.out.print("  " + board[i][j]);
				}else if(fractionBoard[i][j].nenner.compareTo(comp1) == -1  && fractionBoard[i][j].zaehler.compareTo(comp1) == 0) {
					System.out.print(" " + board[i][j] + "  ");
				}else if(fractionBoard[i][j].nenner.compareTo(comp1) == 0  && fractionBoard[i][j].zaehler.compareTo(comp1) == -1) {
					System.out.print("  " + board[i][j]);
				}else{
					System.out.print(" " + board[i][j]);
				}
			}
			System.out.print("\n\n");
		}
	}

}
