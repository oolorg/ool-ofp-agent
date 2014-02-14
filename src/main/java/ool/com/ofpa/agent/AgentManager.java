package ool.com.ofpa.agent;

import ool.com.ofpa.client.OFCException;
import ool.com.ofpa.client.RyuOFCClientImpl;
import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.FlowModify;
import ool.com.ofpa.json.MatchField;
import ool.com.ofpa.json.OFPResponseOut;
import ool.com.ofpa.utils.Definition;
import ool.com.ofpa.validate.ValidateException;
import ool.com.ofpa.validate.ValidateFlowEntry;

public class AgentManager {

    public OFPResponseOut Modify(FlowEntryIn param) throws OFPException {
        OFPResponseOut ret = new OFPResponseOut();
        ValidateFlowEntry checkValidate = new ValidateFlowEntry(param.getList());
        try {
            checkValidate.checkValidate();
        } catch (ValidateException ve) {
            ret.setStatus(Definition.STATUS_BAD_REQUEST);
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
                    ret.setStatus(Definition.STATUS_BAD_REQUEST);
                    ret.setMessage(oe.getMessage());
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
                    ofcClient.doDelete(fe);
                } catch (OFCException oe){
                    ret.setStatus(Definition.STATUS_BAD_REQUEST);
                    ret.setMessage(oe.getMessage());
                }
            }
        }
        return ret;
    }

}