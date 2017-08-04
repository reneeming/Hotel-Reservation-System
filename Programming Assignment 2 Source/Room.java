 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Room.java
*/

public class Room {
	/**
	The id of the Room.
	*/
	private int id;
	
	/**
	The availablility of the Room.
	*/
    private boolean availablility = true;
    
	/**
	The queen sized Beds Number of the Room.
	*/
    private int queenSizeBedsNumber;
	
    /**
	The king sized Beds Number of the Room.
	*/    
    private int kingSizeBedsNumber;
    
	/**
	Parameterized constructor that accepts the id, queenSizeBedsNumber and kingSizeBedsNumber
	* @param id, queenSizeBedsNumber, kingSizeBedsNumber
	*/
    Room(int id, int queenSizeBedsNumber, int kingSizeBedsNumber) {
    	this.id = id;
   	 	this.queenSizeBedsNumber = queenSizeBedsNumber;
   	 	this.kingSizeBedsNumber = kingSizeBedsNumber;
    }
    
    /**
	Get the queen sized bed number
	* @return queenSizeBedsNumber
	*/  
    public int getQueenSizeBedsNumber() {
   	 	return queenSizeBedsNumber;
    }
    
    /**
	Get the king sized bed number
	* @return kingSizeBedsNumber
	*/ 
    public int getKingSizeBedsNumber() {
   	 	return kingSizeBedsNumber;
    }
    
    /**
	Get the availability of room
	* @return availablility
	*/ 
    public boolean getAvailablility() {
   	 	return availablility;
    }
    
    /**
	Set the availability of room
	* @param val
	*/ 
    public void setAvailablility(boolean val) {
   	 	availablility = val;
    }
    
    @Override
    public String toString() {
    	return "Available: " + availablility + ", queen-sized beds #: " + queenSizeBedsNumber + ", king-sized bed #: " + kingSizeBedsNumber; 
    }
}
