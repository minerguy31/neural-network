package m3api.neural;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
	public static Random rnd = new Random();
	
	final ArrayList<Neuron> neurons = new ArrayList<>();
	protected Layer childlayer;
	
	protected Layer() {}
	
	public Layer(int neurons, Layer l) {
		setChildLayer(l);
		for(int i = 0; i < neurons; i++) {
			this.neurons.add(new Neuron(childlayer));
		}
	}
	
	public void setChildLayer(Layer l) {
		childlayer = l;
	}
	
	public void addNeuron(Neuron n) {
		neurons.add(n);
	}
	
	public Layer childLayer() {
		if(childlayer != null)
			return childlayer;
		else
			throw new RuntimeException("No child layer set!");
	}
	
	public Neuron getRandom() {
		int r = rnd.nextInt(neurons.size());
		return neurons.get(r);
	}
	
	public void populate(int nlay, double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		for(int i = 0; i < this.neurons.size(); i++) {
			Neuron n = this.neurons.get(i);
			n.populate(nlay, weightMin, weightMax, thresholdMin, thresholdMax);
		}
	}
	
	public void reset() {
		for(Neuron n : neurons)
			n.reset();
	}
	
	public void mutate(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		int index = rnd.nextInt(neurons.size());
		neurons.get(index).mutate(weightMin, weightMax, thresholdMin, thresholdMax, 
				neurons.get(rnd.nextInt(neurons.size())));
	}
}
