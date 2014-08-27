package com.carmanconsulting.sandbox.osgi.manifest.commons;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ManifestUtils {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(ManifestUtils.class);

//----------------------------------------------------------------------------------------------------------------------
// Static Methods
//----------------------------------------------------------------------------------------------------------------------

    public static String getManifestContents() {
        try {
            final File manifestFile = new File("target/classes/META-INF/MANIFEST.MF");
            LOGGER.debug("Looking for MANIFEST.MF file at {}...", manifestFile.getAbsolutePath());
            return FileUtils.readFileToString(manifestFile);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load MANIFEST.MF file contents.", e);
        }
    }
}
