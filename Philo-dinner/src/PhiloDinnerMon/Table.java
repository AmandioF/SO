package PhiloDinnerMon;

public class Table {
	
   final static int THINKING = 1; // no op
   final static int EATING = 2;  //  running 
   final static int HUNGER = 3;  //  waiting
   final static int NMB_PHILO = 5;
   boolean[] forks = new boolean[NMB_PHILO];
   int[] philos = new int[NMB_PHILO];

   public Table(){
      for (int i = 0; i < 5; i++){
         forks[i] = true;
         philos[i] = THINKING;
      }
   }

   public synchronized void getFork(int philo){
      philos[philo] = HUNGER;
      
      int leftForkId = leftFork(philo);
	  int rightForkId = rightFork(philo);
	  
      while(forks[leftForkId] == false || philos[rightForkId] == EATING){
         try{
            wait();
         }
         catch (InterruptedException e)
         {
        	 System.out.println("The philo died of hunger (starvation)");
         }
      }
      
      forks[leftForkId] = false;
      forks[rightForkId] = false;
      philos[philo] = EATING;
      printState();
   }

   public synchronized void returnFork (int philo) {
      forks[leftFork(philo)] = true;
      forks[rightFork(philo)] = true;
      
      philos[philo] = THINKING;
      printState();
      
      notifyAll();
   }

   public int leftFork(int philo){
      return philo;
   }

   public int rightFork(int philo) {
      return (philo + 1)%NMB_PHILO;
   }

   public void printState(){
      String msg = "";
      System.out.print("Philos = [ ");
      for (int i = 0; i < NMB_PHILO; i++) {
         switch (philos[i])
         {
            case THINKING:
               msg = "THINKING";
               break;
            case EATING:
               msg = "EATING";
               break;
            case HUNGER:
               msg = "HUNGER";
               break;
         }
         System.out.print(msg + " ");
      }
      System.out.println("]");
   }

}
