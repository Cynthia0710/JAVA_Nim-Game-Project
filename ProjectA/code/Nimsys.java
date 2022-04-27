/* ********************************************************
 * Name:Huan Cao
 * Student ID:985151
 * last changed date:04/03/2019
 */

import java.util.Scanner;

public class Nimsys {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
        System.out.println("Welcome to Nim");
	    System.out.println();
	    System.out.println("Please enter Player 1's name:");//Create two player names
	    String name1 = scanner.next() ;
	    NimPlayer nimplayer1 = new NimPlayer(name1);
	    System.out.println();
	    System.out.println("Please enter Player 2's name:");
	    String name2 = scanner.next();
	    NimPlayer nimplayer2 = new NimPlayer(name2);
	    System.out.println();
	    int gamebeginning = 0;
	    
	    //Create a game loop
	    do {
		    System.out.println("Please enter upper bound of stone removal:");//upper bound of stone
			System.out.println();
		    int stonebound = scanner.nextInt();
		    System.out.println("Please enter initial number of stones:");//initial number of stones
			System.out.println();
		    int initialstone = scanner.nextInt();
			int numberOfStones = initialstone;
		    while (numberOfStones != 0) {//Calculate whether the remaining stones are zero
    		    numberOfStones=nimplayer1.removeStone(numberOfStones);//The player1 takes the stone
			    if (numberOfStones == 0) {//Judge player2 wins the game
			    	nimplayer2.win(name2);
			    }
			    else {
				    numberOfStones=nimplayer2.removeStone(numberOfStones);//The player2 takes the stone
				    if(numberOfStones == 0) {//Judge player1 wins the game
				    	nimplayer1.win(name1);
				    }
			    }
				
		    }
		    System.out.println();
		    System.out.println("Do you want to play again (Y/N):");//Decide whether to continue the game
		    String answer = scanner.next();
	        if (answer.equals("Y")) {
	        	gamebeginning = 0;
	        }
		    else {
	    	    gamebeginning = 1;
	        } 
	    }while (gamebeginning == 0 );
	}

}
