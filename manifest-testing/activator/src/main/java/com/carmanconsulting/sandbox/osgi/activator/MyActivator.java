package com.carmanconsulting.sandbox.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyActivator implements BundleActivator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.info("Bundle starting {}...", bundleContext);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("Bundle stopping {}...", bundleContext);
    }
}
