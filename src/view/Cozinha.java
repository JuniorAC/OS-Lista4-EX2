package view;

import java.util.concurrent.Semaphore;

import controller.Prato;

public class Cozinha {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore (1);
		
		for(int i = 1; i<=5; i++) {
			
			Prato P = new Prato(i, semaforo);
			P.start();

	}

}
}