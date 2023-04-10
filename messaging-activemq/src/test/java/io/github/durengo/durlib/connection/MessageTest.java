package io.github.durengo.durlib.connection;

import io.github.durengo.durlib.connection.ConnectionHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void sendAndReceive()
    {
        final String expected = "test_msg";
        ConnectionHandler connection = new ConnectionHandler();
        connection.createSenderAndReceiver();
        connection.sendMessage(expected);
        final String actual = connection.getMessage();
        assertEquals(expected, actual);
    }
}
