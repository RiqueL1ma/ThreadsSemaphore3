package view;

import java.util.concurrent.Semaphore;

import controller.DepositoThread;
import controller.SaqueThread;

public class Principal {

	public static void main(String[] args) {
		Semaphore limiteDeposito = new Semaphore(1);
		Semaphore limiteSaque = new Semaphore(1);
		for (int i = 1; i < 21; i++) {
			int tipo = (int) (Math.random() * 2);
			int id = (int) (Math.random() * 10000);
			int saldo = (int)((Math.random() * 10000) * 100) / 100;
			int valorTransacao = (int)((Math.random() * 10000) * 100) / 100;
			if (tipo == 0) {
				DepositoThread deposito = new DepositoThread(id, saldo, valorTransacao, limiteSaque);
				deposito.start();
			} else if (tipo == 1) {
				SaqueThread saque = new SaqueThread(id, saldo, valorTransacao, limiteDeposito);
				saque.start();
			}
		}

	}

}
