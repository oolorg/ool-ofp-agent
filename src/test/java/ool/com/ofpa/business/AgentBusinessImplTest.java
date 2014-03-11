package ool.com.ofpa.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mockit.*;

import ool.com.ofpa.client.RyuOFCClientImpl;
import ool.com.ofpa.json.FlowEntryIn;
import ool.com.ofpa.json.FlowModify;
import ool.com.ofpa.json.OFPResponseOut;
import ool.com.ofpa.utils.Definition;

import org.junit.Test;

public class AgentBusinessImplTest {
	
	@Mocked
	RyuOFCClientImpl ryuClient;
	
	@Test
	public void testModifyValidateErrorNotRequest() {
		FlowEntryIn param = new FlowEntryIn();
		param.setList(new ArrayList<FlowModify>());
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testModifyValidateErrorModEmpty() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testModifyValidateError1() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		mod.setIp("ip");
		list.add(mod);
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testModifyValidateError2() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		mod.setIp("ip");
		mod.setOfcUrl("url");
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testModifyValidateError3() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		mod.setIp("ip");
		mod.setOfcUrl("url");
		List<Integer> port = new ArrayList<Integer>();
		mod.setPort(port);
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testModifyValidateError4() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		mod.setIp("ip");
		mod.setOfcUrl("url");
		List<Integer> port = new ArrayList<Integer>();
		port.add(1);
		mod.setPort(port);
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testModifyValidateError5() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		mod.setIp("ip");
		mod.setOfcUrl("url");
		List<Integer> port = new ArrayList<Integer>();
		port.add(1);
		port.add(2);
		mod.setPort(port);
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_BAD_REQUEST) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testModifyValidateSuccess() {
		FlowEntryIn param = new FlowEntryIn();
		List<FlowModify> list = new ArrayList<FlowModify>();
		FlowModify mod = new FlowModify();
		list.add(mod);
		mod.setIp("ip");
		mod.setOfcUrl("url");
		List<Integer> port = new ArrayList<Integer>();
		port.add(1);
		port.add(2);
		mod.setPort(port);
		mod.setType("type");
		param.setList(list);
		AgentBusiness ab = new AgentBusinessImpl();
		OFPResponseOut res = ab.Modify(param);
		if (res.getStatus() == Definition.STATUS_SUCCESS) {
			assertTrue(true);
		} else {
			fail();
		}
	}

}
