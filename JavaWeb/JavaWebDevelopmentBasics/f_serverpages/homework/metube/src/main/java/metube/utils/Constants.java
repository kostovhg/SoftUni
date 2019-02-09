package metube.utils;

/*
Lower the possibility for typos and strings mismatch
 */
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

    // Often used long strings
    public static final String TUBE_CREATE_BINDING_MODEL = "tubeCreateBindingModel";

    // Urls
    public static final String INDEX_URL = "/";
    public static final String TUBES_ALL = "/tubes/all";
    public static final String TUBES_CREATE = "/tubes/create";
    public static final String TUBES_DETAILS = "/tubes/details";

    // Patterns
    public static final String TUBES_DETAILS_URI_PATTERN = "/tubes/details?name=%s";
    public static final String REGEX_URI_PAIR_SEPARATOR = "=";
    public static final String YOUTUBE_LINK_REGEX_PATTERN = "^https:\\/\\/www\\.youtube\\.com\\/watch\\?v=[a-zA-Z0-9]{11}$";

    // JSPs names
    public static final String INDEX_JSP = "index";
    public static final String ALL_TUBES_JSP ="all-tubes";
    public static final String CREATE_TUBE_JSP = "create-tube";
    public static final String DETAILS_TUBE_JSP = "details-tube";

    // JSP constants, parameters, etc
    public static final String title = "title";
    public static final String COL_MD_12 = "col col-md-12";
    public static final String CENTER_IT = "d-flex justify-content-center";
    public static final String TUBE_DETAILS_VIEW_MODEL = "tubeDetailsViewModel";
    public static final String ATTRIBUTES_MAP = "attributesMap";
    public static final String PAGE_HEADING = "pageHeading";
    public static final String PAGE_SUBHEADING = "pageSubheading";
    public static final String TUBES_LIST = "tubesList";


}
