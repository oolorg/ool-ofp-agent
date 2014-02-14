package ool.com.ofpa.service;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

@Component
public class OfpaServiceImpl implements OfpaService {

    @Inject
    AgentBusiness ab;

    Injector injector;
    
    Gson gson = new Gson();

	@Override
	public Response doPut(String body) {
		return null;
	}
	
}
