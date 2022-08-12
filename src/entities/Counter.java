package entities;

import java.util.concurrent.Semaphore;

public class Counter extends Thread{

	private String text;
	private static char vowels[] = {'a', 'e', 'i', 'o', 'u'};
	private Semaphore gate;
	private Padronizer padronizer;
	
	public Counter(Semaphore gate, Padronizer padronizer) {
		this.gate = gate;
		this.padronizer = padronizer;
	}
	
	public void getTotalVowels() {
		this.text = this.padronizer.getUpperCaseText();
		
		int count = vowels.length;
		int vowelCount = 0;
		
		for(int i = 0; i < count; i++) {
			for(int j = 0; j < text.length(); j++) {
				if(text.charAt(j) == vowels[i] || 
						text.charAt(j) == Character.toUpperCase(vowels[i])) 
				{
					vowelCount++;
				}
			}
		}
		System.out.println("Total vowel count at: "+vowelCount);
	}
	
	public void run() {
		try {
			gate.acquire();
			getTotalVowels();
			gate.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
