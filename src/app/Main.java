package app;

import java.util.concurrent.Semaphore;

import entities.Counter;
import entities.Generator;
import entities.Padronizer;

public class Main {

	public static void main(String[] args) {
		
		Semaphore gateGenerator = new Semaphore(1);
		Semaphore gatePadronizer = new Semaphore(0);
		Semaphore gateCounter = new Semaphore(0);
		Generator gen = new Generator(gateGenerator, gatePadronizer);
		Padronizer pad = new Padronizer(gatePadronizer, gateCounter, gen);
		Counter counter = new Counter(gateCounter, gateGenerator, pad);
		
		gen.start();
		pad.start();
		counter.start();
	
		try {
			gen.join();
			pad.join();
			counter.join();
		}catch(Exception e) {
			
		}
		counter.printVowels();
	
	}

}
