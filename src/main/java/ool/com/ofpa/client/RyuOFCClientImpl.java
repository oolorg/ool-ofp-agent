/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO 
 */
package ool.com.ofpa.client;
import javax.ws.rs.core.MediaType;

import ool.com.ofpa.utils.Definition;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

/**
 * @author 1131080355959
 *
 */
public class RyuOFCClientImpl implements OFCClient {
	
	private static WebResource resource = null;
	private static ClientResponse response = null;
	private String url = null;

	public RyuOFCClientImpl(String url) {
		this.url = url;
	}

	@Override
	public void doPost(String body) throws OFCException {
		resource = Client.create().resource(url);
		try {
			response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.entity(body).post(ClientResponse.class);
			if (response.getStatus() != Definition.STATUS_CREATED) {
				throw new OFCException("ofc post failed.", response.getStatus());
			}
		} catch (UniformInterfaceException ue) {
			throw new OFCException(ue.getMessage(), Definition.STATUS_INTERNAL_ERROR);
		}
	}

	@Override
	public void doDelete(String body) throws OFCException {
		resource = Client.create().resource(url);
		try {
			response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.entity(body).delete(ClientResponse.class);
			if (response.getStatus() != Definition.STATUS_SUCCESS) {
				throw new OFCException("ofc delete failed.", response.getStatus());
			}
		} catch (UniformInterfaceException ue) {
			throw new OFCException(ue.getMessage(), Definition.STATUS_INTERNAL_ERROR);
		}
	}

}
