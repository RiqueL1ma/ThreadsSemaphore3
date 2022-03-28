package controller;

import java.util.concurrent.Semaphore;

public class SaqueThread extends Thread {
	private int id;
	private double saldo;
	private double valorTransacao;
	private Semaphore limitacao;

	public SaqueThread(int idConta, double saldoConta, double valorTransacao, Semaphore limitacao) {
		this.id = idConta;
		this.saldo = saldoConta;
		this.valorTransacao = valorTransacao;
		this.limitacao = limitacao;
	}

	@Override
	public void run() {
		try {
			limitacao.acquire();
			if(saldo > valorTransacao){
				debitar();
				sleep(2000);
			}
			else {
				System.out.println("Conta " + id + " - Saldo Atual: R$ " + saldo + " Saque no valor de: R$" + valorTransacao + ", Saldo em conta insuficiente!! ");
				sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}

	public void debitar() {
		System.out.println("Conta " + id + " - Saldo Anterior: R$ " + saldo + " Saque no valor de: R$" + valorTransacao + " Novo saldo: R$" + (saldo - valorTransacao));
		this.saldo -= valorTransacao;
	}
}
