package View;

import java.util.concurrent.Semaphore;

import Controller.Threads;

public class Principal {
	public static void main(String args[]) {
		String sentido[] = {"descendo", "Subindo", "indo para a esquerda", "indo para a direita"}; 
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Thread exec = new Threads(i, sentido[i], semaforo);
			exec.start();
		}
		
		
		
	}
}
