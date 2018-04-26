package org.softuni.ruk.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.ruk.model.entities.Employee;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeEntityTests {
    private static final String CLASS_NAME = "Employee";

    private Class<?> entityClass;

    @Before
    public void setUp() {
        //entityClass = new ReflectionUtil().getTestClass(CLASS_NAME);
        entityClass = Employee.class;
    }

    @Test
    public void employeeEntity_ShouldHaveIdAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        boolean hasId =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(Id.class) && ef.getName().equals("id"))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(Id.class) && em.getName().equals("getId"));

        Assert.assertTrue("No Id annotation detected on " + CLASS_NAME + " entity.", hasId);
    }

    @Test
    public void employeeEntity_FirstNameFieldShouldBeRequired() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "firstName";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);

        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(JoinColumn.class)
                                && !ef.getAnnotation(JoinColumn.class).nullable()
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(Column.class)
                                        && !em.getAnnotation(Column.class).nullable()
                                        && em.getName().equals(getterName));

        Assert.assertTrue("No Required configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " entity.", hasNameRequired);
    }

    @Test
    public void employeeEntity_LastNameFieldShouldBeRequired() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "lastName";
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

        Assert.assertTrue("No Required configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " entity.", hasNameRequired);
    }

    @Test
    public void employeeEntity_branchFieldShouldBeRequired() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        Class annotationClass = Column.class;


        String fieldName = "branch";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);

        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(ManyToOne.class)
                                && !ef.getAnnotation(ManyToOne.class).optional()
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em -> em.isAnnotationPresent(ManyToOne.class)
                                        && !em.getAnnotation(ManyToOne.class).optional()
                                        && em.getName().equals(getterName));

        Assert.assertTrue("No Required configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " entity.", hasNameRequired);
    }

    @Test
    public void employeeEntity_BranchFieldShouldHaveRelationshipAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "branch";
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

        Assert.assertTrue("No Relationship configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " entity.", hasNameRequired);
    }

    @Test
    public void employeeEntity_clientsFieldShouldHaveRelationshipAnnotation() {
        Field[] entityFields = entityClass.getDeclaredFields();
        Method[] entityMethods = entityClass.getDeclaredMethods();

        String fieldName = "clients";
        String getterName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
        Class relationshipClass = ManyToMany.class;

        boolean hasNameRequired =
                Arrays.stream(entityFields)
                        .anyMatch(ef -> ef.isAnnotationPresent(relationshipClass)
                                && ef.getName().equals(fieldName))
                        ||
                        Arrays.stream(entityMethods)
                                .anyMatch(em ->
                                        em.isAnnotationPresent(relationshipClass)
                                                && em.getName().equals(getterName));

        Assert.assertTrue("No Relationship configuration on \"" + fieldName + "\" field detected in " + CLASS_NAME + " entity.", hasNameRequired);
    }
}
