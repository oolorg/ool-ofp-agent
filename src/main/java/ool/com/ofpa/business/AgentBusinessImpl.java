package ool.com.ofpa.business;

import ool.com.ofpa.client.OFCException;
import ool.com.ofpa.client.RyuOFCClientImpl;
import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.FlowModify;
import ool.com.ofpa.json.MatchField;
import ool.com.ofpa.json.OFPResponseOut;
import ool.com.ofpa.utils.Definition;
import ool.com.ofpa.validate.ValidateException;
import ool.com.ofpa.validate.ValidateFlowEntry;

public class AgentBusinessImpl implements AgentBusiness {

	@Override
	public OFPResponseOut Modify(FlowEntryIn param) {
        OFPResponseOut ret = new OFPResponseOut();
        ret.setStatus(Definition.STATUS_SUCCESS);
        ret.setMessage("");
        ValidateFlowEntry checkValidate = new ValidateFlowEntry(param.getList());
        try {
            checkValidate.checkValidate();
			reqMod(param);
        } catch (ValidateException ve) {
            ret.setStatus(Definition.STATUS_BAD_REQUEST);
            ret.setMessage(ve.getMessage());
            return ret;
		} catch (OFCException oe) {
            ret.setStatus(oe.getStatusCode());
            ret.setMessage(oe.getMessage());
		}
        return ret;
    }

	private void reqMod(FlowEntryIn param) throws OFCException {
        // delete process
        for (FlowModify fe :param.getList()) {
            if ((fe.getType()).equals("delete")) {
                MatchField ofcParam = new MatchField();
            	RyuOFCClientImpl ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
                ofcParam.setIp(fe.getIp());
                ofcParam.setPort(fe.getPort());
                ofcClient.doDelete(fe);
            }
        }
        // create process
        for (FlowModify fe :param.getList()) {
            if ((fe.getType()).equals("create")) {
                MatchField ofcParam = new MatchField();
            	RyuOFCClientImpl ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
                ofcParam.setIp(fe.getIp());
                ofcParam.setPort(fe.getPort());
                ofcClient.doPost(fe);
            }
        }
	}
}