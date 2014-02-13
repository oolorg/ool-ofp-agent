package ool.com.ofpa.agent;

import java.util.List;
import java.util.Map;

import ool.com.ofpa.json.*;
import ool.com.ofpa.utils.Definition;
import ool.com.ofpa.validate.*;

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

//        // delete process
//        for (List<FlowModify> fe :param.getList()) {
//            MatchField ofcParam;
//            if ((fe.getType()).equals("delete")){
//                RyuOFCClient ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
//                ofcParam.setIP(fe.getIp());
//                ofcParam.setInPort(fe.getInPort());
//                ofcParam.setOutPort(fe.getOutPort());
//                try {
//                    ofcClient.doDelete(fe);
//                } catch (OFCException oe){
//                    ret.setStatus(Definition.STATUS_BAD_REQUEST);
//                    ret.setMessage(oe.getMessage());
//                }
//            }
//        }
//        // create process
//        for (List<FlowModify> fe :param.getList()) {
//            MatchField ofcParam;
//            if ((fe.getType()).equals("create")){
//                RyuOFCClient ofcClient = new RyuOFCClientImpl(fe.getOfcUrl());
//                ofcParam.setIP(fe.getIp());
//                ofcParam.setInPort(fe.getInPort());
//                ofcParam.setOutPort(fe.getOutPort());
//                try {
//                    ofcClient.doDelete(fe);
//                } catch (OFCException oe){
//                    ret.setStatus(Definition.STATUS_BAD_REQUEST);
//                    ret.setMessage(oe.getMessage());
//                }
//            }
//        }
        return ret;
    }

}