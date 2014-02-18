package ool.com.ofpa.client;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import ool.com.ofpa.client.OFCException;
import ool.com.ofpa.client.RyuOFCClientImpl;
import ool.com.ofpa.json.MatchField;
import ool.com.ofpa.utils.Definition;

import mockit.*;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class RyuOFCClientImplTest {
	
	private final static String url = "http://127.1.1.1/ofc/ryu/ctrl";
	
	@Mocked
	private WebResource mockWebResource;
	
	@Mocked
	private UniformInterfaceException mockUniformInterfaceException;
	
	@Mocked
	private ClientResponse mockClientRes;
	
	@Mocked
	private Client mockClient;
	
	@Mocked
	private WebResource.Builder mockBuilder;
	
    /**
     * @test.type abnormal
     * @test.precondition 
     * @test.aim 
     */
	@Test
	public void testDoPostClientError1() {
		RyuOFCClientImpl client = new RyuOFCClientImpl(url);
		new NonStrictExpectations() {
			{
				mockClient.create();
				result = mockClient;
				mockClient.resource(anyString);
				result = mockWebResource; 
				mockUniformInterfaceException.getMessage(); result = "UniformInterfaceException";
				mockUniformInterfaceException.getResponse(); result = mockClientRes;
				mockClientRes.getStatus(); result = 100;
				mockWebResource.accept(MediaType.APPLICATION_JSON); result = mockUniformInterfaceException;
			}
		};
		try {
			MatchField field = new MatchField();
			client.doPost(field);
		} catch (OFCException e) {
			if (e.getStatusCode() != 100) {
				fail();
			} else {
				assertTrue(true);
			}
			return;
		}
		fail();
	}
	
    /**
     * @test.type abnormal
     * @test.precondition 
     * @test.aim 
     */
	@Test
	public void testDoPostClientError2() {
		RyuOFCClientImpl client = new RyuOFCClientImpl(url);
		new NonStrictExpectations() {
			{
				mockClient.create();
				result = mockClient;
				mockClient.resource(anyString);
				result = mockWebResource; 
				mockClientRes.getStatus(); result = 100;
				mockWebResource.accept(MediaType.APPLICATION_JSON); result = new IOException();
			}
		};
		try {
			MatchField field = new MatchField();
			client.doPost(field);
		} catch (OFCException e) {
			if (e.getStatusCode() != Definition.STATUS_INTERNAL_ERROR) {
				fail();
			} else {
				assertTrue(true);
			}
			return;
		}
		fail();
	}
	
    /**
     * @test.type abnormal
     * @test.precondition 
     * @test.aim 
     */
	@Test
	public void testDoPostOfcError() {
		RyuOFCClientImpl client = new RyuOFCClientImpl(url);
		new Expectations() {
			{
				mockClient.create();
				result = mockClient;
				mockClient.resource(anyString);
				result = mockWebResource; 
				mockWebResource.accept(MediaType.APPLICATION_JSON);
				result = mockBuilder;
				mockBuilder.type(MediaType.APPLICATION_JSON);
				result = mockBuilder;
				mockBuilder.entity(anyString);
				result = mockBuilder;
				mockBuilder.post(ClientResponse.class);
				result = mockClientRes;
				mockClientRes.getEntity(String.class);
				result = "{'status':500, 'message':'test'}";
			}
		};
		try {
			MatchField field = new MatchField();
			field.setIp("192.168.1.1");
			List<Integer> ports = new ArrayList<Integer>();
			ports.add(1);
			ports.add(2);
			field.setPort(ports);
			client.doPost(field);
		} catch (OFCException e) {
			if (e.getStatusCode() != Definition.STATUS_INTERNAL_ERROR &&
					!"ofc post failed.".equals(e.getMessage())) {
				fail();
			} else {
				assertTrue(true);
			}
		}
	}

	
    /**
     * @test.type normal
     * @test.precondition 
     * @test.aim 
     */
	@Test
	public void testDoPost() {
		RyuOFCClientImpl client = new RyuOFCClientImpl(url);
		new Expectations() {
			{
				mockClient.create();
				result = mockClient;
				mockClient.resource(anyString);
				result = mockWebResource; 
				mockWebResource.accept(MediaType.APPLICATION_JSON);
				result = mockBuilder;
				mockBuilder.type(MediaType.APPLICATION_JSON);
				result = mockBuilder;
				mockBuilder.entity(anyString);
				result = mockBuilder;
				mockBuilder.post(ClientResponse.class);
				result = mockClientRes;
				mockClientRes.getEntity(String.class);
				result = "{'status':201}";
			}
		};
		try {
			MatchField field = new MatchField();
			field.setIp("192.168.1.1");
			List<Integer> ports = new ArrayList<Integer>();
			ports.add(1);
			ports.add(2);
			field.setPort(ports);
			client.doPost(field);
		} catch (OFCException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
