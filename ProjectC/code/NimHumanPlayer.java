
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

//the inheritance of NimPlayer class, human player
public class NimHumanPlayer extends NimPlayer{

	// instance
	public NimHumanPlayer(String userName, String givenName, String familyName) {
		super(userName, givenName, familyName);
	}

	/* 
	 * the concreteness of removeStone abstract method, the human player takes stones by player 
	 * input, and return the number of stones
	 */
	public int removeStone(int stonesLeft, int upperBound, String nimHumanPlayerFamilyName) {
		int removeNum = 0;

		System.out.print(stonesLeft + " stones left:");
		for (int i = 0; i < stonesLeft; i++) {
			System.out.print(" *");
		}
		System.out.print("\n" + nimHumanPlayerFamilyName + "'s turn - remove how many?\n\n");
		removeNum = Nimsys.scanner.nextInt();
		Nimsys.scanner.nextLine();
		return removeNum;
	}

}
