/**
 * @author OOL 1131080355959
 * @date 2014/02/05
 * @TODO TODO
 */
package ool.com.ofpa.business;

import javax.servlet.http.HttpServletRequest;

import ool.com.ofpa.client.OFCClient;
import ool.com.ofpa.client.RyuOFCClientImpl;
import ool.com.ofpa.json.HelloWorldJsonPostIn;
import ool.com.ofpa.json.HelloWorldJsonPostOut;
import ool.com.ofpa.validate.HelloWorldValidate;
import ool.com.ofpa.validate.ValidateException;

/**
 * @author 1131080355959
 *
 */
public class HelloWorldBusinessImpl implements HelloWorldBusiness {

	/* (non-Javadoc)
	 * @see ool.com.ofpm.business.HelloWorldBusiness#getHello()
	 */
	@Override
	public String sayHello(String message, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return message + ":" + req.getLocalAddr();
	}

	/* (non-Javadoc)
	 * @see ool.com.ofpm.business.HelloWorldBusiness#createHello(ool.com.ofpm.json.HelloWorldJsonPostIn)
	 */
	@Override
	public HelloWorldJsonPostOut createHello(HelloWorldJsonPostIn params) {
    	HelloWorldValidate validator = new HelloWorldValidate();
    	HelloWorldJsonPostOut ret = new HelloWorldJsonPostOut();
    	try {
			validator.postValidation();
			OFCClient dbClient = RyuOFCClientImpl.getInstance();
			dbClient.exec();
			ret.setEnabled("OK");
		} catch (ValidateException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return ret;
	}

}
