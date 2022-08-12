package entities;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Generator extends Thread {

	private String text;
	private Semaphore gate;
	private Semaphore gatePadronizer;

	public Generator(Semaphore gate, Semaphore gatePadronizer) {
		this.gate = gate;
		this.gatePadronizer = gatePadronizer;
		this.text = "";
	}

	public String getText() {
		return text;
	}

	public void generateText() {
		int cont = 0;
		Random r = new Random();
		while (cont < 10) {
			char newChar = ' ';
			newChar = (char) (r.nextInt(26) + 'a');
			this.text += (r.nextInt(2) == 1 ? Character.toUpperCase(newChar) : newChar);
			cont++;
		}
		System.out.println("generated text: " + text);
	}

	public void run() {
		try {
			gate.acquire();
			generateText();
			gatePadronizer.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
