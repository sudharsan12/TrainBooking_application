package flight_ticket_booking;

import java.util.ArrayList;

public class Booking {
 
	int bookId;
	int flightNo;
	int noSeats;
	ArrayList<Integer>seatNo;
	String Class;
	boolean meals;
	int TotalCost;
	char status;
	public Booking(int bookId,int flightNo,int noSeats, ArrayList<Integer> seatNo, String Class,boolean meals, int totalCost, char status) {
		this.bookId = bookId;
		this.flightNo=flightNo;
		this.noSeats = noSeats;
		this.Class=Class;
		this.seatNo = seatNo;
		this.meals = meals;
		TotalCost = totalCost;
		this.status = status;
	}
	
	public void print()
	{
		
	System.out.println("Booking Id : "+bookId);
	System.out.println("Flight No : "+flightNo);
	if(Class.equals("EC"))
		System.out.print("Economy Class : ");
	else
		System.out.print("Bussiness Class : ");
	for(int i=0;i<noSeats;i++)
		System.out.print(seatNo.get(i)+" ");
	System.out.println("");
	System.out.println("Meals Required : ");
	if(status=='B')
	{
	if(meals==true)
	System.out.print("Yes");
	else
		System.out.print("No");
	System.out.println(" ");
	}else
		System.out.println("Canceled");
	
	System.out.println("Total cost : "+TotalCost);
	
	}
	
	
}
