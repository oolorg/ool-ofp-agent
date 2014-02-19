package ool.com.ofpa.service;

import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ool.com.ofpa.business.AgentBusiness;
import ool.com.ofpa.business.AgentBusinessImpl;
import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.OFPResponseOut;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

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

    private static final Logger logger = Logger
            .getLogger(OfpaServiceImpl.class);

    Gson gson = new Gson();

	@Override
	public Response doPut(String params) {
        logger.debug(String.format("doPut(param=%s) - start ", params));
        this.injector = Guice.createInjector(new AbstractModule() {
            @Override protected void configure() {
            	bind(AgentBusiness.class).to(AgentBusinessImpl.class);
            }
        });
        Type type = new TypeToken<FlowEntryIn>(){}.getType();
        FlowEntryIn inPara = gson.fromJson(params, type);
        
        OfpaServiceImpl main = injector.getInstance(OfpaServiceImpl.class);

        OFPResponseOut res = main.ab.Modify(inPara);

        type = new TypeToken<OFPResponseOut>(){}.getType();
        String outPara = gson.toJson(res, type);
        logger.debug(String.format("doPut - end"));
        return Response.ok(outPara).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
}
