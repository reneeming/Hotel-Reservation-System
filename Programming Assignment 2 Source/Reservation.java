 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Reservation.java
*/

public class Reservation {
	/**
	The name for the Reservation.
	*/
    private String name;
    
	/**
	The number of occupants for the Reservation.
	*/
    private int numberOfOccupants;
    
	/**
	The rooms for the Reservation.
	*/
    private Room[] rooms;
    
	/**
	Parameterized constructor that accepts the String name, numberOfOccupants and rooms 
	* @param name, numberOfOccupants, rooms
	*/
    Reservation(String name, int numberOfOccupants, Room[] rooms) {
   	 this.name = name;
   	 this.numberOfOccupants = numberOfOccupants;
   	 this.rooms = rooms;
    }
    
	/**
	Get the name.
	* @return name
	*/
    public String getName() {
    	return name;
    }
    
	/**
	Get the rooms.
	* @return rooms
	*/
    public Room[] getRooms() {
    	return rooms;
    }
    
    @Override
    public String toString() {
    	return "name: " + name + ", occupants #: " + numberOfOccupants + ", rooms #: " + rooms.length;
    }
}
