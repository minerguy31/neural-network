package m3api.neural;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultLayer extends Layer {
	

	ArrayList<ResultNeuron>neurons = new ArrayList<>();
	
	@Override
	public void addNeuron(Neuron n) {
		if(!(n instanceof ResultNeuron))
			throw new RuntimeException("ResultLayer expects a ResultNeuron! ");
		
		neurons.add((ResultNeuron) n);
	}
	
	public HashMap<String, Boolean> getResults() {
		HashMap<String, Boolean> ret = new HashMap<>();
		
		for(ResultNeuron rn : neurons) {
			ret.put(rn.desc, rn.isFired());
			rn.reset();
		}
		
		return ret;
	}

	public void addNeuron(String resultname) {
		addNeuron(new ResultNeuron(this, resultname));
	}
	
	public void reset() {
		for(ResultNeuron n : neurons) {
			n.reset();
		}
	}
	
	@Override
	public Neuron getRandom() {
		int r = rnd.nextInt(neurons.size());
		return neurons.get(r);
	}
}
