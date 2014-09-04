package com.carmanconsulting.sandbox.osgi.webapp;

import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFileExtendOption;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFileOption;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFilePutOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

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

    @Inject
    private CommandProcessor commandProcessor;

    private final Logger logger = LoggerFactory.getLogger(getClass());

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected void assertBundleActive(String symbolicName) {
        String listOutput = executeCommand(String.format("osgi:list -t 10 | grep '%s' | grep 'Active'", symbolicName));
        assertFalse("Feature " + symbolicName + " is not installed!", listOutput == null || listOutput.isEmpty());
    }

    @Configuration
    public Option[] config() {
        return options(

                karafDistributionConfiguration()
                        .frameworkUrl(maven().groupId("com.savoirtech.aetos").artifactId("aetos").type("tar.gz").version("1.5.6"))
                        .karafVersion("2.3.6")
                        .name("Aetos")
                        .unpackDirectory(new File("target/exam")),

                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiRegistryHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiServerPort", "44445"),
                new KarafDistributionConfigurationFilePutOption(KARAF_MANAGEMENT_CONFIG, "rmiServerHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(KARAF_SHELL_CONFIG, "sshHost", "127.0.0.1"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.useFallbackRepositories", "false"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.disableAether", "true"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.defaultRepositories", "file:${karaf.home}/${karaf.default.repository}@snapshots@id=karaf.${karaf.default.repository}"),
                new KarafDistributionConfigurationFilePutOption(PAX_URL_MVN_CONFIG, "org.ops4j.pax.url.mvn.repositories", "http://repo1.maven.org/maven2@id=central"),


                new KarafDistributionConfigurationFilePutOption(PAX_EXAM_RBC_CONFIG, "org.ops4j.pax.exam.rbc.rmi.host", "127.0.0.1"),

                new KarafDistributionConfigurationFilePutOption(FEATURES_CONFIG, "featuresBoot", "config,ssh,management,kar,obr,war"),

                //mavenWar("com.carmanconsulting.sandbox.osgi","webapp", "1.0-SNAPSHOT"),
                mavenBundle().groupId("com.carmanconsulting.sandbox.osgizzz").artifactId("webapp").type("war").version("1.0-SNAPSHOT"),

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
    protected String executeCommand(final String command) {
        return executeCommand(command, COMMAND_TIMEOUT);
    }

    /**
     * Executes a shell command and returns output as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command The command to execute.
     * @param timeout The amount of time in millis to wait for the command to execute.
     * @return command output
     */
    protected String executeCommand(final String command, final Long timeout) {
        String response;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
        FutureTask<String> commandFuture = new FutureTask<>(new Callable<String>() {
            public String call() {
                try {
                    logger.info("Executing command {}...", command);
                    commandSession.execute(command);
                } catch (Exception e) {
                    logger.error("Command threw exception!", e);
                    throw new RuntimeException(e);
                }
                printStream.flush();
                final String output = byteArrayOutputStream.toString();
                logger.info("Command {} output\n{}", command, output);
                return output;
            }
        });

        try {
            executor.submit(commandFuture);
            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("An exception occurred while executing command {}!", command, e);
            response = "SHELL COMMAND TIMED OUT: ";
        }

        return response;
    }

    @ProbeBuilder
    public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*;status=provisional");

        return probe;
    }

    @Test
    public void test() {
        logger.info(executeCommand("osgi:list -t 10"));
        assertBundleActive("OSGi Sandbox :: Webapp Deployment :: Webapp");
    }
}
