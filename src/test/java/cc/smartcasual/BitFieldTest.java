package cc.smartcasual;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitFieldTest {

    @Test
    public void testGet() throws Exception
    {
        BitField bitField = new BitField(565);
        bitField.set(497);
        assertThat(bitField.get(497), is(true));
    }
}