package ool.com.ofpa.json;

import java.util.List;

public class MatchField {
	private String ip;
	private List<Integer> port;
	public String getIp() {
		return ip;
	}
	public void setIp(final String ip) {
		this.ip = ip;
	}
	public List<Integer> getPort() {
		return port;
	}
	public void setPort(final List<Integer> port) {
		this.port = port;
	}
}
