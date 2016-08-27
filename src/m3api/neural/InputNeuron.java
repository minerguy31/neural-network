package m3api.neural;

import java.util.ArrayList;

public class InputNeuron extends Neuron {
	public final ArrayList<Neuron> tofire = new ArrayList<>();
	InputLayer parentlayer;
	String desc;
	
	public InputNeuron(InputLayer l, String desc) {
		parentlayer = l;
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
			for(Neuron n : tofire)
				n.fire(triggered);
	}
}
