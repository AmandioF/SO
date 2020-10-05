package PhiloDinnerMon;
public class Dinner {
   public static void main (String[] args) {
      Table table = new Table ();
      for (int philo = 0; philo < 5; philo++) {
         new Philo(philo, table).start();
      }
   }
}