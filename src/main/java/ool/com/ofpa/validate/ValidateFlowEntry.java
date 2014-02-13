package ool.com.ofpa.validate;

import java.util.List;

import ool.com.ofpa.json.FlowModify;

public class ValidateFlowEntry {
	private List<FlowModify> params;
	public ValidateFlowEntry(List<FlowModify> params) {
		this.params = params;
	}
	public void checkValidate() throws ValidateException {
		for (FlowModify val : params) {
			if(ValidateUtil.checkEmpty(val.getOfcUrl())) {
				throw new ValidateException("ofcUrl is null/empty");
			}
			if(ValidateUtil.checkEmpty(val.getIp())) {
				throw new ValidateException("ip is null/empty");
			}
			if(ValidateUtil.checkEmpty(val.getType())) {
				throw new ValidateException("type is null/empty");
			}
			if(ValidateUtil.checkEmpty(val.getInPort())) {
				throw new ValidateException("inPort is null/empty");
			}
			if(ValidateUtil.checkEmpty(val.getOutPort())) {
				throw new ValidateException("outPort is null/empty");
			}
		}
	}
}
