package ool.com.ofpa.utils;

import java.util.HashMap;
import java.util.Map;

public class OolUtils {
	/**
	 * 
	 * @param "key1=val1&key2=val2"
	 * @return {"key1":"val1", "key2":"val2"}
	 */
    public static Map<String, String> parseQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> ret = new HashMap<String, String>();
        for (String param : params) {
            String[] val = param.split("=");
            ret.put(val[0], val[1]);
        }
        return ret;
    }

    /**
     * 
     * @param {"key1":"val1", "key2":"val2"}
     * @return "key1=val1&key2=val2"
     */
    public static String parseMapQuery(Map<String, String> params) {
        StringBuilder build = new StringBuilder();
        for(Map.Entry<String, String> e : params.entrySet()) {
            if (build.length() > 0) {
                build.append("&");
            }
            build.append(e.getKey());
            build.append("=");
            build.append(e.getValue());
        }
        return build.toString();
    }
}
