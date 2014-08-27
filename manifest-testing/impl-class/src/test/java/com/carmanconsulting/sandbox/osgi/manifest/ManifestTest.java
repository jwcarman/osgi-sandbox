package com.carmanconsulting.sandbox.osgi.manifest;

import com.carmanconsulting.sandbox.osgi.manifest.commons.ManifestUtils;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class ManifestTest {

    @Test
    public void dumpManifest() {
        LoggerFactory.getLogger(getClass()).info("\n\n****************************** MANIFEST.MF ******************************\n\n{}", ManifestUtils.getManifestContents());
    }
}
