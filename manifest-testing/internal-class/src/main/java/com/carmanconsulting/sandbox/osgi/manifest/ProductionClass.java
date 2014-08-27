package com.carmanconsulting.sandbox.osgi.manifest;

import com.carmanconsulting.sandbox.osgi.manifest.commons.ManifestUtils;
import org.apache.commons.lang3.StringUtils;

public class ProductionClass {

    public String reverse(String input) {
        return StringUtils.reverse(input);
    }

    public String getManifestContents() {
        return ManifestUtils.getManifestContents();
    }
}
