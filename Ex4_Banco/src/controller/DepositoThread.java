package controller;

import java.util.concurrent.Semaphore;

public class DepositoThread extends Thread {
	private int id;
	private double saldo;
	private double valorTransacao;
	private Semaphore limitacao;

	public DepositoThread(int id, double saldo, double valorTransacao, Semaphore limitacao) {
		this.id = id;
		this.saldo = saldo;
		this.valorTransacao = valorTransacao;
		this.limitacao = limitacao;
	}

	@Override
	public void run() {
		try {
			limitacao.acquire();
			creditar();
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}

	public void creditar() {
		System.out.println("Conta " + id + " - Saldo Anterior: R$ " + saldo +
		" Deposito no valor de: R$ " + valorTransacao + " Novo saldo: R$" + (saldo + valorTransacao));
		
		this.saldo += valorTransacao;
	}

}