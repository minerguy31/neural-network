package m3api.neural.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import m3api.neural.Network;

public class Learner {
	BiFunction<HashMap<String, Double>, HashMap<String, Double>, Double> fitnessfunc;
	ArrayList<Network> genomes = new ArrayList<>();
	int genomecnt = 0;
	double mutation_weightMin; 
	double mutation_weightMax; 
	double mutation_thresholdMin;
	double mutation_thresholdMax;
	double init_weightMin; 
	double init_weightMax; 
	double init_thresholdMin;
	double init_thresholdMax;
	int layers, neurons;
	HashMap<String, Double> input = new HashMap<>();
	ArrayList<String> result = new ArrayList<>();
	
	public Learner(BiFunction<HashMap<String, Double>, HashMap<String, Double>, Double> biFunction) {
		fitnessfunc = biFunction;
	}

	public Learner setGenomesPerGeneration(int i) {
		genomecnt = i;
		return this;
	}
	
	public Learner setInputs(HashMap<String, Double> h) {
		input = h;
		return this;
	}
	
	public Learner setResultNeurons(ArrayList<String> h) {
		result = h;
		return this;
	}
	
	public Learner setHiddenLayers(int layers, int neurons) {
		this.layers = layers;
		this.neurons = neurons;
		return this;
	}

	public Learner setMutation(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		this.mutation_weightMin = weightMin;
		this.mutation_weightMax = weightMax; 
		this.mutation_thresholdMin = thresholdMin;
		this.mutation_thresholdMax = thresholdMax;
		return this;
	}

	public Learner setInitValues(double weightMin, double weightMax, double thresholdMin, double thresholdMax) {
		this.init_weightMin = weightMin;
		this.init_weightMax = weightMax; 
		this.init_thresholdMin = thresholdMin;
		this.init_thresholdMax = thresholdMax;
		return this;
	}
	
	public void start() {
		for(int i = 0; i < genomecnt; i++) {
			Network n = new Network();
			n.addInputs(input.keySet());
			n.addLayers(neurons, layers);
			for(String resultname : result)
				n.addResult(resultname);
			n.populate(init_weightMin, init_weightMax, init_thresholdMin, init_thresholdMax);
			genomes.add(n);
		}
	}
	
	public void run() {
		HashMap<Network,Double> nets = new HashMap<>();
		double scoretotal = 0d;
		for(Network n : genomes) {
			HashMap<String, Double> result = n.run();
			n.reset();
			double score = fitnessfunc.apply(input, result);
			scoretotal += score;
			nets.put(n, score);
		}
		
		// Generate offspring
		ArrayList<Network> offspring = new ArrayList<>();
		
		for(Entry<Network, Double> e : nets.entrySet()) {
			int ct = (int) (e.getValue() / scoretotal * genomecnt);
			Network n = e.getKey();
			
			for(int i = 0; i < ct; i++) {
				//TODO: Find a way to deep copy the network so mutate can be called multiple times on it
				
				n.mutate(mutation_weightMin, mutation_weightMax, mutation_thresholdMin, mutation_thresholdMax);
				offspring.add(n);
			}
		}
	}
}
