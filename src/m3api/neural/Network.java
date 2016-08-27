package m3api.neural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Network {
	public static Random rnd = new Random();
	
	InputLayer input = new InputLayer();
	ArrayList<Layer> layers = new ArrayList<>();
	ResultLayer result = new ResultLayer();
	int outgoing = 8;
	
	public void setOutgoing(int i) {
		outgoing = i;
	}
	
	public void addInput(String inputname) {
		input.addNeuron(inputname);
	}
	
	public void addResult(String resultname) {
		result.addNeuron(resultname);
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
		for(int i = 0; i < layers; i++) {
			this.layers.add(new Layer(neurons));
		}
	}
	
	public void reset() {
		input.reset();
		result.reset();
		for(Layer l : layers)
			l.reset();
	}
	
	public void populate(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		for(InputNeuron in : input.neurons) {	//Map from input layer to hidden layer 0
			for(int n = 0; n < outgoing; n++)
				in.tofire.add(layers.get(0).getRandom());
		}
		
		for (int i = 0; i < layers.size() - 1; i++) {	//Map hidden layers
			Layer l = layers.get(i);
			for(int n = 0; n < outgoing; n++)
				l.populate(layers.get(i + 1),weightMin, weightMax, thresholdMin, thresholdMax);
		}
		
		for(Neuron n : layers.get(layers.size() - 1).neurons) {	//Map last hidden layer to output
			for(int i = 0; i < outgoing; i++)
				n.tofire.add(result.getRandom());
		}
	}
}
