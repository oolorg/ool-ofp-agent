package ool.com.ofpa.utils;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class OolUtilsTest {

	@Test
	public void testParseQueryMap() {
		String query = "key1=val1&key2=val2";
		Map<String, String> ret = OolUtils.parseQueryMap(query);
		assertEquals("val1", ret.get("key1"));
		assertEquals("val2", ret.get("key2"));
	}
	
	@Test
	public void  testParseMapQuery() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("key1", "val1");
		params.put("key2", "val2");
		String query = OolUtils.parseMapQuery(params);
		assertEquals("key1=val1&key2=val2", query);
	}
}
