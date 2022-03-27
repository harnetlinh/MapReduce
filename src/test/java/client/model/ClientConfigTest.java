package client.model;

import client.model.ClientConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ClientConfigTest {
    ClientConfig clientConfig;

    @BeforeEach
    void setUp() {
        clientConfig = new ClientConfig("localhost", 1099);
    }

    @Test
    @DisplayName("Test client config creation successful")
    void testClientConfigCreation(){
        assertNotNull(clientConfig);
    }

    @Test
    @DisplayName("Config must be return after create")
    void testGetConfig() throws NotBoundException, RemoteException {
        assertEquals("localhost", clientConfig.getHost());
        assertEquals(1099, clientConfig.getPort());
        //assertNotNull(clientConfig.serviceLookup());
        //assertNotEquals(null, clientConfig.serviceLookup());
    }
}