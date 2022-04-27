
/*
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Lecturer:  Prof. Rui Zhang
   Student:   Huan Cao
   StudentID: 985151
   Last changed: 5/30/2019
   ProjectC, NimPlayer class
*/

import java.io.Serializable;

/*
*the parent class, which defines the main methods of player, is convenient for NimHumanPlayer class 
*and NimAIPlayer class to inherit. due to the removeStone method is abstract method, this class is
*also abstract method
*/
public abstract class NimPlayer implements Serializable{
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

	// toString method
	public String toString() {
		return (this.userName + " " + this.givenName + " " + this.familyName + " "
				+ String.valueOf(this.getGamesPlayedNum()) + " " 
				+ String.valueOf(this.getGamesWonNum()));
	}

	// removeStone abstract method
	public abstract int removeStone(int stonesLeft, int upperBound, String nimPlayerFamilyName);

	/*
	* choose the minimum value from the remaining stones and the upper limit of
	* stones to judge whether the stones are selected incorrectly
	*/
	public int min(int stonesLeft, int upperBound) {
		if ((stonesLeft < 0) || (upperBound <= 0)) {
			return 0;
		} else if (stonesLeft < upperBound) {
			return stonesLeft;
		} else {
			return upperBound;
		}
	}

}
