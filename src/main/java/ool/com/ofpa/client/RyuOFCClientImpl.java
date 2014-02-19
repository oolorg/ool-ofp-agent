/**
 * @author OOL 1131080355959
 * @date 2014/02/06
 * @TODO 
 */
package ool.com.ofpa.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ool.com.ofpa.json.MatchField;
import ool.com.ofpa.json.OfcCreateOut;
import ool.com.ofpa.json.OfcDeleteOut;
import ool.com.ofpa.utils.Definition;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author 1131080355959
 *
 */
public class RyuOFCClientImpl implements OFCClient {
	
    private static final Logger logger = Logger
            .getLogger(RyuOFCClientImpl.class);

	private static WebResource resource = null;
	private static ClientResponse response = null;
	private String url = null;
	
	public RyuOFCClientImpl(String url) {
		this.url = url;
	}

	@Override
	public void doPost(MatchField param) throws OFCException {
        logger.debug(String.format("doPost(param=%s) - start ", param));
		resource = Client.create().resource(url);
		Gson gson = new Gson();
		try {
			String body = gson.toJson(param, MatchField.class);
			response = resource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.entity(body).post(ClientResponse.class);
			String json = response.getEntity(String.class);
			OfcCreateOut res = gson.fromJson(json, OfcCreateOut.class);
			if (res.getStatus() != Definition.STATUS_CREATED) {
				logger.error("ofc post failed. message:" + res.getMessage());
				throw new OFCException("ofc post failed.", res.getStatus());
			}
		} catch (UniformInterfaceException ue) {
			logger.error(ue.getMessage());
			throw new OFCException(ue.getMessage(), ue.getResponse().getStatus());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new OFCException(ex.getMessage(), Definition.STATUS_INTERNAL_ERROR);			
		}
		logger.debug("doPost - end");
	}

	@Override
	public void doDelete(final MatchField param) throws OFCException {
        logger.debug(String.format("doDelete(param=%s) - start ", param));
		resource = Client.create().resource(url);
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("ip", param.getIp());
		queryParams.add("port", StringUtils.join(param.getPort().iterator(), ","));
		Gson gson = new Gson();
		try {
			response = resource.queryParams(queryParams).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
			String json = response.getEntity(String.class);
			OfcDeleteOut res = gson.fromJson(json, OfcDeleteOut.class);
			if (res.getStatus() != Definition.STATUS_SUCCESS) {
				logger.error("ofc delete failed. message:" + res.getMessage());
				throw new OFCException("ofc delete failed.", res.getStatus());
			}
		} catch (UniformInterfaceException ue) {
			logger.error(ue.getMessage());
			throw new OFCException(ue.getMessage(), ue.getResponse().getStatus());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new OFCException(ex.getMessage(), Definition.STATUS_INTERNAL_ERROR);			
		}
		logger.debug("doDelete - end");
	}
}
