/*
 *Name: Alvaro Aleman
 *Lab: 1- Silver Dollar Game
 *This class is esigned to implement a text version of the Silver ollar Game. A Strip of size 15 will be represented as ab array. Empty 
 *spaces will be represented as an underscore "_" and coins will be represented as numbers. In the beginning players can determine how many coins 
 *they will play with or can choose to play with a ranfom amount by typing a 0. Players will take turns moving coins with the form "cn, ns",
 *where cn is the coin number to be moved and ns is the number of spaces the coin should be moved to the left. 
 *Method keep track of legality of moves, update the array keeping track of positions, display the string representation of the array, and 
 *end the game once all coins have made it to the left. 
 *
 *Thought Questions. 
 *1.One way to approach this is to allocate a range of numbers to represent a certain number of coins once a random number is acquired. 
 * For example, if we compute a random number between 0 and 10 then we can code it so that if the random number falls between 0 and 5,
 * then three coins would be placed. Likewise, between 5 and 7.5 would place four coins, and between 7.5 and 9 would place five coins and 
 * on. 
 *
 *2.One can guarentee no immediate wins by making sure that there are at least a couple of spaces in between the coins. To do this, when 
 *assigning the random locations for the coins one can restrict that no two are next to each using if statements and the array that keeps 
 *track of coin locations. 
 *
 *3.The computer could suggest when to move a coin beside the next one. This limits the possible moves a player's opponent can have since coins 
 *can only move in one direction. It can also provide hints as to where to move when only two coins are left since it's easy to recognize winning once the 
 *game gets to that point. 
 *
 *4.The method would focus on moving coins so that there are an even number of possible moves left after the move. This guarentees that the
 *computer has at least one more move and makes it more likely to have the final move- which wins the game. It is also easy to recognize where the game 
 *is headed once only the last two coins remain.  
 *
 *5.This rule change would not change my implementation too significantly. My testLegalMove() method has an if statement that checks whether
 *the move would move the coin to a location with a coin already there or pass the coin next to it. I would just need to change this statement
 *to allow coins to pass each other but still not land on top of each other. 
 *
 *
 */

import java.util.Random;
import java.util.Scanner;

public class coinStrip{
    
    private String[] Strip;   //Array that keeps track of current gameboard
    private boolean isLegal;  //Boolean that determines if the chosen move by the player is legal
    private int coins;     //Int that keeps track of the chosen number of coins
    private int[] coinLocations; //Array that stores the current location of the coins on the board
    private int turn = 1;   //Int that keeps track of players' turn
    private int coinChoice;
    private int spacesMoved;


   
 	public static void main(String[] args) {
        System.out.println("Enter # of coins (10 or below) or 0 for a random amount (Game is played on a board with 15 spaces and each coin is represented by a number. Get all non zeros to the left on your turn to win ");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        coinStrip game = new coinStrip(); 
        game.addCoins(num);
	System.out.println(game.toString() );
	while(!game.gameOver()){
            if (game.turn == 1) {
                System.out.println("FIRST PLAYER'S MOVE");
            }else{
                
                System.out.println("SECOND PLAYER'S MOVE");
            }         
	    System.out.println("Which number would you like to move?");
	    game.coinChoice = s.nextInt();
	    System.out.println("How many spaces left would you like to move?");
	    game.spacesMoved = s.nextInt();
	    if(game.coinChoice >=0 && game.coinChoice < game.coins && game.spacesMoved > 0){ 
	       if(game.testLegalMove(game.coinChoice,game.spacesMoved)){

		   game.moveCoin(game.coinChoice,game.spacesMoved);
		   System.out.println(game.toString());

		   if(game.gameOver()){
	   
		       if(game.turn == 1){
			   
			   System.out.println ("FIRST PLAYER WINS!!!");
		       }else{
			
			   System.out.println("SECOND PLAYER WINS!!!");
	      
		       }
		  
		   }
	        if (game.turn ==1) {
			game.turn++;
		   } else {
   
			game.turn--;
		}  
	       }
	    }else{

		System.out.println("ERROR- CHOSEN COIN NUMBER IS OUT OF RANGE OR SPACES MOVED IS NEGATIVE");
	    }
	
	}
	}
	
	

    
    //Initializes an array of length 15 that will represent the board
    public coinStrip(){
	Strip= new String[15];
     	
    } 
  
    //Adds the chosen amount of coins to the gameboard
    public void addCoins(int coinAmount){
	
	if(coinAmount == 0){
	    Random r = new Random();
	    coins = r.nextInt(6) + 1;
	} else {

	    coins = coinAmount;
	}
	
	coinLocations= new int[coins];
        
	int i = 0;
	while( i != coins){
         	Random r = new Random();
		int randomSpot = r.nextInt(Strip.length);
             if (Strip[randomSpot] == null){
                 Strip[randomSpot] =String.valueOf(i);
                 coinLocations[i]=randomSpot;
                 i++;
             }
	}

        int count = 0;     
        while(count != Strip.length){
            if(Strip[count] == null){
            Strip[count] = "_";
	    }
            count++;
        }
       
     }
    
            
         
     
     //Converts array into String representation for system output
     public String toString(){
         String board = "";
         int i = 0;
         while(i != Strip.length){
             board = board + Strip[i];
             i++;
	     //  }
	 // for(int d=0; d< coinLocations.length; d ++){
	 // System.out.print(coinLocations[d] + " ");
	 }
         return board; 
     }


    //Validates that player move was legal and updates array to reflect moves. cn- (0,1,2,3,4...) ns- (1,2,3,4...)
    public boolean testLegalMove(int cn, int ns){ 
       if (cn > coins){
	   System.out.println("Coin Number higher than toal number of coins");
	   return false;
       }else if (cn  < 0 ) {
	   System.out.println("Only positive coin values are acceptable");
	   return false;
       }
       
       //Tests if move would move the coin off the board
       int index = coinLocations[cn];
       if ((index - ns) <  0){
	   System.out.println("You have moved too far (ns is the number of spaces you want to move left)");
	   return false;

       } else{
  
	   //This checks if the new spot would jump over or land on the first piece to the left

	   //Determines first piece to the left of chosen coin
	   int leftMostCoinIndex = 0;
	   while(Strip[leftMostCoinIndex] == "_"){
	       leftMostCoinIndex++;
	   }
	   System.out.println("LMCI:" + (Integer.toString(leftMostCoinIndex)));
	   int chosenCoinLocation = coinLocations[cn];
	   int spacesApart = 1;
	   if (Integer.parseInt(Strip[leftMostCoinIndex]) != cn){
		    while(Strip[chosenCoinLocation-1] == "_"){
			chosenCoinLocation--;
			spacesApart++;
		    }
        
		    if ((coinLocations[cn]-ns)  <= (coinLocations[cn]-spacesApart)){
	       System.out.println("Illegal Move- you have moved on top of a coin or have moved passed a coin");
	       return false;

	       } 
	       }else{
	       if(leftMostCoinIndex-ns < 0){

		   System.out.println("Error: Leftmost coin would reach a negative index");
		   return false;
	       }
		   
	   }
	  
       }
        return true;
    }

      //Moves the piece and updates the coinLocation Array
       public void moveCoin(int cn, int ns){
       int coinLocation = coinLocations[cn];   
       Strip[coinLocation] = "_";
       Strip[coinLocation-ns] = String.valueOf(cn);
       coinLocations[cn] = coinLocation-ns;
     
       }
	
    //Ends the game once all coins are on the left 
    public boolean gameOver(){

	   int lastCoinIndex = 14;
	   while(Strip[lastCoinIndex] == "_"){

	lastCoinIndex--;
    }
	    
	   if ((lastCoinIndex+1) == coins){
            return true;
        }else{
            
            return false; 
        }
   }
    
}
