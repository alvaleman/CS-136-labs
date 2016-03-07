//*Alvaro Aleman & Chinmayi Manjunath


import structure5.*;
import java.io.*;
import java.util.Scanner;

/**
 * The individual creatures in the world are all representatives of
 * some species class and share certain common characteristics, such
 * as the species name and the program they execute.  Rather than copy
 * this information into each creature, this data can be recorded once
 * as part of the description for a species and then each creature can
 * simply include the appropriate species pointer as part of its
 * internal data structure.
 * <p>
 * 
 * To encapsulate all of the operations operating on a species within
 * this abstraction, this provides a constructor that will read a file
 * containing the name of the creature and its program, as described
 * in the earlier part of this assignment.  To make the folder
 * structure more manageable, the species files for each creature are
 * stored in a subfolder named Creatures.  This, creating the Species
 * for the file Hop.txt will causes the program to read in
 * "Creatures/Hop.txt".
 * 
 * <p>
 *
 * Note: The instruction addresses start at one, not zero.
 */
public class Species {

    String name; //String that keeps track of species name
    String color; //String that keeps track of species color
    Vector<String> program; //Vector that will hold instructions 
    
    /**
     * Create a species for the given file.
     * @pre fileName exists in the Creature subdirectory.
     */
    public Species(String fileName)  {
	program = new Vector<String>();
	
	Scanner input = 
	    new Scanner(new FileStream("Creatures" + 
				       java.io.File.separator + 
				       fileName));
	name = input.nextLine();

	color = input.nextLine();

	while (input.hasNextLine()){

	    String instruction = input.nextLine();

   
		program.add(instruction);
       
	}
    }
    
    /**
     * Return the name of the species.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the color of the species.
     */
    public String getColor() {
	return color;
    }

    /**
     * Return the number of instructions in the program.
     */
    public int programSize() {
        return program.size();
    }

    /**
     * Return an instruction from the program.
     * @pre  1 <= i <= programSize().
     * @post returns instruction i of the program.
     */
    public Instruction programStep(int i) {
        
	String fullCommand =  program.get(i-1);
	int opcode = 0 ;
	int address = 0;
	int memory = 0;

	String[] instruction = fullCommand.split(" ");

        String command = instruction[0].toUpperCase();

	// if only address int provided
	if(instruction.length == 2){
	    address = Integer.parseInt(instruction[1]);
	}
	// if memory and address ints provided
	else if (instruction.length == 3) {
	    memory = Integer.parseInt(instruction[1]);
	    address = Integer.parseInt(instruction[2]);
	}
	
	if (command.equals("HOP")){
	    opcode = 1;
	}
	else if (command.equals("LEFT")){
	    opcode = 2;
	}
	else if (command.equals("RIGHT")){
	    opcode = 3;
	}
	else if (command.equals("INFECT")){
	    opcode = 4;
	}
	else if (command.equals("IFEMPTY")){
	    opcode = 5;
	}
	else if (command.equals("IFWALL")){
	    opcode = 6;
	}
	else if (command.equals("IFSAME")){
	    opcode = 7;
	}
	else if (command.equals("IFENEMY")){
	    opcode = 8;
	}
	else if (command.equals("IFRANDOM")){
	    opcode = 9;
	}
	else if (command.equals("GO")){
	    opcode = 10;
	}

	// EC code till end of method
	else if (command.equals("IFENEMYRIGHT")) {
	    opcode = 11;
	}

	else if (command.equals("IFENEMYLEFT")) {
	    opcode = 12;
	}

	else if (command.equals("IF2ENEMY")) {
	    opcode = 13;
	}

	else if (command.equals("SET")) {
	    opcode = 14;
	}

	else if (command.equals("IFEQ")) {
	    opcode = 15;
	}

	else if (command.equals("INC")) {
	    opcode = 16;
	}

	else if (command.equals("DEC")) {
	    opcode = 17;
	}

	else if (command.equals("IFMEMEQ")) {
	    opcode = 18;
	}

	else if (command.equals("COPYMEM")) {
	    opcode = 19;
	}
	
	return new Instruction(opcode, memory, address);
    }							

    /**
     * Return a String representation of the program.
     */
    public String programToString() {
	String s = "";
	for (int i = 1; i <= programSize(); i++) {
	    s = s + (i) + ": " + programStep(i) + "\n";
	}
	return s;
    }

    // tester    
    public static void main(String s[]) {
	Species sp = new Species("Hop.txt");
	System.out.println(sp.getName());
	System.out.println(sp.getColor());
	System.out.println("first step should be hop: " + sp.programStep(1));
	
	System.out.println("second step should be go 1: " + sp.programStep(2));


    }

}
   
