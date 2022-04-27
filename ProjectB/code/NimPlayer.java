

/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer:  Prof. Rui Zhang
   Student:   Huan Cao
   StudentID: 985151
   Last changed: 5/6/2019
   ProjectB, NimPlayer class
*/

public class NimPlayer {
	private String userName;
	private String givenName;
	private String familyName;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesWonRate;

	Nimsys nimSys = new Nimsys();
	
	// instance
	public NimPlayer(String userName, String givenName, String familyName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		this.gamesPlayed = 0;
		this.gamesWon = 0;
		this.gamesWonRate = 0;
	}

	// set names
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	// get names
	public String getUserName() {
		return userName;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	// set game numbers and rate
	public void setGamesPlayedNum(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public void setGamesWonNum(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public void setGamesWonRate() {
		if (this.getGamesPlayedNum() != 0) {
			this.gamesWonRate = (int) Math.round((((double) this.getGamesWonNum()) 
					             / ((double) this.getGamesPlayedNum())) * 100);
		}
	}

	// reset rate
	public void resetWonRate() {
		this.gamesWonRate = 0;
	}

	// get game numbers and rate
	public int getGamesPlayedNum() {
		return gamesPlayed;
	}

	public int getGamesWonNum() {
		return gamesWon;
	}

	public int getGamesWonRate() {
		return gamesWonRate;
	}

	// increase game number
	public void increaseGamesPlayedNum() {
		this.gamesPlayed = this.gamesPlayed + 1;
	}

	public void increaseGamesWonNum() {
		this.gamesWon = this.gamesWon + 1;
	}

	public String toString() {
		return this.userName + " " + this.givenName + " " + this.familyName + " "
				+ String.valueOf(this.getGamesPlayedNum()) + " " 
				+ String.valueOf(this.getGamesWonNum());
	}

	// remove stones
	public int removeStone(int stonesLeft, int upperBound) {
		int removeNum;
		System.out.printf(stonesLeft + " stones left:");
		for (int i = 0; i < stonesLeft; i++) {
			System.out.printf(" *");
		}
		System.out.printf("\n" + this.getFamilyName() + "'s turn - remove how many?\n\n");
		removeNum = Nimsys.scanner.nextInt();
		Nimsys.scanner.nextLine();

		/*
		 * whether the number of removing stones greater than stones left or upper
		 * bound, if greater,player receives error
		 */
		if ((removeNum > upperBound) || (removeNum > stonesLeft) || removeNum <= 0) {
			if (removeNum > stonesLeft || removeNum <= 0) {
				System.out.printf("Invalid move. You must remove between 1 and " 
						+ stonesLeft + " stones.\n\n");
				return stonesLeft;
			} else if (removeNum > upperBound) {
				System.out.printf("Invalid move. You must remove between 1 and " 
						+ upperBound + " stones.\n\n");
				return stonesLeft;
			}
		}

		// the number of stone left
		stonesLeft = stonesLeft - removeNum;
		return stonesLeft;
	}
}
