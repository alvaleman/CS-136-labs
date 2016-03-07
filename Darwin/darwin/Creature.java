
import structure5.*;
import java.util.*;
import java.util.Random;

/**
 * This class represents one creature on the board.
 * Each creature must remember its species, position, direction,
 * and the world in which it is living.
 * <p>
 * In addition, the Creature must remember the next instruction
 * out of its program to execute.
 * <p>
 * The creature is also repsonsible for making itself appear in
 * the WorldMap.  In fact, you should only update the WorldMap from
 * inside the Creature class.  
 */

public class Creature {


    Species species; 
    World<Creature> world;
    Position pos;
    int dir;
    int i; // next instruction
    int mem; // memory int, set to 0
    
    /**
     * Create a creature of the given species, with the indicated
     * position and direction.  Note that we also pass in the 
     * world-- remember this world, so that you can check what
     * is in front of the creature and to update the board
     * when the creature moves.
     */
    public Creature(Species species,
		    World<Creature> world,
		    Position pos,
		    int dir, int i, int mem) {

	this.species = species;
	this.world = world;
	this.pos = pos;
	this.dir = dir;
	this.i = i;
	this.mem = mem;
	
    }

    /**
     * Return the species of the creature.
     */
    public Species species() {
	return species;
    }

    /**
     * Return the current direction of the creature.
     */
    public int direction() {
	return dir;
    }

    /**
     * Return the position of the creature.
     */
    public Position position() {
	return pos;
    }

    /**
     * Return memory int of creature - for creature communication (not written in this code, but I wanted method here in case we write function in later)
     */
    public int memory() {
	return mem;
    }

    /**
     * Execute steps from the Creature's program until 
     * a hop, left, right, or infect instruction is executed.
     */
    public void takeOneTurn() {
	// first two lines in case you have silly creature who only processes ifx/go/set/inc/dec statements
	world.set(pos, this); // modify world with creature
	WorldMap.displaySquare(pos, species.getName().charAt(0), dir, species.getColor()); // updatedisplay
	
	Instruction inst = species.programStep(i); // instruction for creature movement

	// if turn left
	if(inst.toString().contains("left")) { 
	    dir = leftFrom(dir); // change direction - turn left
	    world.set(pos, this); // modify world with creature, now with new direction
	    WorldMap.displaySquare(pos, species.getName().charAt(0), dir, species.getColor()); // modify map
	    i++;
	   
	}

	// if turn right
	else if (inst.toString().contains("right")) {
	    dir = rightFrom(dir);
	    world.set(pos, this);
	    WorldMap.displaySquare(pos, species.getName().charAt(0), dir, species.getColor());
	    i++;
	}

	// if go to x step
	else if (inst.toString().contains("go")) {
	    i = inst.getAddress(); // update instruction step
	    takeOneTurn();
	   	    
	}

	// if infect species in front
	else if (inst.toString().contains("infect")) {
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) != null && !world.get(pos.getAdjacent(dir)).species().getName().equals(species.getName())) { // if adjacent position is valid, contains species, foreign species
		
		int step = 1; // for the newly infected species, instruction number
		if (inst.getAddress() != 0) { // if instruction address fed
		    step = inst.getAddress(); // set step to address
		}
		Creature infected = new Creature(species, world, pos.getAdjacent(dir), world.get(pos.getAdjacent(dir)).direction(), step, 0); // make new creature at adjacent position
		Darwin.creatures.remove(world.get(pos.getAdjacent(dir))); // remove creature at infection site
		world.set(pos.getAdjacent(dir), infected); // put infected species at that position
		Darwin.creatures.add(infected); // add infected species to darwin creatures vector
		WorldMap.displaySquare(pos.getAdjacent(dir), species.getName().charAt(0), world.get(pos.getAdjacent(dir)).direction(), species.getColor()); // display on map
		
		i++;
	    }
	    else {
		i++;
		takeOneTurn();
	    }
	}

	// if hop/moveforward
	else if (inst.toString().equals("hop")) {
	    // if next position is real/available
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) == null) {
		world.set(pos, null); // set current position to null
		WorldMap.displaySquare(pos, ' ', dir, species.getColor()); // clear square from worldmap
		pos = pos.getAdjacent(dir); // get new position
		world.set(pos, this); // put creature in new position
		WorldMap.displaySquare(pos,species.getName().charAt(0), dir, species.getColor());
	    }
	    i++;
	}

	// if next spot is empty
	else if (inst.toString().contains("ifempty")) {
	    // if legitimate spot - go to specified step
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) == null) {
		i = inst.getAddress();
	    }
	    else { // increment step 
		i++;
	    }
	    
	    takeOneTurn();
	}

	// if we're at a border
	else if (inst.toString().contains("ifwall")) {

	    // if next pos is within range --> not at the border
	    if (world.inRange(pos.getAdjacent(dir))) {
		i++;
	    }
	    // go to specified step
	    else {
		i = inst.getAddress();
	    }

	    takeOneTurn();
	    
	}

	// if species are same
	else if (inst.toString().contains("ifsame")) {
	    // same as ifenemy, except last condition checks if species.getName() are equal rather than not
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) != null && world.get(pos.getAdjacent(dir)).species().getName().equals(species.getName())) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// checks if next thing in front is foreign species
	else if (inst.toString().contains("ifenemy")) {
	    // checks are same as for infect
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) != null && !world.get(pos.getAdjacent(dir)).species().getName().equals( species.getName()) ) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// EXTRA CREDIT - EYESIGHT LEFT - checks if enemy to left of creature
	else if (inst.toString().contains("ifenemyleft")) {
	    if (world.inRange(pos.getAdjacent(leftFrom(dir))) && world.get(pos.getAdjacent(leftFrom(dir))) != null && !world.get(pos.getAdjacent(leftFrom(dir))).species().getName().equals( species.getName()) ) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// EXTRA CREDIT - EYESIGHT RIGHT - enemy to right of creature
	else if (inst.toString().contains("ifenemyright")) {
	    if (world.inRange(pos.getAdjacent(rightFrom(dir))) && world.get(pos.getAdjacent(rightFrom(dir))) != null && !world.get(pos.getAdjacent(rightFrom(dir))).species().equals( species.getName()) ) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// EXTRA CREDIT - EYESIGHT 2 FWD - enemy two positions forward of creature
	else if (inst.toString().contains("if2enemy")) {
	    if (world.inRange(pos.getAdjacent(dir).getAdjacent(dir)) && world.get(pos.getAdjacent(dir).getAdjacent(dir)) != null && !world.get(pos.getAdjacent(dir).getAdjacent(dir)).species().getName().equals(species.getName()) ) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// if instruction says random, then randomly decide if go to specified step or next step
	else if (inst.toString().contains("ifrandom")) {
	    Random random = new Random();
	    if ((random.nextInt()%2) == 0) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// EXTRA CREDIT - MEMORY FUNCTIONS (also updated code in instructions and species to account for mem)
	// SET CREATURE'S MEMORY
	else if (inst.toString().contains("set")) {
	    mem = inst.getAddress();
	    i++;
	}

	// IFEQ V N, jump to address n in program if memory contains v
	else if (inst.toString().contains("ifeq")) {
	    if (mem == inst.getMem()) {
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// increment memory int
	else if (inst.toString().contains("inc")) {
	    mem++;
	    i++;
	}

	// decrement memory int
	else if (inst.toString().contains("dec")) {
	    mem--;
	    i++;
	}

	// ask creature in front for its mem int
	else if (inst.toString().contains("ifmemeq")) {
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) != null && world.get(pos.getAdjacent(dir)).memory() == inst.getMem() ) { // if creature in front and mems are equal
		i = inst.getAddress();
	    }
	    else {
		i++;
	    }
	    takeOneTurn();
	}

	// copies mem value
	else if (inst.toString().contains("copymem")) {
	    if (world.inRange(pos.getAdjacent(dir)) && world.get(pos.getAdjacent(dir)) != null) { // if creature in front
		mem = world.get(pos.getAdjacent(dir)).memory();
		i++;
	    }
	}
	
    }

    /**
     * Return the compass direction the is 90 degrees left of
     * the one passed in.
     */
    public static int leftFrom(int direction) {
	Assert.pre(0 <= direction && direction < 4, "Bad direction");
	return (4 + direction - 1) % 4;
    }

    /**
     * Return the compass direction the is 90 degrees right of
     * the one passed in.
     */
    public static int rightFrom(int direction) {
	Assert.pre(0 <= direction && direction < 4, "Bad direction");
	return (direction + 1) % 4;
    }

    /**
     */
    public static void main(String st[]) {

	// test creature code here.
	WorldMap.createWorldMap(6,6);
	World<Creature> world = new World<Creature>(6,6);//create 6X6 world 
	Position pos = new Position(0,3); //initial position of creature
	Species spec = new Species("Rover.txt"); // species for creature
	int dir = 1; //direction for creature
	int i = 1; //first instruction for creature
	int m = 0; // memory
	Creature one = new Creature (spec, world, pos, dir, i, m);
	one.takeOneTurn(); //Move Rover forward one space
	one.takeOneTurn(); //Move Rover forward another space
	one.takeOneTurn(); //Try to move rover
  
    }


}
