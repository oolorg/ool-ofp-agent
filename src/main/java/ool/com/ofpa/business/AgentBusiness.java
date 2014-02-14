package ool.com.ofpa.business;

import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.OFPResponseOut;

public interface AgentBusiness {
	public OFPResponseOut Modify(FlowEntryIn param);
}