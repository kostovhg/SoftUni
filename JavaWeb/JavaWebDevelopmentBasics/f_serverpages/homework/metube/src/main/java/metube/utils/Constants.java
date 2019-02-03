package metube.utils;

public final class Constants {

    // Persistent unit name
    public static final String PERSISTANCE_UNIT_NAME = "metube";

    // DB name
    public static final String DB_NAME = "metube_db";

    // DB table names
    public static final String DB_TABLE_TUBES = "tubes";

    // Entity fields names (and table column names in some cases)
    public static final String TUBE_EF_ID = "id";
    public static final String TUBE_EF_NAME = "name";
    public static final String TUBE_EF_DESCRIPTION = "description";
    public static final String TUBE_EF_YOU_TUBE_LINK = "youTubeLink";
    public static final String TUBE_EF_UPLOADER = "uploader";

    // Columns names (different from entity fields names)
    public static final String TUBE_TF_YOU_TUBE_LINK = "you_tube_link";

    //
    public static final String TUBE_CREATE_BINDING_MODEL = "tubeCreateBindingModel";

    // JSP constants
    public static final String title = "title";
    public static final String COL_MD_12 = "col col-md-12";
    public static final String CENTER_IT = "d-flex justify-content-center";


}
