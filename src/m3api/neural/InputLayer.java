package m3api.neural;

import java.util.ArrayList;

public class InputLayer extends Layer {

	ArrayList<InputNeuron>neurons = new ArrayList<>();
	
	@Override
	public void addNeuron(Neuron n) {
		if(!(n instanceof InputNeuron))
			throw new RuntimeException("InputLayer expects a InputNeuron! ");
		
		neurons.add((InputNeuron) n);
	}
	
	public void addNeuron(String s) {
		addNeuron(new InputNeuron(this, s));
	}
	
	public void fire() {
		for(InputNeuron n : neurons)
			n.fire();
	}
}
