package ool.com.ofpa.validate;

import java.util.List;

import ool.com.ofpa.json.FlowModify;
import ool.com.ofpa.utils.Definition;

public class ValidateFlowEntry {
	private List<FlowModify> params;
	public ValidateFlowEntry(List<FlowModify> params) {
		this.params = params;
	}
	public void checkValidate() throws ValidateException {
		if (params.size() == 0) {
			throw new ValidateException("request not found.");
		}
		for (FlowModify val : params) {
			if (ValidateUtil.checkEmpty(val.getOfcUrl())) {
				throw new ValidateException("ofcUrl is null/empty");
			}
			if (ValidateUtil.checkEmpty(val.getIp())) {
				throw new ValidateException("ip is null/empty");
			}
			if (ValidateUtil.checkEmpty(val.getType())) {
				throw new ValidateException("type is null/empty");
			}
			if (null == val.getPort()) {
				throw new ValidateException("port is null");
			}
			if (Definition.PARAM_PORT_LENGTH > val.getPort().size()) {
				throw new ValidateException("port is invalid value.");
			}
			if (Definition.PARAM_PORT_LENGTH < val.getPort().size()) {
				throw new ValidateException("port is out of range.");
			}
		}
	}
}
