package PhiloDinnerSem;

public class Philo implements Runnable {

	private int id;
	private Table table;
	final static int TL = 100;
	
	public Philo(int id, Table table) {
		this.id = id;
		this.table = table;

		new Thread((Runnable) this, "" + id).start();
	}

	@Override
	public void run() {
		while (true) {
			thinking();
			getFork();
			
			eating();
			returnFork();
		}
	}

	private void thinking() {
		try {
			System.out.println("The philo [" + (this.id+1) + "] thought");
			int tmp = (int) (Math.random() * TL);
			Thread.sleep(tmp);
		} catch (InterruptedException e) {
			System.out.println("The philo got a headache");
		}

	}

	private void eating() {
		try {
			System.out.println("The Philo [" + (this.id+1) + "] eat");
			int tmp = (int) (Math.random() * TL);
			Thread.sleep(tmp);
		} catch (InterruptedException e) {
			System.out.println("The philo had a stomachache");
		}
	}

	private void getFork() {
		table.getFork(id);
	}

	private void returnFork() {
		table.returnFork(id);
	}
}
