/* ********************************************************
 * Name:Huan Cao
 * Student Number:985151
 * last changed date:04/03/2019
 */
public class NimPlayer {
	String name;
    int remove,numberOfStones;
	   
    //Create players' names
    public NimPlayer(String name) {
      	this.name=name;
    }
	
    //Players take stones, how many stones should players take, and which player takes stones
    public int removeStone(int numberOfStones) {
   	    System.out.print(numberOfStones + " stones left:");
   	    int i;
	    for(i=1; i <= numberOfStones; i++) {
	    	System.out.print(" *");
	    } 
	    System.out.println();
        System.out.println(this.name + "'s turn - remove how many?");
		System.out.println();
        int remove = Nimsys.scanner.nextInt();
	    numberOfStones=numberOfStones - remove;
        return numberOfStones;
    }
    
    //Judge which player wins the game
    public void win(String name) {
    System.out.println("Game Over");
    System.out.println(this.name + " wins!");
    }

}
