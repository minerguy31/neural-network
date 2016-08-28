package m3api.neural;

public class InputNeuron extends Neuron {
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
