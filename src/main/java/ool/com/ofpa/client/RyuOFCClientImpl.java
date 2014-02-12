/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO 
 */
package ool.com.ofpa.client;

/**
 * @author 1131080355959
 *
 */
public class RyuOFCClientImpl implements OFCClient {
	
	private static RyuOFCClientImpl instance = null;
	
	private RyuOFCClientImpl() {
	}
	
	public static RyuOFCClientImpl getInstance() {
		if (instance == null) {
			instance = new RyuOFCClientImpl();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see ool.com.ofpm.client.DBClient#exec()
	 */
	@Override
	public void exec() {
		// TODO Auto-generated method stub	
	}

}
