
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

	// two players who execute game and the type of players
	public void exeGame(int initialStones, int upperBound, NimPlayer playerArray1,
			NimPlayer playerArray2) {
		gameMethod(initialStones, upperBound, playerArray1, playerArray2);
	}

	//the method of playing games and winning game
	public void gameMethod(int initialStones, int upperBound, NimPlayer playerArray1, 
			               NimPlayer playerArray2) {

		/*
		 * initialize and define variable, playerOrder decides which player's turn,
		 * tempStonesNum decides when removeNum is error, instead of changing players,
		 * the player continues to move stones, removeNum, the number of removing
		 * stones, minStones, the minimum number of remaining stones and the upper limit
		 * of stones
		 */
		int playerOrder = 1;
		int removeNum = 0;
		int minStones = 0;
		
		/*
		 * when the number of initial stones greater than 0,playerOrder equal to
		 * 1,player1 removes stones,and determine if there is any input error,
		 * playerOrder equal to 0,player2 removes stones,and determine if there is any
		 * input error
		 */
		while (initialStones > 0) {	
			if (playerOrder == 1) {
				
				//player1 removes stones
				removeNum = playerArray1.removeStone(initialStones, upperBound, 
						                             playerArray1.getFamilyName());
				
				//the minimum number of remaining stones and the upper limit of stones
				minStones = playerArray1.min(initialStones, upperBound);

				//determine if there is any input error
				try
				{
					if ((removeNum <= 0) || (removeNum > minStones))
						throw new RemoveNumException("Invalid move. You must remove between 1 and " 
					                        + minStones + " stones.\n");	
					
					//after removing stones, the number of left stones
					initialStones = initialStones - removeNum;
					
					//next player turn
					playerOrder = 0;
				} 
				catch (RemoveNumException e) 
				{
					String message = e.getMessage();
					System.out.println(message);
				}
			} else {
				
				//player2 removes stones
				removeNum = playerArray2.removeStone(initialStones, upperBound, 
						                             playerArray2.getFamilyName());
				
				//the minimum number of remaining stones and the upper limit of stones
				minStones = playerArray2.min(initialStones, upperBound);

				//determine if there is any input error
				try 
				{
					if ((removeNum <= 0) || (removeNum > minStones))
						throw new RemoveNumException("Invalid move. You must remove between 1 and "
					                        + minStones + " stones.\n");
					
					//after removing stones, the number of left stones
					initialStones = initialStones - removeNum;

					//next player turn
					playerOrder = 1;
				}
				catch (RemoveNumException e)
				{
					String message = e.getMessage();
					System.out.println(message);
				}
			}
		}
		
		/*
		 * when the number of initial stones equal to 0,playerOrder is equal to
		 * 0,player2 wins,player1's the number of games played plus 1,player2's the
		 * number of games played plus 1 and the number of games won plus 1, playerOrder
		 * equal to 1,player1 wins,player2's the number of games played plus 1,player1's
		 * the number of games played plus 1 and the number of games won plus 1
		 */
		System.out.println("Game Over");
		if (playerOrder == 0) {
			System.out.print(playerArray2.getFamilyName() + ' ' + playerArray2.getGivenName() 
			                 + " wins!\n");
			playerArray1.increaseGamesPlayedNum();
			playerArray2.increaseGamesWonNum();
			playerArray2.increaseGamesPlayedNum();
		} else {
			System.out.print(playerArray1.getFamilyName() + ' ' + playerArray1.getGivenName() 
			                 + " wins!\n");
			playerArray2.increaseGamesPlayedNum();
			playerArray1.increaseGamesWonNum();
			playerArray1.increaseGamesPlayedNum();

		}
	}
}
