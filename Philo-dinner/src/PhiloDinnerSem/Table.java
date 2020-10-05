package PhiloDinnerSem;

import java.util.concurrent.Semaphore;

public class Table {
	private int NMB_PHILOS;
	private Semaphore[] forks;

	public Table(int philos) {    
		this.NMB_PHILOS = philos;
		this.forks = new Semaphore[philos];
		
		for(int i = 0; i < philos; i++) {
			this.forks[i] = new Semaphore(1);
		}
	}
	
	public int leftFork(int philo){
		return philo;
	}

	public int rightFork(int philo) {
		return (philo + 1)%NMB_PHILOS;
   	}
	
	public void getFork(int philo) {
		
		int leftForkId = leftFork(philo);
		int rightForkId = rightFork(philo);
		
        if(leftForkId > rightForkId){
        	int aux = rightForkId;
        	rightForkId = leftForkId;
        	leftForkId = aux;
        }
        
		try {
			forks[leftForkId].acquire();
            forks[rightForkId].acquire();
			System.out.println("Philo [" + philo + "] is eating");
		}catch (InterruptedException e) {
			System.out.println("The philo died of hunger (starvation)");
        }
	}

	public void returnFork(int philo) {

		int leftForkId = leftFork(philo);
		int rightForkId = rightFork(philo);
		
        forks[leftForkId].release();
        forks[rightForkId].release();
		System.out.println("Philo [" + philo + "] eat. now he will think");
    }
	
}