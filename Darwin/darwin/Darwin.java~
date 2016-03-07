
import structure5.*;
import java.util.Random;
import java.io.*;

/**
 * This class controls the simulation.  The design is entirely up to
 * you.  You should include a main method that takes the array of
 * species file names passed in and populates a world with species of
 * each type.  
 * <p>
 * Be sure to call the WorldMap.pause() method every time
 * through the main simulation loop or else the simulation will be too fast. 
 * For example:
 * <pre>
 *   public void simulate() {
 *       for (int rounds = 0; rounds < numRounds; rounds++) {
 *         giveEachCreatureOneTurn();
 *         pause(100);
 *       }
 *   }
 * </pre>
 */
class Darwin {

    public static World<Creature> world;
    public static Vector<Creature> creatures;

    /**
     * The array passed into main will include the arguments that
     * appeared on the command line.  For example, running "java
     * Darwin Hop.txt Rover.txt" will call the main method with s
     * being an array of two strings: "Hop.txt" and "Rover.txt".
     */
    public static void main(String s[]) {

	WorldMap.createWorldMap(20,20);
	creatures = new Vector<Creature>();
	world = new World<Creature>(20,20);
	Random random = new Random();

	//populate board with creatures

	while( creatures.size() < 50){

	    int x = (Math.abs(random.nextInt()))%19;
	    int y = (Math.abs(random.nextInt()))%19;
	    Position pos = new Position (x,y); //random position
	    int w =  (Math.abs(random.nextInt()))%s.length; //to determine which species the creature will be 
	    int z =  (Math.abs(random.nextInt()))%4; //random direction
	    int i = 1; //first instruction
	    int m = 0; // memory
	    Creature one = new Creature (new Species(s[w]), world, pos, z, i, m);
	    creatures.add(one);

	}
        
	simulate();
    }


    public static void simulate() {

	while(true){  //continue simiulation 
	    
	    for ( Creature creature: creatures){ //for loop that goes through each creature in vector that holds creatures
		
		creature.takeOneTurn();
	    }
	WorldMap.pause(500);
	
	}
    }
}
