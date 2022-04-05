
/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer:  Prof. Rui Zhang
   Student:   Huan Cao
   StudentID: 985151
   Last changed: 5/6/2019
   ProjectB, NimGame class
*/
public class NimGame {
	// execute game
	int initialStones;
	int upperBound;
	NimPlayer userName1;
	NimPlayer userName2;
	int tempStonesNum;
	public void exeGame(int initialStones, int upperBound, NimPlayer userName1
			            , NimPlayer userName2) {
		int playerOrder = 1;

		/*
		 * when the number of initial stones greater than 0,playerOrder equal to
		 * 1,player1 removes stones,and determine if there is any input error, playerOrder 
		 * equal to 0,player2 removes stones,and determine if there is any input error
		 */
		do {
			tempStonesNum = initialStones;
			if (playerOrder == 1) {
				initialStones = userName1.removeStone(initialStones, upperBound);
				
				/*
				 * determine whether there is any input error,If the number of stones before
				 * moving the stone is greater than the number of stones after moving the stone,
				 * that is, the stone was successfully moved and there was no error input,
				 * another player will move the stone, otherwise the player will continue to
				 * move the stone 
				 */
				if (tempStonesNum > initialStones) {
					playerOrder = 0;
				}
			} else {
				initialStones = userName2.removeStone(initialStones, upperBound);
				
				/*
				 * determine whether there is any input error,the specific description is the same 
				 * as above
				 */
				if (tempStonesNum > initialStones) {
					playerOrder = 1;
				}
			}
		} while (initialStones > 0);
		System.out.println("Game Over");

		/*
		 * when the number of initial stones equal to 0,playerOrder not equal to
		 * 1,player2 wins,player1's the number of games played plus 1,player2's the
		 * number of games played plus 1 and the number of games won plus 1, playerOrder
		 * equal to 1,player1 wins,player2's the number of games played plus 1,player1's
		 * the number of games played plus 1 and the number of games won plus 1
		 */
		if (playerOrder != 1) {
			System.out.printf(userName2.getFamilyName() + ' ' + userName2.getGivenName() 
			                  + " wins!\n");
			userName1.increaseGamesPlayedNum();
			userName2.increaseGamesWonNum();
			userName2.increaseGamesPlayedNum();
		} else {
			System.out.printf(userName1.getFamilyName() + ' ' + userName1.getGivenName() 
			                  + " wins!\n");
			userName2.increaseGamesPlayedNum();
			userName1.increaseGamesWonNum();
			userName1.increaseGamesPlayedNum();
		}
	}
}