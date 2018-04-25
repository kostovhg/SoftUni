package org.softuni.mostwanted.structural;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.mostwanted.util.ReflectionUtil;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConstantsTests {
    private Map<String, Class<?>> allClasses;

    @Before
    public void setUp() {
        allClasses = new ReflectionUtil().getAllClasses();
    }

    @Test
    public void project_ShouldContainConstants() {
        boolean hasConstants = this.allClasses
                .values()
                .stream()
                .anyMatch(x ->
                        Arrays.stream(x.getDeclaredFields())
                .anyMatch(f -> Modifier.isFinal(f.getModifiers()) && Modifier.isStatic(f.getModifiers())));

        Assert.assertTrue("Your project should contain atleast 1 Constant. It is big enough for constants.", hasConstants);
    }
}
