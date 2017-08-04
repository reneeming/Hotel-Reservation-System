 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: FakeDB.java
*/

public class FakeDB {
	/**
	The hotels list in a fake Database. 
	*/
    private Hotel[] hotels;
 
    FakeDB() {
   	 hotels = new Hotel[2];
   	 
   	 addHotel(new Hotel("Holiday Inn", "New York City, NY", "A high-rise, full-service hotel with swimming pool, multiple restaurants, conference center, and exercise room."));
   	 addHotel(new Hotel("Holiday Inn Express", "San Jose, CA", "A middle-rise, full-service hotel with swimming pool, single restaurant, conference center, and exercise room."));
    }
 
	/**
	Get the list of hotels.
	* @return hotels
	*/
    public Hotel[] getHotels() {
   	 return hotels;
    }
 
	/**
	Add a hotel
	* @param hotel
	*/
    public void addHotel(Hotel hotel) {
   	 for (int i = 0 ; i < hotels.length; i++) {
   		 if (hotels[i] == null) {
   			 hotels[i] = hotel;
   			 return;
   		 }
   	 }
   	 Hotel[] newHotels = new Hotel[2 * hotels.length];
   	 for (int i = 0 ; i < hotels.length; i++) {
   		 newHotels[i] = hotels[i];
   	 }
   	 newHotels[hotels.length] = hotel;
   	 hotels = newHotels;
    }
 
	/**
	Delete a hotel
	* @param hotel
	*/
    public boolean deleteHotel(Hotel hotel) {
   	 int targetIndex = -1;
   	 for (int i = 0 ; i < hotels.length; i++) {
   		 if (hotels[i] == hotel) {
   			 targetIndex = i;
   			 break;
   		 }
   	 }
   	 if (targetIndex == -1) {
   		 return false;
   	 }
   	 for (int i = targetIndex; i < hotels.length - 1; i++) {
   		 hotels[i] = hotels[i + 1];
   	 }
   	 hotels[hotels.length - 1] = null;
   	 return true;
    }
}
