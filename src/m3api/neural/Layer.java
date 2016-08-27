package m3api.neural;

import java.util.ArrayList;
import java.util.Random;

public class Layer {
	public static Random rnd = new Random();
	
	final ArrayList<Neuron> neurons = new ArrayList<>();
	private Layer childlayer;
	
	protected Layer() {}
	
	public Layer(int neurons) {
		for(int i = 0; i < neurons; i++) {
			this.neurons.add(new Neuron());
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
	
	public void populate(Layer after, double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		for(int i = 0; i < after.neurons.size(); i++) {
			Neuron n = this.neurons.get(i);
			n.tofire.add(after.getRandom());
			n.populate(weightMin, weightMax, thresholdMin, thresholdMax);
		}
	}
	
	public void reset() {
		for(Neuron n : neurons)
			n.reset();
	}
}
