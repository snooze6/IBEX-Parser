package Parser;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * Created by snooze on 3/17/16.
 */
public class IbexParserTest {

    @Test
    public void testParse() throws Exception {
        int cant = IbexParser.parse().size();
        assertTrue(cant==35);
    }
}