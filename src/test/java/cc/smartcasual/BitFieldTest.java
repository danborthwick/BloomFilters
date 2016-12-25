package cc.smartcasual;

import cc.smartcasual.filter.BitField;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BitFieldTest {

    @Test
    public void testGet() throws Exception
    {
        BitField bitField = new BitField(565);
        bitField.set(497);
        assertThat(bitField.get(496), is(false));
        assertThat(bitField.get(497), is(true));
        assertThat(bitField.get(498), is(false));
    }

    @Test
    public void testNumberOfBitsSetSmall() throws Exception {
        BitField bitField = new BitField(3);
        bitField.set(0);
        bitField.set(2);

        assertThat(bitField.count(), is(2));
    }

    @Test
    public void testNumberOfBitsSet() throws Exception {
        BitField bitField = new BitField(1067);
        bitField.set(0);
        bitField.set(42);
        bitField.set(123);
        bitField.set(1066);

        assertThat(bitField.count(), is(4));
    }
}