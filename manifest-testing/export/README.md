### Customizations
osgi.export={local-packages},org.apache.commons.lang3

### Manifest File Contents
<pre>
Manifest-Version: 1.0
Bnd-LastModified: 1410491324975
Build-Jdk: 1.7.0_67
Build-Time: 20140911-2308
Built-By: jcarman
Bundle-DocURL: http://www.carmanconsulting.com
Bundle-ManifestVersion: 2
Bundle-Name: OSGi Sandbox :: Manifest Testing :: Export Package
Bundle-SymbolicName: com.carmanconsulting.sandbox.osgi.export-package
Bundle-Vendor: Carman Consulting, Inc.
Bundle-Version: 1.0.0.SNAPSHOT
Created-By: Apache Maven Bundle Plugin
Embed-Directory: lib
Export-Package: 
 com.carmanconsulting.sandbox.osgi.manifest;version="1.0.0",
 org.apache.commons.lang3;version="3.1";uses:="org.apache.commons.lang3.
 text.translate"
Implementation-Title: OSGi Sandbox :: Manifest Testing :: Export Package
Implementation-Vendor: Carman Consulting, Inc.
Implementation-Vendor-Id: com.carmanconsulting.sandbox.osgi
Implementation-Version: 1.0-SNAPSHOT
Import-Package: 
 org.apache.commons.lang3.builder;version="[3.1,4)",
 org.apache.commons.lang3.exception;version="[3.1,4)",
 org.apache.commons.lang3.math;version="[3.1,4)",
 org.apache.commons.lang3.mutable;version="[3.1,4)",
 org.apache.commons.lang3.text.translate;version="[3.1,4)"
Require-Capability: osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.7))"
Specification-Title: OSGi Sandbox :: Manifest Testing :: Export Package
Specification-Vendor: Carman Consulting, Inc.
Specification-Version: 1.0-SNAPSHOT
Tool: Bnd-2.3.0.201405100607
</pre>

### Jar File Contents
<pre>
META-INF/MANIFEST.MF
META-INF/
META-INF/maven/
META-INF/maven/com.carmanconsulting.sandbox.osgi/
META-INF/maven/com.carmanconsulting.sandbox.osgi/export-package/
META-INF/maven/com.carmanconsulting.sandbox.osgi/export-package/pom.properties
META-INF/maven/com.carmanconsulting.sandbox.osgi/export-package/pom.xml
com/
com/carmanconsulting/
com/carmanconsulting/sandbox/
com/carmanconsulting/sandbox/osgi/
com/carmanconsulting/sandbox/osgi/manifest/
com/carmanconsulting/sandbox/osgi/manifest/ProductionClass.class
org/
org/apache/
org/apache/commons/
org/apache/commons/lang3/
org/apache/commons/lang3/AnnotationUtils$1.class
org/apache/commons/lang3/AnnotationUtils.class
org/apache/commons/lang3/ArrayUtils.class
org/apache/commons/lang3/BitField.class
org/apache/commons/lang3/BooleanUtils.class
org/apache/commons/lang3/CharEncoding.class
org/apache/commons/lang3/CharRange$1.class
org/apache/commons/lang3/CharRange$CharacterIterator.class
org/apache/commons/lang3/CharRange.class
org/apache/commons/lang3/CharSequenceUtils.class
org/apache/commons/lang3/CharSet.class
org/apache/commons/lang3/CharSetUtils.class
org/apache/commons/lang3/CharUtils.class
org/apache/commons/lang3/ClassUtils.class
org/apache/commons/lang3/EnumUtils.class
org/apache/commons/lang3/JavaVersion.class
org/apache/commons/lang3/LocaleUtils$SyncAvoid.class
org/apache/commons/lang3/LocaleUtils.class
org/apache/commons/lang3/ObjectUtils$Null.class
org/apache/commons/lang3/ObjectUtils.class
org/apache/commons/lang3/RandomStringUtils.class
org/apache/commons/lang3/Range$ComparableComparator.class
org/apache/commons/lang3/Range.class
org/apache/commons/lang3/SerializationException.class
org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream.class
org/apache/commons/lang3/SerializationUtils.class
org/apache/commons/lang3/StringEscapeUtils$CsvEscaper.class
org/apache/commons/lang3/StringEscapeUtils$CsvUnescaper.class
org/apache/commons/lang3/StringEscapeUtils.class
org/apache/commons/lang3/StringUtils$InitStripAccents.class
org/apache/commons/lang3/StringUtils.class
org/apache/commons/lang3/SystemUtils.class
org/apache/commons/lang3/Validate.class
</pre>