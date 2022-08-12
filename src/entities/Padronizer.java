package entities;

import java.util.concurrent.Semaphore;

public class Padronizer extends Thread{

	private Semaphore gate;
	private String oldText;
	private String upperCaseText;
	Generator gen;

	public Padronizer(Semaphore gate, Generator gen) {
		this.gate = gate;
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
			gate.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
