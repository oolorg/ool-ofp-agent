package ool.com.ofpa.service;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ool.com.ofpa.json.FlowEntryIn;

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
	public Response doPut(String params) {
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override protected void configure() {
            	bind(AgentBusiness.class).to(AgentBusinessImpl.class);
            }
        });
        Type type = new TypeToken<FlowEntryIn>(){}.getType();
        FlowEntryIn inPara = gson.fromJson(params, type);
        
        OfpaServiceImpl main = injector.getInstance(OfpaServiceImpl.class);
    	OFPResponseOut res = main.ab.createHello(inPara);
    	
        type = new TypeToken<OFPResponseOut>(){}.getType();
        String outPara = gson.toJson(res, type);
        return Response.ok(outPara).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
}
