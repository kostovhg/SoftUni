package org.softuni.mostwanted.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {
    private URLClassLoader ucl;

    private Map<String, Class<?>> classMap;

    public ReflectionUtil() {
        this.classMap = new HashMap<String, Class<?>>();
        this.seedAllClasses();
    }

    private String getClassPackageName(File rootAppDirectory, String rootPackage, String className) {
        File clazz = this.getFile(rootAppDirectory.getPath(), className + ".class");

        String result = clazz.getPath().substring(clazz.getPath().indexOf(rootPackage.replace(".", "\\"))).replace("\\", ".").replace(".class", "");

        return result;
    }

    private File getFile(String filePath, String searchTerm) {
        File file = new File(filePath);

        if (file.getName().split("\\/")[file.getName().split("\\/").length - 1].equals(searchTerm)) {
            return file;
        }

        for (File child : file.listFiles()) {
            if (child.isDirectory()) {
                File result = getFile(child.getPath(), searchTerm);

                if (result != null) {
                    return result;
                }
            } else if (child.getName().split("\\/")[file.getName().split("\\/").length - 1].equals(searchTerm)) {
                return child;
            }
        }

        return null;
    }

    private String getPackageRoot(File rootAppFolder) {
        String rootPackage = rootAppFolder.getPath()
                .substring(rootAppFolder.getPath().indexOf("classes\\") + "classes\\".length()).replace("\\", ".");

        return rootPackage;
    }

    private Class<?> loadClass(String className) {
        try {
            File rootAppFolder = this.getFile(
                    this
                            .getClass()
                            .getResource("")
                            .getPath()
                            .replace("test-classes", "classes")
                            .substring(0,
                                    this.getClass()
                                            .getResource("")
                                            .getPath()
                                            .replace("test-classes", "classes")
                                            .indexOf("classes") + "classes".length()) + "/", "MostWantedApplication.class").getParentFile();

            String rootPackage = this.getPackageRoot(rootAppFolder);

            URL[] urls = new URL[0];

            try {
                urls = new URL[]{rootAppFolder.toURI().toURL()};

                ucl = URLClassLoader.newInstance(urls, Thread.currentThread().getContextClassLoader());
                Thread.currentThread().setContextClassLoader(ucl);

                return ucl.loadClass(this.getClassPackageName(rootAppFolder, rootPackage, className));
            } catch (MalformedURLException | ClassNotFoundException ignored) {
                ;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("There was an error loading your class. Aborting...");
        }

        return null;
    }

    private void seedFileIfClass(File file) {
        if(file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                this.seedFileIfClass(childFile);
            }
        }

        if(file.getName().endsWith(".class")) {
            String fileNameWithoutExtension = file.getName().replace(".class", "");

            this.classMap.putIfAbsent(fileNameWithoutExtension, this.loadClass(fileNameWithoutExtension));
        }
    }

    private void seedAllClasses() {
        File rootAppFolder = this.getFile(
                this
                        .getClass()
                        .getResource("")
                        .getPath()
                        .replace("test-classes", "classes")
                        .substring(0,
                                this.getClass()
                                        .getResource("")
                                        .getPath()
                                        .replace("test-classes", "classes")
                                        .indexOf("classes") + "classes".length()) + "/", "MostWantedApplication.class").getParentFile();

        this.seedFileIfClass(rootAppFolder);
    }

    public Class<?> getTestClass(String className) {
        if(!this.classMap.containsKey(className)) {
            throw new IllegalArgumentException("No class with name - \"" + className + "\" found. Aborting...");
        }

        return this.classMap.get(className);
    }

    public Map<String, Class<?>> getAllClasses() {
        return Collections.unmodifiableMap(this.classMap);
    }
}
