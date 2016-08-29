package m3api.neural;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {
	public static Random r = new Random();
	public ArrayList<Double> weights = new ArrayList<>();
	
	public Layer nextlayer;
	
	public double triggered = 0;
	public boolean alreadyran = false;
	public double threshold = 2;
	
	public Neuron(Layer n) {
		nextlayer = n;
	}
	
	public void fire(double b) {
		
		
		
		triggered += b;
		
		if(!alreadyran && triggered >= threshold) {
			
			ArrayList<Neuron> neurons = nextlayer.neurons;
			for (int i = 0; i < neurons.size(); i++) {
				Neuron n = neurons.get(i);
				
				n.fire(weights.get(i));
			}
			alreadyran = true;
		}
	}
	
	public void reset() {
		triggered = 0;
		alreadyran = false;
	}
	
	public void populate(int nlay, double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		for(int i = 0; i < nlay; i++)
			this.weights.add(weightMin + (weightMax - weightMin) * r.nextDouble());
		this.threshold = thresholdMin + (thresholdMax - thresholdMin) * r.nextDouble();
	}
	
	public void mutateValues(int nlay, double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		
		this.weights.set(nlay, this.weights.get(nlay) + weightMin + (weightMax - weightMin) * r.nextDouble());
		this.threshold += thresholdMin + (thresholdMax - thresholdMin) * r.nextDouble();
	}
	
	public void mutate(double weightMin, double weightMax, double thresholdMin, double thresholdMax, Neuron randomneuron) {
		int i = r.nextInt(weights.size());
		
		mutateValues(i, weightMin, weightMax, thresholdMin, thresholdMax);
	}
	
	@SuppressWarnings("unchecked")
	public Neuron getClone(Layer nextlayer) {
		Neuron ret = new Neuron(nextlayer);
		
		ret.weights = ((ArrayList<Double>) weights.clone());
		ret.threshold = this.threshold;
		
		return ret;
	}
}
