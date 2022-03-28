package Controller;

import java.util.concurrent.Semaphore;

public class Threads extends Thread {
	private int i = 0;
	private String sentido;
	Semaphore semaforo;

	public Threads(int i, String sentido, Semaphore sem) {
		this.i = i;
		this.sentido = sentido;
		this.semaforo = sem;
	}

	public void run() {
		cruzarVia();	
		super.run();
	}

	public void cruzarVia() {
		try {
			semaforo.acquire();
			System.out.println("O carro: " + (i+1) + " está " + sentido);
			int tempoSleep = (int)((Math.random() * 2001) + 1000);
			sleep(tempoSleep);
			System.out.println("O carro: " + (i+1) + " cruzou.");
		} catch (Exception a) {
		} finally {
			semaforo.release();
		}
	}
}
