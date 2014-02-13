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
public interface OFCClient {
	public void doPost(String body) throws OFCException;
	public void doDelete(String body) throws OFCException;
}
