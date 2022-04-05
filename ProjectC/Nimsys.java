
/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer:  Prof. Rui Zhang
   Student:   Huan Cao
   StudentID: 985151
   Last changed: 5/30/2019
   ProjectC, NimSys class
*/

import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Nimsys implements Serializable{

	static Scanner scanner = new Scanner(System.in);
	static Nimsys nimSys = new Nimsys();
	NimPlayer[] playerArray = new NimPlayer[100];

	public static void main(String[] args) {
		
		//fetches players' information input from memory
		nimSys.readPlayerInfo();
		
		System.out.print("Welcome to Nim\n");
		
		//game loop
		while (true) {
			System.out.print("\n");
			System.out.print("$");

			/*
			 * input command, and split the command by ' ' and ','
			 */
			String command = scanner.nextLine();
			String[] commandInfo = command.split(" |,");

			/*
			 * error reporting for incorrect command input, if the command is not addplayer, 
			 * addaiplayer, removeplayer, displayplayer, editplayer, resetstats, rankings, 
			 * startgame or exit, the system automatically reported an error
			 */
			try 
			{
				if ((!commandInfo[0].equals("addplayer")) 
						&& (!commandInfo[0].equals("addaiplayer"))
						&& (!commandInfo[0].equals("removeplayer")) 
						&& (!commandInfo[0].equals("displayplayer"))
						&& (!commandInfo[0].equals("editplayer")) 
						&& (!commandInfo[0].equals("resetstats"))
						&& (!commandInfo[0].equals("rankings")) 
						&& (!commandInfo[0].equals("startgame"))
						&& (!commandInfo[0].equals("exit")))
					throw new 
					  CommandInfoException("'" + commandInfo[0] + "'" + " is not a valid command.");
			}
			catch (CommandInfoException e) 
			{
				String message = e.getMessage();
				System.out.println(message);
			}

			// input correct command, execute relevant function
			if (commandInfo[0].equals("addplayer")) {
				nimSys.addPlayer(commandInfo);
			} else if (commandInfo[0].equals("addaiplayer")) {
				nimSys.addAIPlayer(commandInfo);
			} else if (commandInfo[0].equals("removeplayer")) {
				nimSys.removePlayer(commandInfo);
			} else if (commandInfo[0].equals("displayplayer")) {
				nimSys.displayPlayer(commandInfo);
			} else if (commandInfo[0].equals("editplayer")) {
				nimSys.editPlayer(commandInfo);
			} else if (commandInfo[0].equals("resetstats")) {
				nimSys.resetStats(commandInfo);
			} else if (commandInfo[0].equals("rankings")) {
				nimSys.rankings(commandInfo);
			} else if (commandInfo[0].equals("startgame")) {
				nimSys.startGame(commandInfo);
			} else if (commandInfo[0].equals("exit")) {
				
				//output players' information to file
				nimSys.writePlayerInfo();
				System.out.print("\n");
				System.exit(0);
			}
		}
	}

	/*
	 * addPlayer(String[] nameInfo):determine the number of arguments and whether the player exists,
	 * according to the situation, add player
	 */
	private void addPlayer(String[] commandInfo) {

		// the number of arguments
		int count = commandInfo.length - 1;

		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is not equal to 3, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count != 3)
				throw new 
				       ArgumentNumException("Incorrect number of arguments supplied to command.");
		}
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		/*
		 * add player, if the number of arguments is equal to 3, if exist, "The player already 
		 * exists.", if not exist, add the player
		 */
		if (count == 3) {
			NimHumanPlayer nimHumanPlayer = new NimHumanPlayer(commandInfo[1], commandInfo[2],
					                                           commandInfo[3]);

			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] == null) {
					continue;
				} else {
					if (playerArray[i].getUserName().equals(nimHumanPlayer.getUserName())) {
						System.out.println("The player already exists.");
						return;
					}
				}
			}

			for (int j = 0; j < playerArray.length; j++) {
				if (playerArray[j] == null) {
					playerArray[j] = nimHumanPlayer;
					break;
				}
			}
		}
	}

	/*
	 * addPlayer(String[] nameInfo):determine the number of arguments and whether the AI player 
	 * exists, according to the situation, add AI player
	 */
	private void addAIPlayer(String[] commandInfo) {

		// the number of arguments
		int count = commandInfo.length - 1;

		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is not equal to 3, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count != 3)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");
		} 
		catch (ArgumentNumException e)
		{
			String message = e.getMessage();
			System.out.println(message);
		}

		/*
		 * add AI player, if the number of arguments is equal to 3, if exist, "The player already 
		 * exists.", if not exist, add the player
		 */
		if (count == 3) {
			NimAIPlayer nimAIPlayer = new NimAIPlayer(commandInfo[1], commandInfo[2],
					                                  commandInfo[3]);

			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] == null) {
					continue;
				} else {
					if (playerArray[i].getUserName().equals(nimAIPlayer.getUserName())) {
						System.out.println("The player already exists.");
						return;
					}
				}
			}

			for (int j = 0; j < playerArray.length; j++) {
				if (playerArray[j] == null) {
					playerArray[j] = nimAIPlayer;
					break;
				}
			}
		}
	}

	/*
	 * removePlayer(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation, remove player
	 */
	private void removePlayer(String[] commandInfo) {
		
		// the number of arguments
		int count = commandInfo.length - 1;
		
		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is larger than 1, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try
		{
			if (count > 1)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");	
		} 
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}

		/*
		 * remove player, if the number of arguments is equal to 0, remove all players, if the 
		 * number of arguments is equal to 1, if the player exists, remove this player, if not,
		 * "The player does not exist."
		 */
		if (count == 0) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String answer = scanner.nextLine();
			if (answer.equals("y")) {
				for (int i = 0; i < playerArray.length; i++) {
					if (playerArray[i] != null) {
						playerArray[i] = null;
					}
				}
			}
		} else if (count == 1) {
			for (int j = 0; j < playerArray.length; j++) {
				if (playerArray[j] == null) {
					continue;
				} else {
					if (playerArray[j].getUserName().equals(commandInfo[1])) {
						playerArray[j] = null;
						return;
					}
				}
			}
			System.out.println("The player does not exist.");
		}
	}

	/*
	 * editPlayer(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation, edit player
	 */
	private void editPlayer(String[] commandInfo) {
		
		// the number of arguments
		int count = commandInfo.length - 1;

		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is not equal to 3, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count != 3)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");	
		}
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		/*
		 * edit player, if the number of arguments is equal to 3, if exists, edit the player, if 
		 * not, "The player does not exist."
		 */
		if (count == 3) {
			NimHumanPlayer nimHumanPlayer = new NimHumanPlayer(commandInfo[1], commandInfo[2],
					                                           commandInfo[3]);
			NimAIPlayer nimAIPlayer = new NimAIPlayer(commandInfo[1], commandInfo[2],
					                                  commandInfo[3]);

			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] == null) {
					continue;
				} else {
					if (playerArray[i].getUserName().equals(nimHumanPlayer.getUserName())
						|| playerArray[i].getUserName().equals(nimAIPlayer.getUserName())) {
						playerArray[i].setFamilyName(commandInfo[3]);
						playerArray[i].setGivenName(commandInfo[2]);
						return;
					}
				}
			}
			System.out.println("The player does not exist.");
		}
	}
	
	/*
	 * resetStats(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation, reset statics
	 */
	private void resetStats(String[] commandInfo) {

		// the number of arguments
		int count = commandInfo.length - 1;

		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is larger than 1, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try
		{
			if (count > 1)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");
		}
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		/*
		 * reset statics, if the number of arguments is equal to 0, reset all players, if the 
		 * number of arguments is equal to 1, if the player exists, reset this player, if not,
		 * "The player does not exist."
		 */
		if (count == 0) {
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
		} else if (count == 1) {
			for (int j = 0; j < playerArray.length; j++) {
				if (playerArray[j] == null) {
					continue;
				} else {
					if (playerArray[j].getUserName().equals(commandInfo[1])) {
						playerArray[j].setGamesPlayedNum(0);
						playerArray[j].setGamesWonNum(0);
						playerArray[j].resetWonRate();
						return;
					}
				}
			}
			System.out.println("The player does not exist.");
		}

	}

	/*
	 * displayPlayer(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation,  display players
	 */
	private void displayPlayer(String[] commandInfo) {
		
		// the number of arguments
		int count = commandInfo.length - 1;
		
		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is larger than 1, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count > 1)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");
		}
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		/*
		 * display players, if the number of arguments is equal to 0, display all players, if the 
		 * number of arguments is equal to 1, if the player exists, displayer this player, if not,
		 * "The player does not exist."
		 */
		if (count == 0) {
			nimSys.sortUserName();
			for (int i = 0; i < playerArray.length; i++) {
				if (playerArray[i] != null) {
					System.out.println(playerArray[i].getUserName() + ',' 
							           + playerArray[i].getFamilyName() + ','
							           + playerArray[i].getGivenName() + ','
							           + String.valueOf(playerArray[i].getGamesPlayedNum()) 
							           + " games,"
							           + String.valueOf(playerArray[i].getGamesWonNum()) + " wins");
				}
			}
		} else if (count == 1) {
			for (int j = 0; j < playerArray.length; j++) {
				if (playerArray[j] == null) {
					continue;
				} else {
					if (playerArray[j].getUserName().equals(commandInfo[1])) {
						System.out.println(playerArray[j].getUserName() + ',' 
								           + playerArray[j].getFamilyName() + ','
								           + playerArray[j].getGivenName() + ','
								           + String.valueOf(playerArray[j].getGamesPlayedNum()) 
								           + " games,"
								           + String.valueOf(playerArray[j].getGamesWonNum()) 
								           + " wins");
						return;
					}
				}
			}
			System.out.println("The player does not exist.");
		}
	}

	/*
	 * rankings(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation,  rank players
	 */
	private void rankings(String[] commandInfo) {
		
		// the number of arguments
		int count = commandInfo.length - 1;
		
		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is larger than 1, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count > 1)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");	
		} 
		catch (ArgumentNumException e)
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		/*
		 * rank players by the desc or asc order of win rate, if the number of arguments is equal 
		 * to 0, rank all players by default order(desc), if the number of arguments is equal to 1,
		 * if the order is "desc", rank all players by desc order, if the order is "asc", rank all 
		 * players by asc order
		 */
		if ((count == 0)) {
			nimSys.sortWinRateDesc();
			for (int i = 0; i <= 10; i++) {
				if (playerArray[i] == null) {
					continue;
				} else {
					String gamesWonRatePer = String.valueOf(playerArray[i].getGamesWonRate()) + '%';
					System.out.printf("%-5s| %02d games | %s %s\n", gamesWonRatePer,
							playerArray[i].getGamesPlayedNum(), playerArray[i].getFamilyName(),
							playerArray[i].getGivenName());
				}
			}
		} else if (count == 1) {
			if (commandInfo[1].equals("desc")) {
				nimSys.sortWinRateDesc();
				for (int j = 0; j <= 10; j++) {
					if (playerArray[j] == null) {
						continue;
					} else {
						String gamesWonRatePer = String.valueOf(playerArray[j].getGamesWonRate()) 
								                                + '%';
						System.out.printf("%-5s| %02d games | %s %s\n", gamesWonRatePer,
								playerArray[j].getGamesPlayedNum(), playerArray[j].getFamilyName(),
								playerArray[j].getGivenName());
					}
				}
			} else if (commandInfo[1].equals("asc")) {
				nimSys.sortWinRateAsc();
				for (int a = 0; a <= 10; a++) {
					if (playerArray[a] == null) {
						continue;
					} else {
						String gamesWonRatePer = String.valueOf(playerArray[a].getGamesWonRate()) 
								                                + '%';
						System.out.printf("%-5s| %02d games | %s %s\n", gamesWonRatePer,
								playerArray[a].getGamesPlayedNum(), playerArray[a].getFamilyName(),
								playerArray[a].getGivenName());
					}
				}
			}
		}

	}

	/*
	 * startGame(String[] nameInfo):determine the number of arguments and whether the player 
	 * exists, according to the situation, start game
	 */
	private void startGame(String[] commandInfo) {

		// the number of arguments
		int count = commandInfo.length - 1;
		
		/*
		 * determine whether the number of input arguments is correct. if the number of
		 * arguments is not equal to 4, the system will automatically report an error,
		 * "Incorrect number of arguments supplied to command."
		 */
		try 
		{
			if (count != 4)
				throw new 
				      ArgumentNumException("Incorrect number of arguments supplied to command.");
		} 
		catch (ArgumentNumException e) 
		{
			String message = e.getMessage();
			System.out.println(message);
		}
		
		if (count == 4) {
			
			NimGame nimGame = new NimGame();
			
			/*
			 * initialize and define variable, existUser1, if player1 exists, existUser2, if 
			 * player2 exists
			 */
			boolean existUser1 = false;
			boolean existUser2 = false;
			int initialStones = Integer.parseInt(commandInfo[1]);
			int upperBound = Integer.parseInt(commandInfo[2]);
			String userName1 = commandInfo[3];
			String userName2 = commandInfo[4];
			int i;
			int j;

			// look for player1 and determine if he exists
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

			// look for player2 and determine if he exists
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
			 * if player1 and player2 both exist, start the game, otherwise show "One of the
			 * players do not exist."
			 */
			if ((existUser1 & existUser2) == true) {
				System.out.print("\nInitial stone count: " + initialStones + "\n");
				System.out.print("Maximum stone removal: " + upperBound + "\n");
				System.out.print("Player 1: " + playerArray[i].getFamilyName() + ' ' 
						         + playerArray[i].getGivenName() + "\n");
				System.out.print("Player 2: " + playerArray[j].getFamilyName() + ' ' 
						         + playerArray[j].getGivenName() + "\n\n");
				nimGame.exeGame(initialStones, upperBound, playerArray[i], playerArray[j]);
			} else {
				System.out.println("One of the players does not exist.");
			}
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
	 * sortWinRateDesc():Sort in desc order by the rate of winning, if the same,
	 * sort rate, sort in desc order by username
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

	// exchange all information for player1 and player2
	private void swapArray(NimPlayer[] playerArray, int i, int j) {
		NimPlayer temp = playerArray[i];
		playerArray[i] = playerArray[j];
		playerArray[j] = temp;
	}
	
	//fetches players' information input from memory
		private void readPlayerInfo() {
			
			/* 
			 * initialize readPlayerInfo, playerInfo, finishInfo(determine whether all player
			 * information has been obtained) 
			 */
			ObjectInputStream readPlayerInfo = null;
			NimPlayer playerInfo = null;
			boolean finishInfo = false;
			
			/*
			 * create constructor, error reporting for if file is not found or if there are some 
			 * problems with input
			 */
			try 
			{
				readPlayerInfo = new ObjectInputStream(new FileInputStream("players.dat"));
			} 
			catch (FileNotFoundException e) 
			{
				return;
			}
			catch (IOException e) 
			{
				System.out.println("Problems with input from players.dat.");
				System.exit(0);
			}

			//fetches players' information input from memory
			try 
			{
				int i = 0;
				while (!finishInfo) {
					try 
					{
						playerInfo = (NimPlayer) readPlayerInfo.readObject();
						if (playerInfo != null)
							playerArray[i] = (NimPlayer) playerInfo;
						i = i + 1;
					}
					catch (EOFException e) 
					{
						finishInfo = true;
					}

				}

			}
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//close the stream's connection to stream
			try 
			{
				readPlayerInfo.close();
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	//output players' information to file
	private void writePlayerInfo() {
		
		//initialize writePlayerInfo
		ObjectOutputStream writePlayerInfo = null;
		
		/*
		 * create constructor, error reporting for if file is not found or if there are some 
		 * problems with file output
		 */
		try 
		{
			writePlayerInfo = new ObjectOutputStream(new FileOutputStream("players.dat"));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Cannot find file players.dat.");
			System.exit(0);
		}
		catch (IOException e) 
		{
			System.out.println("Problem with file output.");
			System.exit(0);
		}
		
		//output players' information to file
		for (int i = 0; i < playerArray.length; i++) {
			if (playerArray[i] != null) {
				try 
				{
					writePlayerInfo.writeObject(playerArray[i]);
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//close the stream's connection to stream
		try 
		{
			writePlayerInfo.close();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}