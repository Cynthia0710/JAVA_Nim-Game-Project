
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

//the inheritance of NimPlayer class, AI player
public class NimAIPlayer extends NimPlayer{

	// instance
	public NimAIPlayer(String userName, String givenName, String familyName) {
		super(userName, givenName, familyName);
	}

	/* 
	 * the concreteness of removeStone abstract method, when the AI player takes 
	 * (stonesLeft - 1) % (upperBound + 1) stones by himself, the probability of winning is high. 
	 * if (stonesLeft - 1) % (upperBound + 1) equals 0, return remove 1 stone, otherwise return
	 * (stonesLeft - 1) % (upperBound + 1)
	 */
	public int removeStone(int stonesLeft, int upperBound, String nimAIPlayerFamilyName) {
		int removeNum = 0;

		System.out.print(stonesLeft + " stones left:");
		for (int i = 0; i < stonesLeft; i++) {
			System.out.print(" *");
		}
		System.out.print("\n" + nimAIPlayerFamilyName + "'s turn - remove how many?\n\n");
		removeNum = (stonesLeft - 1) % (upperBound + 1);
		if (removeNum == 0)
			return 1;
		else
			return removeNum;
	}

}
