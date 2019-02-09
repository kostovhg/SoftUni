package metube.utils;

/*
Repeated string patterns for jsp file names extracted in separate class methods
 */
public final class JspFileNameBuilder {

    public static String getJsp(String name, String path){
        return String.format("%s/%s.jsp", path, name);
    }

    public static String getJsp(String name){
        return getJsp(name, "/jsps");
    }
}
