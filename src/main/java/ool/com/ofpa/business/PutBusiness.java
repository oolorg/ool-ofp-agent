package ool.com.ofpa.business;

import ool.com.ofpa.json.ResultOut;

public class PutBusiness {
	private String uri;
	public PutBusiness() {
	}

	@SuppressWarnings("finally")
	public ResultOut put(){
		ResultOut result = null;
		try {
			//AgentClientImpl agent_client = AgentClientImpl.getInstance(this.uri);
			//BaseResultIn ofpa_result = agent_client.getTopology();
			//result = convert(ofpa_result);
			result = new ResultOut();
			result.setMessage("put test OK!");
			result.setStatus("200");
		} catch(Exception e) {
			result = new ResultOut();
			result.setMessage(e.getMessage());
			result.setStatus("500");
		} finally {
			return result;
		}
	}
}
