package app;

import java.util.concurrent.Semaphore;

import entities.Counter;
import entities.Generator;
import entities.Padronizer;

public class Main {

	public static void main(String[] args) {
		
		Semaphore gate = new Semaphore(1);
		Generator gen = new Generator(gate);
		Padronizer pad = new Padronizer(gate, gen);
		Counter counter = new Counter(gate, pad);
		
		gen.generateText();
		pad.toUpperCase();
		counter.getTotalVowels();
	}

}
