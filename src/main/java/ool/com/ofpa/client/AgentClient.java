package ool.com.ofpa.client;

import ool.com.ofpa.json.BaseResultIn;

public interface AgentClient {
	public BaseResultIn getTopology() throws Exception;
	public BaseResultIn addFlows() throws Exception;
	public BaseResultIn delFlows() throws Exception;
}
