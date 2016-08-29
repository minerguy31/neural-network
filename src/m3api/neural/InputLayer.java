package m3api.neural;

public class InputLayer extends Layer {
	
	@Override
	public void addNeuron(Neuron n) {
		if(!(n instanceof InputNeuron))
			throw new RuntimeException("InputLayer expects a InputNeuron! ");
		
		neurons.add((InputNeuron) n);
	}
	
	public void addNeuron(String s) {
		InputNeuron n = new InputNeuron(childlayer, s);
		addNeuron(n);
	}
	
	public void fire() {
		for(Neuron n : neurons)
			((InputNeuron) n).fire();
	}
}
