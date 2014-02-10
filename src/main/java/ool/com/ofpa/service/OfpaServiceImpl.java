package ool.com.ofpa.service;


import java.lang.reflect.Type;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ool.com.ofpa.business.PutBusiness;
import ool.com.ofpa.json.ResultOut;
import ool.com.ofpa.service.utils.ResponseGenerator;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Path("/")
@Component
public class OfpaServiceImpl implements OfpaService {

	private Gson gson = new Gson();

	@PUT
	@Path("/")
	public Response doPut(@RequestBody String body) {
		PutBusiness put = new PutBusiness();
		ResultOut result_out = put.put();
		Type collectionType = new TypeToken<ResultOut>(){}.getType();
		String res_str =  this.gson.toJson(result_out, collectionType);
		return ResponseGenerator.generate(res_str, Status.OK);
	}
}

