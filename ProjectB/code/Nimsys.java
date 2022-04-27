
/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer:  Prof. Rui Zhang
   Student:   Huan Cao
   StudentID: 985151
   Last changed: 5/6/2019
   ProjectB, NimSys class
*/
import java.util.Scanner;

public class Nimsys {

	static Scanner scanner = new Scanner(System.in);
	static Nimsys nimSys = new Nimsys();
	NimPlayer[] playerArray = new NimPlayer[100];
	public static void main(String[] args) {
		String[] commandInfo = new String[2];
		String[] nameInfo = new String[4];
		
		System.out.printf("Welcome to Nim\n");
		while (true) {
			System.out.printf("\n");
			System.out.printf("$");

			/*
			 * splits the command into a player command (commandInfo[0]) and name
			 * information (commandInfo[1]) by ' ', and then splits the player information
			 * (commandInfo[1]) by ','.
			 */
			String command = scanner.nextLine();
			commandInfo[0] = command;
			commandInfo[1] = null;
			if (command != null) {
				for (int i = 0; i < command.length() - 1; i++) {
					if (command.charAt(i) == ' ' && command.charAt(i + 1) != ' ') {
						commandInfo = command.split(" ");
						for (int j = 0; j < commandInfo[1].length(); j++) {
							if (commandInfo[1].charAt(j) == ',') {
								nameInfo = commandInfo[1].split(",");
								break;
							}
						}
						break;
					}
				}
			}

			/*
			 * If the player command (commandInfo[0]) is "addplayer", execute add
			 * player(addPlayer(nameInfo))
			 */
			if (commandInfo[0].equals("addplayer")) {
				nimSys.addPlayer(nameInfo);
			}

			/*
			 * If the player command (commandInfo[0]) is "removeplayer", execute remove
			 * player (removePlayer() or removePlayer(commandInfo[1]))
			 */
			if (commandInfo[0].equals("removeplayer")) {
				if (commandInfo[1] == null) {
					nimSys.removePlayer();
				} else {
					nimSys.removePlayer(commandInfo[1]);
				}
			}

			/*
			 * If the player command (commandInfo[0]) is "displayplayer", execute display
			 * player information (displayPlayer() or diaplayPlayer(commandInfo[1]))
			 */
			if (commandInfo[0].equals("displayplayer")) {
				if (commandInfo[1] == null) {
					nimSys.displayPlayer();
				} else {
					nimSys.displayPlayer(commandInfo[1]);
				}
			}

			/*
			 * If the player command (commandInfo[0]) is "editplayer",execute edit player
			 * (editPlayer(nameInfo))
			 */
			if (commandInfo[0].equals("editplayer")) {
				nimSys.editPlayer(nameInfo);
			}

			/*
			 * If the player command (commandInfo[0]) is "resetstats", execute reset player
			 * information (resetStats() or resetStats(commandInfo[1]))
			 */
			if (commandInfo[0].equals("resetstats")) {
				if (commandInfo[1] == null) {
					nimSys.resetStats();
				} else {
					nimSys.resetStats(commandInfo[1]);
				}
			}

			/*
			 * If the player command (commandInfo[0]) is "rankings",sort players by winning
			 * percentage (rankings() or commandInfo[1].equals("desc") or
			 * commandInfo[1].equals("asc")
			 */
			if (commandInfo[0].equals("rankings")) {
				if (commandInfo[1] == null) {
					nimSys.rankings();
				} else if (commandInfo[1].equals("desc")) {
					nimSys.rankings();
				} else if (commandInfo[1].equals("asc")) {
					nimSys.rankings(commandInfo[1]);
				}
			}

			/*
			 * If the player command (commandInfo[0]) is "rankings ",sort players by winning
			 * percentage (rankings() or commandInfo[1].equals("desc") or
			 * commandInfo[1].equals("asc")
			 */
			if (commandInfo[0].equals("rankings ")) {
				if (commandInfo[1] == null) {
					nimSys.rankings();
				} else if (commandInfo[1].equals("desc")) {
					nimSys.rankings();
				} else if (commandInfo[1].equals("asc")) {
					nimSys.rankings(commandInfo[1]);
				}
			}

			// If the player command (commandInfo[0]) is "startgame", two players start the game
			if (commandInfo[0].equals("startgame")) {
				nimSys.startGame(nameInfo);
			}

			// If the player command (commandInfo[0]) is "exit", end the game
			if (commandInfo[0].equals("exit")) {
				System.out.printf("\n");
				System.exit(0);
			}
		}
	}
	
	/*
	 * addPlayer(String[] nameInfo):player's name information input, if exist, the player 
	 * already exist,if not exist, add the player 
	 */
	private void addPlayer(String[] nameInfo) {
		NimPlayer nimPlayer = new NimPlayer(nameInfo[0], nameInfo[1], nameInfo[2]);

		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				if (playerArray[i].getUserName().equals(nimPlayer.getUserName())) {
					System.out.println("The player already exists.");
					return;
				}
			}
		}
		for (int j = 0; j < playerArray.length; j++) {
			if (playerArray[j] == null) {
				playerArray[j] = nimPlayer;
				break;
			}
		}
	}

	// removePlayer():no player's username input, then delete all players	
	private void removePlayer() {
		System.out.println("Are you sure you want to remove all players? (y/n)");
		String answer = scanner.nextLine();
		if (answer.equals("y")) {
			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] != null) {
					playerArray[i] = null;
				}
			}
		}
	}
	
	/*
	 * removePlayer(String userName):player's username input, if exist, delete this player, if not
	 * exist, the player not exist
	 */
	private void removePlayer(String userName) {
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				if (playerArray[i].getUserName().equals(userName)) {
					playerArray[i] = null;
					return;
				}
			}
		}
		System.out.println("The player does not exist.");
	}

	
	/*
	 * editPlayer(String[] nameInfo):editPlayerplayer's name information input, if exist, 
	 * edit the player's name information  
	 */ 
	private void editPlayer(String[] nameInfo) {
		NimPlayer nimPlayer = new NimPlayer(nameInfo[0], nameInfo[1], nameInfo[2]);
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				if (playerArray[i].getUserName().equals(nimPlayer.getUserName())) {
					playerArray[i].setFamilyName(nameInfo[2]);
					playerArray[i].setGivenName(nameInfo[1]);
					return;
				}
			}
		}
		System.out.println("The player does not exist.");
	}

	/*
	 * resetStats():no player's username input, reset all players' gamesplayed and
	 * gameswon information
	 */
	private void resetStats() {
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		String answer = scanner.nextLine();
		if (answer.equals("y")) {
			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] == null) {
					continue;
				} else {
					playerArray[i].setGamesPlayedNum(0);
					playerArray[i].setGamesWonNum(0);
					playerArray[i].resetWonRate();
				}
			}
		}
	}

	/*
	 * resetStats(String userName):player's username input, if exist, reset this player's 
	 * gamesplayed and gameswon information
	 */
	private void resetStats(String userName) {
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				if (playerArray[i].getUserName().equals(userName)) {
					playerArray[i].setGamesPlayedNum(0);
					playerArray[i].setGamesWonNum(0);
					playerArray[i].resetWonRate();
					return;
				}
			}
		}
		System.out.println("The player does not exist.");
	}

	/*
	 * displayPlayer():no player's username input, then display all players'
	 * information, sorted by username in ascending order
	 */
	private void displayPlayer() {
		nimSys.sortUserName();
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] != null) {
				System.out.println(playerArray[i].getUserName() + ',' 
						           + playerArray[i].getFamilyName() + ','
						           + playerArray[i].getGivenName() + ',' 
						           + String.valueOf(playerArray[i].getGamesPlayedNum())
						           + " games," + String.valueOf(playerArray[i].getGamesWonNum()) 
						           + " wins");
			}
		}
	}

	/*
	 * displayPlayer(String userName):player's username input, if exist, display
	 * this player's information, sorted by username in ascending order, if not
	 * exist, the player not exist
	 */
	private void displayPlayer(String userName) {
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				if (playerArray[i].getUserName().equals(userName)) {
					System.out.println(playerArray[i].getUserName() + ',' 
							           + playerArray[i].getFamilyName() + ','
							           + playerArray[i].getGivenName() + ',' 
							           + String.valueOf(playerArray[i].getGamesPlayedNum())
							           + " games," + String.valueOf(playerArray[i].getGamesWonNum()) 
							           + " wins");
					return;
				}
			}
		}
		System.out.println("The player does not exist.");
	}

	/*
	 * rankings():no order input(or default order:desc),display players' the rate of
	 * winning games, the number of play game, family name, given name ,sorted by the
	 * rate of winning games in desc order
	 */
	private void rankings() {
		nimSys.sortWinRateDesc();
		for (int i = 0; i <= 10; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				String gamesWonRatePer = String.valueOf(playerArray[i].getGamesWonRate()) + '%';
				System.out.printf("%-5s| %02d games | %s %s\n", gamesWonRatePer
						          , playerArray[i].getGamesPlayedNum()
						          , playerArray[i].getFamilyName(), playerArray[i].getGivenName());
			}
		}
	}

	/*
	 * rankings(String order):has order input(asc),display players' the rate of
	 * winning games, the number of play game, family name, given name ,sorted by the
	 * rate of winning games in asc order
	 */
	private void rankings(String order) {
		nimSys.sortWinRateAsc();
		for (int i = 0; i <= 10; i++) {
			if (playerArray[i] == null) {
				continue;
			} else {
				String gamesWonRatePer = String.valueOf(playerArray[i].getGamesWonRate()) + '%';
				System.out.printf("%-5s| %02d games | %s %s\n", gamesWonRatePer
						          , playerArray[i].getGamesPlayedNum()
						          , playerArray[i].getFamilyName(), playerArray[i].getGivenName());
			}
		}
	}

	/*
	 * startGame(String[] nameInfo):has user name information
	 * input,nameInfo[0]:initialStones,nameInfo[1]:upperBound,
	 * nameInfo[2]:player1,nameInfo[3]:player2,start game
	 */
	private void startGame(String[] nameInfo) {
		NimGame nimGame = new NimGame();

		boolean existUser1 = false;
		boolean existUser2 = false;
		int initialStones = Integer.parseInt(nameInfo[0]);
		int upperBound = Integer.parseInt(nameInfo[1]);
		String userName1 = nameInfo[2];
		String userName2 = nameInfo[3];
		int i;
		int j;

		// Look for player1 and determine if he exists
		for (i = 0; i < playerArray.length; i++) {
			if (playerArray[i] == null) {
				continue;
			} else if (playerArray[i].getUserName().equals(userName1)) {
				existUser1 = true;
				break;
			} else {
				existUser1 = false;
			}
		}

		// Look for player2 and determine if he exists
		for (j = 0; j < playerArray.length; j++) {
			if (playerArray[j] == null) {
				continue;
			} else if (playerArray[j].getUserName().equals(userName2)) {
				existUser2 = true;
				break;
			} else {
				existUser2 = false;
			}
		}

		/*
		 * If player1 and player2 both exist, start the game, otherwise show "One of the
		 * players do not exist."
		 */
		if ((existUser1 & existUser2) == true) {
			System.out.printf("\nInitial stone count: " + initialStones + "\n");
			System.out.printf("Maximum stone removal: " + upperBound + "\n");
			System.out.printf("Player 1: " + playerArray[i].getFamilyName() + ' ' 
					          + playerArray[i].getGivenName() + "\n");
			System.out.printf("Player 2: " + playerArray[j].getFamilyName() + ' ' 
					          + playerArray[j].getGivenName() + "\n\n");
			nimGame.exeGame(initialStones, upperBound, playerArray[i], playerArray[j]);
		} else {
			System.out.println("One of the players does not exist.");
		}
	}

	// sortUserName():Sort in ascending order by username
	private void sortUserName() {
		for (int i = 0; i < playerArray.length - 1; i++) {
			for (int j = i + 1; j < playerArray.length; j++) {
				if (playerArray[i] != null && playerArray[j] != null) {
					if (playerArray[i].getUserName().compareTo(playerArray[j].getUserName()) > 0) {
						swapArray(playerArray, i, j);
					}
				}
			}
		}
	}

	/*
	 * sortWinRateAsc():Sort in ascending order by the rate of winning, if the same
	 * ,sort in desc order by username
	 */
	private void sortWinRateAsc() {
		for (int a = 0; a < playerArray.length - 1; a++) {
			if (playerArray[a] == null) {
				continue;
			} else {
				playerArray[a].setGamesWonRate();
			}
		}
		for (int i = 0; i < playerArray.length - 1; i++) {
			for (int j = i + 1; j < playerArray.length; j++) {
				if ((playerArray[i] == null) || (playerArray[j] == null)) {
					continue;
				} else {
					if (playerArray[i].getGamesWonRate() > playerArray[j].getGamesWonRate()) {
						swapArray(playerArray, i, j);
					} else if (playerArray[i].getGamesWonRate() 
							   == playerArray[j].getGamesWonRate()) {
						if (playerArray[i].getUserName().compareTo(playerArray[j].getUserName()) 
								> 0) {
							swapArray(playerArray, i, j);
						}
					}
				}
			}
		}
	}

	/*
	 * sortWinRateDesc():Sort in desc order by the rate of winning, if the same, sort
	 * rate, sort in desc order by username
	 */
	private void sortWinRateDesc() {
		for (int a = 0; a < playerArray.length - 1; a++) {
			if (playerArray[a] == null) {
				continue;
			} else {
				playerArray[a].setGamesWonRate();
			}
		}
		for (int i = 0; i < playerArray.length - 1; i++) {
			for (int j = i + 1; j < playerArray.length; j++) {
				if ((playerArray[i] == null) || (playerArray[j] == null)) {
					continue;
				} else {
					if (playerArray[i].getGamesWonRate() < playerArray[j].getGamesWonRate()) {
						swapArray(playerArray, i, j);
					} else if (playerArray[i].getGamesWonRate() 
							   == playerArray[j].getGamesWonRate()) {
						if (playerArray[i].getUserName().compareTo(playerArray[j].getUserName()) 
								> 0) {
							swapArray(playerArray, i, j);
						}
					}

				}
			}
		}
	}

	// Exchange all information for player1 and player2
	private void swapArray(NimPlayer[] playerArray, int i, int j) {
		NimPlayer temp = playerArray[i];
		playerArray[i] = playerArray[j];
		playerArray[j] = temp;
	}
}
