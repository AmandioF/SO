package PhiloDinnerSem;

public class Dinner {
	public static void main(String[] args) {
		Table table = new Table(5);
		for (int i = 0; i < 5; i++) {
			new Philo(i, table);
		}
	}
}