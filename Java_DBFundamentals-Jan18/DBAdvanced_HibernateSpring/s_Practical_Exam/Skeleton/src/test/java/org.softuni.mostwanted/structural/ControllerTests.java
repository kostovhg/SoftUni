package org.softuni.mostwanted.structural;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.mostwanted.util.ReflectionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTests {
    private Map<String, Class<?>> allClasses;

    @Before
    public void setUp() {
        allClasses = new ReflectionUtil().getAllClasses();
    }

    @Test
    public void project_ShouldContain6ControllerAnnotatedClasses() {
        long controllerAnnotatedClassesCount = this.allClasses
                .values()
                .stream()
                .filter(x -> x.isAnnotationPresent(Controller.class))
                .count();

        Assert.assertEquals("Your project should contain atleast 6 @Controller Annotated Classes.", 6, controllerAnnotatedClassesCount);
    }
}
