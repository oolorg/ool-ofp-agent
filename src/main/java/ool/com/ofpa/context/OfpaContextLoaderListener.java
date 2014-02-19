package ool.com.ofpa.context;

import javax.servlet.ServletContextEvent;

import ool.com.ofpa.client.RyuOFCClientImpl;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

public class OfpaContextLoaderListener extends ContextLoaderListener {

    private static final Logger logger = Logger
            .getLogger(RyuOFCClientImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		logger.info("ool-ofp-agent context initialized.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("ool-ofp-agent context destroyed.");
		super.contextDestroyed(event);
	}
}
