package m3api.neural;

public class ResultNeuron extends Neuron {
	String desc;
	
	public ResultNeuron(ResultLayer l, String desc) {
		super(l);
		this.desc = desc;
	}
	
	@Override
	public void fire(double d) {
		triggered += d;
	}
	
	public double triggered() {
		return triggered;
	}
	
	public ResultNeuron getClone(Layer nextlayer, ResultLayer superlayer) {
		Neuron n = super.getClone(nextlayer);
		
		ResultNeuron ret = new ResultNeuron(superlayer, this.desc);
		ret.weights = n.weights;
		ret.threshold = n.threshold;
		
		return ret;
	}
}
