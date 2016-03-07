
import structure5.*;
import java.io.*;

/**
 * This class represents one Darwin instruction.  Instructions
 * contain two parts: an opcode and an address.  For instructions
 * that do not perform jumps (hop, left, right, infect), the address
 * part is not used.
 * 
 * The fill instruction set is the following:<p>
 * <pre>
 *   hop
 *   left
 *   right
 *   infect
 *   ifempty
 *   ifwall
 *   ifsame
 *   ifenemy
 *   ifrandom
 *   go
 * </pre>
 * The following instructions require a target address to jump to:
 *   <pre>ifempty, ifwall, ifsame, ifenemy, ifrandom, go</pre>
 *
 */
public class Instruction {

    /** opcode for the hop instruction */
    public static final int HOP =      1;
    /** opcode for the left instruction */
    public static final int LEFT =     2;
    /** opcode for the right instruction */
    public static final int RIGHT =    3;
    /** opcode for the infect instruction */
    public static final int INFECT =   4;
    /** opcode for the ifempty instruction */
    public static final int IFEMPTY =  5;
    /** opcode for the ifwall instruction */
    public static final int IFWALL =   6;
    /** opcode for the ifsame instruction */
    public static final int IFSAME =   7;
    /** opcode for the ifenemy instruction */
    public static final int IFENEMY =  8;
    /** opcode for the ifrandom instruction */
    public static final int IFRANDOM = 9;
    /** opcode for the go instruction */
    public static final int GO =       10;
    // EC CODE
    /** opcode for ifenemyright **/
    public static final int IFENEMYRIGHT = 11;
    /** opcode for ifenemyleft **/
    public static final int IFENEMYLEFT = 12;
    /** opcode for if2enemy **/
    public static final int IF2ENEMY = 13;
    /** opcode for set **/
    public static final int SET = 14;
     /** opcode for ifeq **/
    public static final int IFEQ = 15;
     /** opcode for inc **/
    public static final int INC = 16;
     /** opcode for dec **/
    public static final int DEC = 17;
    /** opcode for ifmemeq **/
    public static final int IFMEMEQ = 18;
    /** opcode for copymem **/
    public static final int COPYMEM = 19;
    protected int opcode;  /** the opcode */
    protected int address; /** the address */
    protected int memory; /** the memory */

    /**
     * Creates a new instruction.  address is the target of 
     * the operation, if one is needed.  Otherwise it is not used.
     * @pre 0 <= opcode <= COPYMEM.
     */
    public Instruction(int opcode, int memory, int address) {
	Assert.pre(0 < opcode && opcode <= COPYMEM, "Bad opcode");
	this.opcode = opcode;
	this.memory = memory; // returns memory
	this.address = address;
    }

    /**
     * Returns the opcode
     * @post returns the opcode
     */
    public int getOpcode() {
	return opcode;
    }

    /**
     * Returns the addrss
     * @post returns the address
     */
    public int getAddress() {
	return address;
    }

    // returns memory
    public int getMem() {
	return memory;
    }
    
    /**
     * Returns a printable representation of an Instruction
     */
    public String toString() {
	switch (opcode) {
	case HOP:          return "hop";
	case LEFT:         return "left";
	case RIGHT:        return "right";
	case INFECT:       return "infect " + address;
	case IFEMPTY:      return "ifempty " + address;
	case IFWALL:       return "ifwall " + address;
	case IFSAME:       return "ifsame " + address;
	case IFENEMY:      return "ifenemy " + address;
	case IFRANDOM:     return "ifrandom " + address;
	case GO:           return "go " + address;
	    // EC CODE
	case IFENEMYRIGHT: return "ifenemyright" + address;
	case IFENEMYLEFT:  return "ifenemyleft" + address;
	case IF2ENEMY:     return "if2enemy" + address;
	case SET:          return "set" + address;
	case IFEQ:         return "ifeq" + memory + address;
	case INC:          return "inc";
	case DEC:          return "dec";
	case IFMEMEQ:      return "ifmemeq" + memory + address;
	case COPYMEM:      return "copymem";
	    
	default:
	    return "BAD INSTRUCTION: " + opcode + " " + memory + " " + address;
	}
    }
}

