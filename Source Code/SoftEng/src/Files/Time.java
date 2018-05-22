package Files;

/* Classname: Time
*
* Programmers/Authors: 
* 
*  1.Milos Tomic
*  2.Maja Dusanic 
*  3.Alexander Teuchtmann 
*  4.Andrea Aistleithner 
*  5.Christopher Huber 
* 
*  Date: 22.05.2018
*  Version: 1.0.20
*
* Copyright notice
* - Programm is being build by the above mentioned programmers
* 
* Purpose of program: 
* - Time scheduling of projects, tasks etc.
*/

public class Time {
		private  int hour;
		private  int min;

		
		public Time(int hour, int min) {
			this.hour = hour;
			this.min = min;
		}

		
		public int getHour() {
			return hour;
		}

		
		public int getMin() {
			return min;
		}

		
		public String toString() {
			return String.format("%1$02d:%2$02d", getHour(), getMin());
		}
}
