package m3api.neural;

public class InputNeuron extends Neuron {
	String desc;
	
	public InputNeuron(Layer l, String desc) {
		super(l);
		this.desc = desc;
	}
	
	public double triggered() {
		return triggered;
	}
	
	public void setTriggered(double fire) {
		this.triggered = fire;
	}

	public void fire() {
		if(triggered != 0.0)
			for (int i = 0; i < nextlayer.neurons.size(); i++) {
				Neuron n = nextlayer.neurons.get(i);
				n.fire(weights.get(i));
			}
		System.out.println(this.weights);
	}
	
	public InputNeuron getClone(Layer nextlayer) {
		InputNeuron ret = new InputNeuron(nextlayer, desc);
		
		Neuron n = super.getClone(nextlayer);
		
		ret.weights = n.weights;
		
		return ret;
	}
}
