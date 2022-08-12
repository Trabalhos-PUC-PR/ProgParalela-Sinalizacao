package entities;

import java.util.concurrent.Semaphore;

public class Padronizer extends Thread{

	private Semaphore gate;
	private Semaphore gateCounter;
	private String oldText;
	private String upperCaseText;
	Generator gen;

	public Padronizer(Semaphore gate, Semaphore gateCounter, Generator gen) {
		this.gate = gate;
		this.gateCounter = gateCounter;
		this.gen = gen;
	}
	
	public String getUpperCaseText() {
		return upperCaseText;
	}
	
	public void toUpperCase() {
		this.oldText = gen.getText();
		this.upperCaseText = oldText.toUpperCase();
		System.out.println("...upper cased: "+this.upperCaseText);
	}
	
	public void run() {
		try {
			gate.acquire();
			toUpperCase();
			gateCounter.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
