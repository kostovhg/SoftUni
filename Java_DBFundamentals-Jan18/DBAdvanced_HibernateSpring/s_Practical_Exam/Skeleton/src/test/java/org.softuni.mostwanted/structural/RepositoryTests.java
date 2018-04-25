package org.softuni.mostwanted.structural;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.mostwanted.util.ReflectionUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTests {
    private Map<String, Class<?>> allClasses;

    @Before
    public void setUp() {
        allClasses = new ReflectionUtil().getAllClasses();
    }

    @Test
    public void project_ShouldContain6RepositoryAnnotatedClasses() {
        long repositoryAnnotatedClassesCount = this.allClasses
                .values()
                .stream()
                .filter(x -> x.isAnnotationPresent(Repository.class))
                .count();

        Assert.assertEquals("Your project should contain atleast 6 @Repository Annotated Classes.", 6, repositoryAnnotatedClassesCount);
    }

    @Test
    public void project_ShouldContain6RepositoryImplementations() {
        long repositoryClassesCount = this.allClasses
                .values()
                .stream()
                .filter(x -> JpaRepository.class.isAssignableFrom(x))
                .count();

        Assert.assertEquals("Your project should contain atleast 6 Repository Classes.", 6, repositoryClassesCount);
    }
}
