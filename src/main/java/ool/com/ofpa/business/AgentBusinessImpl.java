package ool.com.ofpa.business;

import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger
            .getLogger(AgentBusinessImpl.class);

	@Override
	public OFPResponseOut Modify(FlowEntryIn param) {
        logger.debug(String.format("Modify(param=%s) - start ", param));
        OFPResponseOut ret = new OFPResponseOut();
        ret.setStatus(Definition.STATUS_SUCCESS);
        ret.setMessage("");
        ValidateFlowEntry checkValidate = new ValidateFlowEntry(param.getList());
        try {
            checkValidate.checkValidate();
			reqMod(param);
        } catch (ValidateException ve) {
			logger.error(ve.getMessage());
            ret.setStatus(Definition.STATUS_BAD_REQUEST);
            ret.setMessage(ve.getMessage());
            return ret;
		} catch (OFCException oe) {
			logger.error(oe.getMessage());
            ret.setStatus(oe.getStatusCode());
            ret.setMessage(oe.getMessage());
		}
        logger.debug(String.format("Modify(ret=%s) - end ", ret));
        return ret;
    }

	private void reqMod(FlowEntryIn param) throws OFCException {
        logger.debug(String.format("reqMod(param=%s) - start ", param));
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
        logger.debug(String.format("reqMod - end"));
	}
}