package org.softuni.mostwanted.structural;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.mostwanted.util.ReflectionUtil;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParserTests {
    private static final String CLASS_NAME = "Parser";

    private Class<?> testClass;

    private Map<String, Class<?>> allClasses;

    @Before
    public void setUp() {
        testClass = new ReflectionUtil().getTestClass(CLASS_NAME);
        allClasses = new ReflectionUtil().getAllClasses();
    }

    @Test
    public void parserInterface_ShouldBeImplemented() {
        boolean hasChildren = this.allClasses
                .entrySet()
                .stream()
                .anyMatch(x -> testClass.isAssignableFrom(x.getValue())
                && !x.getValue().getName().equals(testClass.getName()));

        Assert.assertTrue(testClass + " has no implementations.", hasChildren);
    }
}
