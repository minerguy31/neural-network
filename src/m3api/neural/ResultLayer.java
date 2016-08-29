package m3api.neural;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultLayer extends Layer {
	
	@Override
	public void addNeuron(Neuron n) {
		if(!(n instanceof ResultNeuron))
			throw new RuntimeException("ResultLayer expects a ResultNeuron! ");
		
		neurons.add((ResultNeuron) n);
	}
	
	public HashMap<String, Double> getResults() {
		HashMap<String, Double> ret = new HashMap<>();
		
		for(Neuron n : neurons) {
			ResultNeuron rn = (ResultNeuron) n;
			ret.put(rn.desc, rn.triggered());
			rn.reset();
		}
		
		return ret;
	}

	public void addNeuron(String resultname) {
		addNeuron(new ResultNeuron(this, resultname));
	}
	
	@Override
	public Neuron getRandom() {
		int r = rnd.nextInt(neurons.size());
		return neurons.get(r);
	}
}
