package com.carmanconsulting.sandbox.osgi.manifest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductionClass {
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionClass.class);

    public String reverse(String input) {
        return StringUtils.reverse(input);
    }

    public void loadJdbcDriver(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Unable to load JDBC driver {}.", e);
        }
    }

    public void loadHsqldbDriver() {
        loadJdbcDriver("org.hsqldb.jdbcDriver");
    }
}
