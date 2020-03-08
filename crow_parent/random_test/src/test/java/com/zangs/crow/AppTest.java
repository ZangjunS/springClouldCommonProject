package com.zangs.crow;

import com.zangs.crow.common.util.PhoneUtil;
import com.zangs.crow.common.util.RR;
import org.junit.Test;
import org.nutz.lang.random.R;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void matchPhone() {
        String myPhone = "18150637359";
        assertTrue(PhoneUtil.isMobileNO(myPhone));
    }
    @Test
    public void uuid(){
        System.out.println(R.UU32());
    }
}
