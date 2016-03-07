
import structure5.*;

/**
 * This module includes the functions necessary to keep track of the
 * creatures in a two-dimensional world. In order for the design to be
 * general, the interface adopts the following design: <p>
 * 1. The contents have generic type E.  <p>
 * 2. The dimensions of the world array are specified by the client. <p>
 * There are many ways to implement this structure.  HINT: 
 * look at the structure.Matrix class.  You should need to add no more than
 * about ten lines of code to this file.
 */

public class World<E> {

    protected E[][] World;
    protected int height;
    protected int width; 

    /**
     * This function creates a new world consisting of width columns 
     * and height rows, each of which is numbered beginning at 0. 
     * A newly created world contains no objects. 
     */
    public World(int w, int h) {
	height = h;
	width = w;
	World = (E[][]) new Object[width][height];
	
    }

    /**
     * Returns the height of the world.
     */
    public int height() {
	return World.length;
    }

    /**
     * Returns the width of the world.
     */
    public int width() {
        return World[0].length;
    }

    /**
     * Returns whether pos is in the world or not.
     * @pre  pos is a non-null position.
     * @post returns true if pos is an (x,y) location in 
     *       the bounds of the board.
     */
    boolean inRange(Position pos) {
        int x = pos.getX();
	int y = pos.getY();
	return ((x > -1) && ( x < width) && (y > -1) && ( y < height));
    }

    /**
     * Set a position on the board to contain c.
     * @pre  pos is a non-null position on the board.
     */
    public void set(Position pos, E c) {
       int x = pos.getX();
       int y = pos.getY();
        World[x][y] = c;
    }

    /**
     * Return the contents of a position on the board.
     * @pre  pos is a non-null position on the board.
     */
    public E get(Position pos) {
        int x = pos.getX();
	int y = pos.getY();
	return World[x][y];
    }


    public static void main(String s[]) {
	
	World<String> World = new World <String>(6,6);
	
	Position pos = new Position(4,4);

	Position pos2 = new Position(3,3);

	System.out.println("Object in (3,3) is in range:" + World.inRange( pos2 ));

	Position pos3 = new Position (7,7);

	Position pos4 = new Position (-1, -1);

	System.out.println("These positions are out of bounds and should both return false: " + World.inRange(pos3) + " and " + World.inRange(pos4));
	
    }
	
}
