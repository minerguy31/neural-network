package m3api.neural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Network {
	public static Random rnd = new Random();
	
	InputLayer input = new InputLayer();
	ArrayList<Layer> layers = new ArrayList<>();
	ResultLayer result = new ResultLayer();
	
	public void addInput(String inputname) {
		input.addNeuron(inputname);
	}
	
	public void addResult(String resultname) {
		result.addNeuron(resultname);
	}
		
	public HashMap<String, Boolean> run() {
		input.fire();
		HashMap<String, Boolean> ret = result.getResults();
		reset();
		return ret;
	}
	
	public void addLayers(int neurons, int layers) {
		for(int i = 0; i < layers; i++) {
			this.layers.add(new Layer(neurons));
		}
	}
	
	public void reset() {
		input.reset();
		result.reset();
	}
	
	public void populate() {
		for(InputNeuron in : input.neurons) {	//Map from input layer to hidden layer 0
			in.tofire.add(layers.get(0).getRandom());
		}
		
		for (int i = 0; i < layers.size() - 1; i++) {	//Map hidden layers
			Layer l = layers.get(i);
			l.populate(layers.get(i + 1));
		}
		
		for(Neuron n : layers.get(layers.size() - 1).neurons) {	//Map last hidden layer to output
			n.tofire.add(result.getRandom());
		}
	}
}
