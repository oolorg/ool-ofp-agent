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
        ret.setStatus(Definition.STATUS_CREATED);
        ret.setMessage("");
        ValidateFlowEntry checkValidate = new ValidateFlowEntry(param.getList());
        try {
            checkValidate.checkValidate();
        } catch (ValidateException ve) {
            ret.setStatus(Definition.STATUS_INTERNAL_ERROR);
            ret.setMessage(ve.getMessage());
            return ret;
        }

        // delete process
        for (FlowModify fe :param.getList()) {
            if ((fe.getType()).equals("delete")){
                MatchField ofcParam = new MatchField();
            	RyuOFCClientImpl ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
                ofcParam.setIp(fe.getIp());
                ofcParam.setInPort(fe.getInPort());
                ofcParam.setOutPort(fe.getOutPort());
                try {
                    ofcClient.doDelete(fe);
                } catch (OFCException oe){
                    ret.setStatus(oe.getStatusCode());
                    ret.setMessage(oe.getMessage());
                    return ret;
                }
            }
        }
        // create process
        for (FlowModify fe :param.getList()) {
            if ((fe.getType()).equals("create")){
                MatchField ofcParam = new MatchField();
            	RyuOFCClientImpl ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
                ofcParam.setIp(fe.getIp());
                ofcParam.setInPort(fe.getInPort());
                ofcParam.setOutPort(fe.getOutPort());
                try {
                    ofcClient.doPost(fe);
                } catch (OFCException oe){
                    ret.setStatus(oe.getStatusCode());
                    ret.setMessage(oe.getMessage());
                    return ret;
                }
            }
        }
        return ret;
    }

}