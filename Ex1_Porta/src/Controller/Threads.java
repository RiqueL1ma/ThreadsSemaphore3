package Controller;

import java.util.concurrent.Semaphore;

public class Threads extends Thread {
	private int i = 0;
	Semaphore semaforo;

	public Threads(int i, Semaphore sem) {
		this.i = i;
		this.semaforo = sem;
	}

	public void run() {
		try {
			int percorrido = 0;
			while (percorrido < 200) {
				int percorreu = (int)((Math.random() * 3) + 4); 
				sleep(1000);
				percorrido = percorrido + percorreu;
				System.out.println("A pessoa: " + i 
				+ " percorreu = " + percorreu + 
				" espaço percorrido: " + percorrido);	
			}
			System.out.println("Pessoa: " + i + " chegou na porta.");
			cruzarPorta();	
		} catch (Exception e) {
		}
		super.run();
	}

	public void cruzarPorta() {
		try {
			semaforo.acquire();
			System.out.println("Pessoa: " + i + " Cruzando a porta.");
			int tempoSleep = (int)((Math.random() * 2001) + 1000);
			sleep(tempoSleep);
			System.out.println("Pessoa: " + i + " entrou.");
		} catch (Exception a) {
		} finally {
			semaforo.release();
		}
	}
}
