package m3api.neural;

public class ResultNeuron extends Neuron {
	ResultLayer parentlayer;
	String desc;
	
	public ResultNeuron(ResultLayer l, String desc) {
		parentlayer = l;
		this.desc = desc;
	}
	
	@Override
	public void fire(double d) {
		triggered += d;
	}
	
	public double triggered() {
		return triggered;
	}
}
