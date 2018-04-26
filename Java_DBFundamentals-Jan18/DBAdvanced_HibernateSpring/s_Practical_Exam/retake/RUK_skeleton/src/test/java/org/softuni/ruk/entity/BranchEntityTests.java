package org.softuni.ruk.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.ruk.model.entities.Branch;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class BranchEntityTests {
    private static final String CLASS_NAME = "Branch";

    private Class<?> entityClass;

    @Before
    public void setUp() {
        entityClass = Branch.class;
        //entityClass = new ReflectionUtil().getTestClass(CLASS_NAME);
    }

    @Test
    public void branchEntity_ShouldHaveIdAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        boolean hasId =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(Id.class) && ef.getName().equals("id"))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(Id.class) && em.getName().equals("getId"));

        Assert.assertTrue("No Id annotation detected on Branch entity.", hasId);
    }

    @Test
    public void branchEntity_NameFieldShouldBeRequired() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "name";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);


        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(Column.class)
                                && !ef.getAnnotation(Column.class).nullable()
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(Column.class)
                                        && !em.getAnnotation(Column.class).nullable()
                                        && em.getName().equals(getterName));

        Assert.assertTrue("No Required configuration on \"" + fieldName + "\" field detected in Branch entity.", hasNameRequired);
    }
}
