package m3api.neural;

import java.util.ArrayList;

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
}
