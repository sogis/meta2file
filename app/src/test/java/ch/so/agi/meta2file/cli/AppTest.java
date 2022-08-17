package ch.so.agi.meta2file.cli;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import ch.so.agi.meta2file.cli.util.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class AppTest {
    static String WAIT_PATTERN = ".*database system is ready to accept connections.*\\s";

    // static: will be shared between test methods
    // non static: will be started before and stopped after each test method
    
    /*
     * DockerImageName.parse().asCompatibleSubstituteFor(): Damit wir unser eigenes PostGIS-Image verwenden können, auf ARM64 läuft.
     * Das sollte man aber nur machen, wenn man sicher ist, dass das Image wirklich TestContainer-kompatibel sind.
     *  
     * Erstellt eine Datenbank "simi".
     * Erstellt den Superuser "ddluser".
     * "init_postgresql.sql" bei Bedarf in das src/test/resource-Verzeichnis kopieren.
     */
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("sogis/postgis:14-3.2").asCompatibleSubstituteFor("postgres"))
        .withDatabaseName("simi")
        .withUsername(TestUtil.PG_CON_DDLUSER)
        .withPassword(TestUtil.PG_CON_DDLPASS)
        //.withInitScript("init_postgresql.sql")
        .waitingFor(Wait.forLogMessage(WAIT_PATTERN, 2));

    
    @Test
    void test() throws Exception {
        assertTrue(postgres.isRunning());
        
        System.out.println(postgres.getJdbcUrl());
        System.out.println(postgres.getUsername());        
        System.out.println(postgres.getPassword());        
    }
}
