package Daten;

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
