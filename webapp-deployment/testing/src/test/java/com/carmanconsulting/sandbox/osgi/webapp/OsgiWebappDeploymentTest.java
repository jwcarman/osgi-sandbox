package com.carmanconsulting.sandbox.osgi.webapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFileExtendOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

@RunWith(PaxExam.class)
public class OsgiWebappDeploymentTest extends Assert {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Long COMMAND_TIMEOUT = 600000L;

    private static final String FEATURES_CONFIG = "etc/org.apache.karaf.features.cfg";
    private static final String PAX_URL_MVN_CONFIG = "etc/org.ops4j.pax.url.mvn.cfg";
    private static final String KARAF_MANAGEMENT_CONFIG = "etc/org.apache.karaf.management.cfg";
    private static final String KARAF_SHELL_CONFIG = "etc/org.apache.karaf.shell.cfg";
    private static final String PAX_EXAM_RBC_CONFIG = "etc/org.ops4j.pax.exam.rbc.cfg";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Logger logger = LoggerFactory.getLogger(getClass());

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

//    protected void assertBundleActive(String symbolicName) {
//        String listOutput = executeCommand(String.format("osgi:list -t 10 | grep '%s' | grep 'Active'", symbolicName));
//        assertFalse("Feature " + symbolicName + " is not installed!", listOutput == null || listOutput.isEmpty());
//    }

    @Configuration
    public Option[] config() {
        return options(
                junitBundles(),


                karafDistributionConfiguration()
                        .frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf").version("3.0.0").type("tar.gz"))
                        .useDeployFolder(false)
                        .unpackDirectory(new File("target/exam")),

                new KarafDistributionConfigurationFileExtendOption(FEATURES_CONFIG, "featuresBoot", "war"),

                mavenBundle().groupId("com.carmanconsulting.sandbox.osgi").artifactId("webapp").type("war"),

                keepRuntimeFolder(),

                logLevel(LogLevelOption.LogLevel.INFO));
    }

    /**
     * Executes a shell command and returns output as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command the command
     * @return the command output
     */
//    protected String executeCommand(final String command) {
//        return executeCommand(command, COMMAND_TIMEOUT);
//    }

//    /**
//     * Executes a shell command and returns output as a String.
//     * Commands have a default timeout of 10 seconds.
//     *
//     * @param command The command to execute.
//     * @param timeout The amount of time in millis to wait for the command to execute.
//     * @return command output
//     */
//    protected String executeCommand(final String command, final Long timeout) {
//        String response;
//        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
//        final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
//        FutureTask<String> commandFuture = new FutureTask<>(new Callable<String>() {
//            public String call() {
//                try {
//                    logger.info("Executing command {}...", command);
//                    commandSession.execute(command);
//                } catch (Exception e) {
//                    logger.error("Command threw exception!", e);
//                    throw new RuntimeException(e);
//                }
//                printStream.flush();
//                final String output = byteArrayOutputStream.toString();
//                logger.info("Command {} output\n{}", command, output);
//                return output;
//            }
//        });
//
//        try {
//            executor.submit(commandFuture);
//            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            logger.error("An exception occurred while executing command {}!", command, e);
//            response = "SHELL COMMAND TIMED OUT: ";
//        }
//
//        return response;
//    }

//    private KarafDistributionConfigurationFileExtendOption addFeatureRepository(String groupId, String artifactId, String version) {
//        return new KarafDistributionConfigurationFileExtendOption(FEATURES_CONFIG, "featureRepositories", String.format("mvn:%s/%s/%s/xml/features", groupId, artifactId, version));
//    }
//
//    private KarafDistributionConfigurationFileExtendOption addBootFeature(String featureName) {
//        return new KarafDistributionConfigurationFileExtendOption(FEATURES_CONFIG, "featuresBoot", featureName);
//    }

    @Test
    public void test() {
        //assertBundleActive("OSGi Sandbox :: Webapp Deployment :: Webapp");
    }
}
