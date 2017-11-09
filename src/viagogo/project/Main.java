package viagogo.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Main m = new Main();
		
		// Create List of type Event and generate Events
		List<Event> events = m.generateEvents();
		
		System.out.print("Enter event coordinates X,Y : ");
		Scanner sc = new Scanner(System.in);
		
		// parse user input into int[] array
		int[] coords = new int[2]; int i = 0;
		for(String str: sc.nextLine().split(",")){
		    coords[i++] = Integer.parseInt(str);
		} 
		sc.close();
		
		// call method to print 5 closest events
		m.closestEvents(events, coords);
	}
	
	/**
	 * @param ID
	 * @return an Event object with an List<Double> of tickets
	 *  and a pair of coordinates (-10 <= x <= 10) and (-10 <= y <= 10)
	 */
	public Event generateSeed(int ID){
		
		Random rand = new Random();
		
		int[] coordinates = new int[2];
		
		coordinates[0] = rand.nextInt(10 + 10 + 1) - 10;
		coordinates[1] = rand.nextInt(10 + 10 - 1) - 10;
		
		int randomTickets = rand.nextInt(20);
		List<Double> tickets = new ArrayList<>();
		
		for(int i = 0; i < randomTickets; i++){
			double randomPrice = Math.round((rand.nextDouble() * 50 + 1) * 100);
			randomPrice /= 100;
			tickets.add(randomPrice);
		}
		return new Event(ID, tickets, coordinates);
	}
	
	/**
	 * @return a List<Event> of (in this case) 10 events 
	 */
	public List<Event> generateEvents(){
		List<Event> events = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			events.add(generateSeed(i));
		}
		return events;
	}
	
	/**
	 * @param int[] here
	 * @param int[] there
	 * @return the manhattan distance between two coordinates.
	 * ManhattanDistance(p,q) = |px-qx| + |py-qy|
	 */
	public int manhattanDist(int[] here, int[] there){
		return (Math.abs(there[0] - here[0]) + Math.abs(there[1] - here[1]));
	}
	
	/**
	 * @param List<Event> events
	 * @param int[] coords
	 */
	public void closestEvents(List<Event> events, int[] coords){
		
		// int[] array of all distances from user input
		int[] eventDist = new int[events.size()];
		
		// Custom HashMap to allow for multiple values
		CustomMap<Integer, Event> mapping = new CustomMap<>();
		
		/** iterate through each event and calculate distance.
		 * put distance into array (indexed by eventID).
		 * map dist to event so the mapping between dist 
		 * and event isn't broken when distance array is sorted.
		 */
		
		for(Event event: events){
			int dist = manhattanDist(event.getCoords(), coords);
			eventDist[event.getID()] = dist;
			mapping.put(dist, event);
		}
		
		// sorted by smallest distance 
		Arrays.sort(eventDist);
		
		StringBuilder sb = new StringBuilder();
		
		Event event = null;
		
		// print 5 smallest distances from user input
		for(int i = 0; i <= 4; i++){
			event = mapping.get(eventDist[i]).remove(0);
			sb.append("Event: " + event.getID());
			sb.append(" - $" + event.getMinTicketPrice());
			sb.append(", Distance " + eventDist[i] + "\n");
		}
	}
}
