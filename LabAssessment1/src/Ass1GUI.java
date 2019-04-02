/*
 * Ass1GUI.java
 *
 *  Created on: 14 Mar 2019
 *      Author: Robert Trpeski
 *    Student#: c3244194
 *
 *
 */


import javax.swing.*;

public class Ass1GUI
{
    public static void main (String[] args)
    {
        float totalHours = 0;
		float hours = 0;
		float salary = 0;
		float totalSalary = 0;
		int numberOfWeeks = 5;
		for (int week = 1; week <= numberOfWeeks; week++){
			hours = Float.parseFloat(JOptionPane.showInputDialog("Please enter number of hours worked in week " + week)); //request number of hours
			totalHours = totalHours + hours;
			while (hours < 0){ // if hours are negative, give invalid input error
				hours = Float.parseFloat(JOptionPane.showInputDialog("INVALID INPUT!\nPlease enter number of hours worked in week " + week));
			}
			if (hours <= 40 && hours >= 0){ // if hours less than or equal to 40 hourly rate is $10
				salary = hours*10;
			}
			else if (hours > 40){ // for any hours additional to 40, hourly rate is $15
				salary = (40*10) + (hours-40)*15;
			}
			totalSalary = totalSalary + salary;
			JOptionPane.showMessageDialog(null, "Salary for week "+(week)+" is: $"+String.format("%.2f", salary)+"\n"); // Print salary for the week
		}
		JOptionPane.showMessageDialog(null, "Total salary for 5 weeks is: $"+String.format("%.2f", totalSalary)); // print total salary
	}
}
