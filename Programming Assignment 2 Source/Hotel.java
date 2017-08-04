 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Hotel.java
*/

public class Hotel {
	/**
	Name of the hotel.
	*/
	private String name;
	
	/**
	City of the hotel.
	*/
	private String city;
	
	/**
	Description of the hotel.
	*/
	private String description;

	/**
	The rooms of the hotel.
	*/
	private Room[] rooms;
	
	/**
	The reservations of the hotel.
	*/
	private Reservation[] reservations;

	/**
	Parameterized constructor that accepts the String name, city and description 
	* @param name, city, desc
	*/
	Hotel(String name, String city, String desc) {
		setName(name);
		setCity(city);
		setDescription(desc);

		/*
		 * Assume every hotel have 2 room and one room have 2 queen size bed(not
		 * available), and the other one have 1 king size bed(available).
		 */
		rooms = new Room[2];
		addRoom(new Room(1, 0, 1));
		addRoom(new  Room(2, 2, 0));
		rooms[0].setAvailablility(true);
		rooms[1].setAvailablility(false);
		Room[] reservedRooms = {rooms[1]};
		
		reservations = new Reservation[2];
		addReservation(new Reservation("Jonathan Corley", 2, reservedRooms));
	}

	/**
	Remove all rooms of the hotel.
	*/
	public void cleanRooms() {
		for (Room room: rooms) {
			room = null;
		}
	}
	
	/**
	Get the name of hotel.
	* @return name
	*/
	public String getName() {
		return name;
	}

	/**
	Set the name of hotel.
	* @param val
	*/
	public void setName(String val) {
		this.name = val;
	}

	/**
	Get the city of hotel.
	* @return city
	*/
	public String getCity() {
		return city;
	}

	/**
	Set the city of hotel.
	* @param val
	*/
	public void setCity(String val) {
		this.city = val;
	}

	/**
	Get the description of hotel.
	* @return description
	*/
	public String getDescription() {
		return description;
	}

	/**
	Set the description of hotel.
	* @param val
	*/
	public void setDescription(String val) {
		this.description = val;
	}

	/**
	Get all rooms of hotel.
	* @return rooms
	*/
	public Room[] getRooms() {
		return rooms;
	}

	/**
	Get all reservations of hotel.
	* @return reservations
	*/
	public Reservation[] getReservation() {
		return reservations;
	}

	/**
	Get all reservations of hotel.
	* @return res
	*/
	public int getNumberOfAvailableRoomWithQueenSizedBeds() {
		int res = 0;
		for (Room room : rooms) {
			if (room.getQueenSizeBedsNumber() > 0 && room.getAvailablility()) {
				res++;
			}
		}
		return res;
	}

	/**
	Get all munber of available room with king sized bed of hotel.
	* @return res
	*/
	public int getNumberOfAvailableRoomWithKingSizedBeds() {
		int res = 0;
		for (Room room : rooms) {
			if (room.getKingSizeBedsNumber() > 0 && room.getAvailablility()) {
				res++;
			}
		}
		return res;
	}

	/**
	Get all munber of available room of hotel.
	*/
	public int getNumberOfTotalAvailableRooms() {
		return getNumberOfAvailableRoomWithQueenSizedBeds() + getNumberOfAvailableRoomWithKingSizedBeds();
	}

	/**
	Get next available room with queen sized bed of hotel.
	*/
	public Room getNextAvailableQueenRoom() {
		for (Room room : rooms) {
			if (room.getQueenSizeBedsNumber() > 0 && room.getAvailablility()) {
				return room;
			}
		}
		return null;
	}

	/**
	Get next available room with king sized bed of hotel.
	*/
	public Room getNextAvailableKingRoom() {
		for (Room room : rooms) {
			if (room.getKingSizeBedsNumber() > 0 && room.getAvailablility()) {
				return room;
			}
		}
		return null;
	}

	/**
	Add a reservation for the hotel.
    * @param reservation
	*/
	public void addReservation(Reservation reservation) {
		for (int i = 0; i < reservations.length; i++) {
			if (reservations[i] == null) {
				reservations[i] = reservation;
				return;
			}
		}
		Reservation[] newReservations = new Reservation[2 * reservations.length];
		for (int i = 0; i < reservations.length; i++) {
			newReservations[i] = reservations[i];
		}
		newReservations[reservations.length] = reservation;
		reservations = newReservations;
	}

	/**
	Delete a reservation for the hotel.
    * @param reservation
	*/
	public boolean deleteReservation(Reservation reservation) {
		int targetIndex = -1;
		for (int i = 0; i < reservations.length; i++) {
			if (reservations[i] == reservation) {
				targetIndex = i;
				break;
			}
		}
		if (targetIndex == -1) {
			return false;
		}
		for (int i = targetIndex; i < reservations.length - 1; i++) {
			reservations[i] = reservations[i + 1];
		}
		reservations[reservations.length - 1] = null;
		return true;
	}
	
	/**
	Add a room for the hotel.
    * @param room
	*/
    public void addRoom(Room room) {
        for (int i = 0 ; i < rooms.length; i++) {
          if (rooms[i] == null) {
            rooms[i] = room;
            return;
          }
        }
        Room[] newRooms = new Room[2 * rooms.length];
        for (int i = 0 ; i < rooms.length; i++) {
          newRooms[i] = rooms[i];
        }
        newRooms[rooms.length] = room;
        rooms = newRooms;
       }
    
	/**
	Delete a room for the hotel.
    * @param room
	*/
     public boolean deleteRoom(Room room) {
        int targetIndex = -1;
        for (int i = 0 ; i < rooms.length; i++) {
          if (rooms[i] == room) {
            targetIndex = i;
            break;
          }
        }
        if (targetIndex == -1) {
          return false;
        }
        for (int i = targetIndex; i < rooms.length - 1; i++) {
          rooms[i] = rooms[i + 1];
        }
        rooms[rooms.length - 1] = null;
        return true;
     }
}
