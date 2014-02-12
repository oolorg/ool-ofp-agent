package ool.com.ofpa.service;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

// TODO 戻り値をResponse型にする：　HTTPヘッダを編集できないため
@Path("/")
public interface OfpaService {
	@PUT
	@Path("/")
	public Response doPut(@RequestBody String body);
}
