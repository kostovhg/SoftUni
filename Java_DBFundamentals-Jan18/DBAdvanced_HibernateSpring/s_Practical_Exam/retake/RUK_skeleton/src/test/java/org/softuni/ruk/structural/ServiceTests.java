package org.softuni.ruk.structural;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.ruk.util.ReflectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTests {
    private Map<String, Class<?>> allClasses;

    @Before
    public void setUp() {
        allClasses = new ReflectionUtil().getAllClasses();
    }

    @Test
    public void project_ShouldContain6ServiceAnnotatedClasses() {
        long serviceAnnotatedClassesCount = this.allClasses
                .values()
                .stream()
                .filter(x -> x.isAnnotationPresent(Service.class))
                .count();

        Assert.assertTrue("Your project should contain atleast 5 @Service Annotated Classes.", 5 <= serviceAnnotatedClassesCount);
    }
}
