package PhiloDinnerMon;

public class Philo extends Thread {
   final static int TL = 100;
   Table table;
   int philo;

   public Philo (String name, Table dinner_table, int phil){
      super(name);
      table = dinner_table;
      philo = phil;
   }

   public void run (){
      int tmp = 0;
      while (true){
         tmp = (int) (Math.random() * TL);
         think(tmp);
         getFork();
         tmp = (int) (Math.random() * TL);
         eat(tmp);
         returnFork();
      }
   }

   public void think(int tmp){
      try{
         sleep(tmp);
         System.out.println("The philo [" + (philo+1) + "] thought");
      }
      catch (InterruptedException e){
         System.out.println("The philo got a headache");
      }
   }

   public void eat(int tmp){
      try{
         sleep(tmp);
         System.out.println("The Philo [" + (philo+1) + "] eat");
      }
      catch (InterruptedException e)
      {
         System.out.println("The philo had a stomachache");
      }
   }

   public void getFork()
   {
      table.getFork(philo);
   }

   public void returnFork()
   {
      table.returnFork(philo);
   }
}
