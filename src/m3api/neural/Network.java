package m3api.neural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Network {
	public static Random rnd = new Random();
	
	InputLayer input = new InputLayer();
	ArrayList<Layer> layers = new ArrayList<>();
	ResultLayer result = new ResultLayer();
	
	public Network() { }
	
	public void addInput(String inputname) {
		input.addNeuron(inputname);
	}
	
	public void addInputs(Set<String> inputnames) {
		for(String inputname : inputnames)
			addInput(inputname);
	}
	
	public void addResult(String resultname) {
		result.addNeuron(resultname);
	}
	
	public void setInputs(HashMap<String, Double> inputs) {
		for(Entry<String, Double> n : inputs.entrySet()) {
			for(Neuron w : input.neurons)
				if(((InputNeuron) w).desc.equals(n.getKey())) {
					((InputNeuron) w).setTriggered(n.getValue());
				}
		}
	}
		
	public HashMap<String, Double> run() {
		input.fire();
		HashMap<String, Double> ret = result.getResults();
		for(Layer l : layers) {
			for(Neuron w : l.neurons) {
				System.out.print(w.triggered + " ");
			}
			System.out.println();
		}
		reset();
		
		return ret;
	}
	
	public void addLayers(int neurons, int layers) {
		this.layers.clear();
		Layer n = this.result;
		System.out.println(n.neurons);
		for(int i = layers - 1; i >= 0; i--) {
			Layer k = new Layer(neurons, n);
			this.layers.add(k);	
			n = k;
		}
		
		Collections.reverse(this.layers);
		
		input.setChildLayer(this.layers.get(0));
	}
	
	public void reset() {
		input.reset();
		result.reset();
		for(Layer l : layers)
			l.reset();
	}
	
	public void populate(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		int width = layers.get(0).neurons.size();
		input.populate(width, weightMin, weightMax, thresholdMin, thresholdMax);	//Map input layer to first hidden layer
		
		for (int i = 0; i < layers.size() - 1; i++) {	//Map hidden layers
			Layer l = layers.get(i);
			l.populate(width, weightMin, weightMax, thresholdMin, thresholdMax);
		}
		
		layers.get(layers.size() - 1).populate(result.neurons.size(), weightMin, weightMax, thresholdMin, thresholdMax);	//Map last hidden layer to output
	}
	
	public void mutate(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		if(rnd.nextInt(layers.size()) > 0)
			layers.get(rnd.nextInt(layers.size())).mutate(weightMin, weightMax, thresholdMin, thresholdMax);
		else
			input.mutate(weightMin, weightMax, thresholdMin, thresholdMax);
	}
	
	public Network getClone() {
		Network ret = new Network();
		
		Layer last = ret.result = this.result.getClone();
		
		for(int i = layers.size() - 1; i >= 0; i--) {
			Layer l = layers.get(i);
			Layer clone = l.getClone(last);
			ret.layers.add(clone);
			last = clone;
		}
		
		Collections.reverse(ret.layers);
		
		Layer r = this.input.getClone(last);
		
		ret.input.neurons = r.neurons;
		ret.input.childlayer = r.childlayer;
		
		return ret;
	}
}
