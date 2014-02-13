/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO 
 */
package ool.com.ofpa.client;

import ool.com.ofpa.json.MatchField;

/**
 * @author 1131080355959
 *
 */
public interface OFCClient {
	public void doPost(MatchField param) throws OFCException;
	public void doDelete(MatchField param) throws OFCException;
}
