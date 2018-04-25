package org.softuni.mostwanted;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MostWantedApplicationTests {
    @Test
    public void contextLoad() {
        Assert.assertTrue("Failure loading context.", true);
    }
}
