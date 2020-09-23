package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Prato extends Thread{
	
	private Semaphore semaforo;
	private int ID;
	private Random temp = new Random();

	public Prato(int ID, Semaphore semaforo) {
		this.ID = ID;
		this.semaforo = semaforo;
	}

	public void run () {
		if (ID % 2 == 0) {
			preparo("Lasanha a Bolonhesa", temp.nextInt(600)+600);
		
		}else {
			preparo("Sopa de Cebola", temp.nextInt(300)+500);
		}
	}

	private void preparo(String nome, int temp) {
		System.out.println("ID#" +ID + " " + nome + " iniciou o preparo" );
		long porcentagem = 0;
		porcentagem = 10000 / temp;
		long totalPorcentagem = 0;
		
		for (int i = 0; i<=temp; i += 100) {
			try {
				sleep(100);
				totalPorcentagem += porcentagem;
				if (totalPorcentagem >100) {
					System.out.println("ID#" +ID + " " + nome + " Está " +  "100%");	
				}else {
				System.out.println("ID#" +ID + " " + nome + " Está " +  totalPorcentagem + "%");
				}
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		entrega(ID);
	}

	private void entrega(int iD) {
		
		try {
			semaforo.acquire();
			sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("ID#" +ID + " " + ID + " Prato finalizado");
			semaforo.release();
		
		}
	}
}
