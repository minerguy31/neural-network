package m3api.neural;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {
	public static Random r = new Random();
	public ArrayList<Neuron> tofire = new ArrayList<>();
	
	public double weight = 1;
	public double triggered = 0;
	public boolean alreadyran = false;
	public double threshold = 2;
	
	public void fire(double b) {
		triggered += b;
		
		if(!alreadyran && triggered >= threshold) {
			for(Neuron n : tofire)
				n.fire(weight);
			alreadyran = true;
		}
	}
	
	public void reset() {
		triggered = 0;
		alreadyran = false;
	}
	
	public void populate(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		this.weight = weightMin + (weightMax - weightMin) * r.nextDouble();
		this.threshold = thresholdMin + (thresholdMax - thresholdMin) * r.nextDouble();
	}
	
	public void mutateValues(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		this.weight += weightMin + (weightMax - weightMin) * r.nextDouble();
		this.threshold += thresholdMin + (thresholdMax - thresholdMin) * r.nextDouble();
	}
	
	public void mutateConnections(Neuron randomneuron) {
		tofire.remove(r.nextInt(tofire.size()));
		tofire.add(randomneuron);
	}
	
	public void mutate(double weightMin, double weightMax, double thresholdMin, double thresholdMax, Neuron randomneuron) {
		if(r.nextBoolean())
			mutateValues(weightMin, weightMax, thresholdMin, thresholdMax);
		else
			mutateConnections(randomneuron);
	}
}
