package minionsORM.scanner;

import minionsORM.annotations.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityScanner {

    private int extensionLength = ".java".length();

    /**
     * Return all java classes that represent entities
     * @param path - relative path to "entities" package from project directory
     * @return - return list of classes with annotation "Entity"
     * @throws ClassNotFoundException - if there there is file that is no class in the folder and sub folders from path
     */
    public List<Class> getAllEntities(String path) throws ClassNotFoundException {
        path = path.replace("db_advancedjavaapps_introduction", "g_Db_Apps_Introduction");
        path += File.separator + "src" + File.separator +
                "main" + File.separator +
                "java" + File.separator +
                "minionsORM" + File.separator +
                "entities";

        File dir = new File(path);
        File[] files = dir.listFiles();
        List<Class> entities = new ArrayList<>();

        for (File file : files) {
            if(file.isFile()){
                String filename = file.getName();

                Class newClass = Class.forName("minionsORM.entities." +  filename.substring(0, filename.length() - extensionLength));

                if(!newClass.isAnnotationPresent(Entity.class)){
                    continue;
                }
                entities.add(newClass);
            } else {
                entities.addAll(getAllEntities((path + File.separator + file.getName())));
            }
        }
        return entities;
    }
}
