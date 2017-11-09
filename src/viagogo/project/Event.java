package viagogo.project;

import java.util.List;

public class Event {
	
	private int eventID = 0;
	private List<Double> tickets;
	private int[] coordinates = new int[2];
	
	public Event(int eventID, List<Double> tickets, int[] coordinates){
		this.eventID = eventID;
		this.tickets = tickets;
		this.coordinates = coordinates;
	}
	/**
	 * quick O(n) runtime method to get minimum ticket
	 * price from array
	 * @return minimum price as double
	 */
	public double getMinTicketPrice(){
		double min = Integer.MAX_VALUE;
		for(Double ticket: tickets){
			min = Math.min(min, ticket);
		}
		return min;
	}
	// setup getters for Event
	public int getID() {return this.eventID;}
	public int[] getCoords() {return this.coordinates;}

}
