package entities;

import java.util.concurrent.Semaphore;

public class Counter extends Thread{

	private String text;
	private static char vowels[] = {'a', 'e', 'i', 'o', 'u'};
	private Semaphore gate;
	private Semaphore gateGenerator;
	private Padronizer padronizer;
	private int vowelCount;
	
	public Counter(Semaphore gate, Semaphore gateGenerator, Padronizer padronizer) {
		this.gate = gate;
		this.gateGenerator = gateGenerator;
		this.padronizer = padronizer;
	}
	
	public void getTotalVowels() {
		this.text = this.padronizer.getUpperCaseText();
		
		int count = vowels.length;
		vowelCount = 0;
		
		for(int i = 0; i < count; i++) {
			for(int j = 0; j < text.length(); j++) {
				if(text.charAt(j) == vowels[i] || 
						text.charAt(j) == Character.toUpperCase(vowels[i])) 
				{
					vowelCount++;
				}
			}
		}
	}
	
	public void printVowels() {
		System.out.println("Total vowel count at: "+vowelCount);
	}
	
	public void run() {
		try {
			gate.acquire();
			getTotalVowels();
			gateGenerator.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
