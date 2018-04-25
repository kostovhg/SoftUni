package org.softuni.mostwanted.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.mostwanted.util.ReflectionUtil;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class RaceEntryEntityTests {
    private static final String CLASS_NAME = "RaceEntry";

    private Class<?> entityClass;

    @Before
    public void setUp() {
        entityClass = new ReflectionUtil().getTestClass(CLASS_NAME);
    }

    @Test
    public void raceEntryEntity_ShouldHaveIdAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        boolean hasId =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(Id.class) && ef.getName().equals("id"))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(Id.class) && em.getName().equals("getId"));

        Assert.assertTrue("No Id annotation detected on " + CLASS_NAME + " binding.", hasId);
    }

    @Test
    public void raceEntryEntity_CarFieldShouldHaveRelationshipAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "car";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
        Class relationshipClass = ManyToOne.class;

        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(relationshipClass)
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em ->
                                        em.isAnnotationPresent(relationshipClass)
                                                && em.getName().equals(getterName));

        Assert.assertTrue("No Relationship configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " binding.", hasNameRequired);
    }

    @Test
    public void raceEntryEntity_RacerFieldShouldHaveRelationshipAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "racer";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
        Class relationshipClass = ManyToOne.class;

        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(relationshipClass)
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em ->
                                        em.isAnnotationPresent(relationshipClass)
                                                && em.getName().equals(getterName));

        Assert.assertTrue("No Relationship configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " binding.", hasNameRequired);
    }
}
