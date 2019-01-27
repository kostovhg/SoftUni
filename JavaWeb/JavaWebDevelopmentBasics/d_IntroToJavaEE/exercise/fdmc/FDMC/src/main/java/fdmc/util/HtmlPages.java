package fdmc.util;

public final class HtmlPages {

    public static final String HTML_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>FDMC - {{page}}</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "        {{body}}\n" +
            "</body>\n" +
            "</html>";

    public static final String HOME_BODY = "<h1>Welcome to Fluffy Duffy Munchkin Cats!</h1>\n" +
            "    <h4>Navigate through the application using the links below!</h4>\n" +
            "    <hr>\n" +
            "    <a href=\"/cats/create\">Create Cat</a>\n" +
            "    <br>\n" +
            "    <a href=\"/cats/all\">All Cats</a>";

    public static final String CREATE_BODY = "<h1>Create a Cat!</h1>\n" +
            "    <form action=\"/cats/create\" method=\"post\">\n" +
            "        <label for=\"name\">Name:\n" +
            "            <input type=\"text\" id=\"name\" name=\"name\" required>\n" +
            "        </label><br/>\n" +
            "        <label for=\"breed\">Breed:\n" +
            "            <input type=\"text\" id=\"breed\" name=\"breed\">\n" +
            "        </label><br/>\n" +
            "        <label for=\"color\">Color:\n" +
            "            <input type=\"text\" id=\"color\" name=\"color\">\n" +
            "        </label><br/>\n" +
            "        <label for=\"numberOfLegs\">Nuber of legs:\n" +
            "            <input type=\"number\" id=\"numberOfLegs\" name=\"numberOfLegs\" value=\"4\">\n" +
            "        </label><br/>\n" +
            "        <input type=\"submit\" value=\"Create Cat\">\n" +
            "    </form>\n" +
            "    <br />\n" +
            "    <a href=\"/\">Back To Home</a>";

    public static final String PROFILE_BODY = "    <h1>Cat - {{name}}</h1>\n" +
            "    <hr/>\n" +
            "    <h3>Breed: {{breed}}</h3>\n" +
            "    <h3>Color: {{color}}</h3>\n" +
            "    <h3>Number of legs: {{legs}}</h3>\n" +
            "\n" +
            "    <br/>\n" +
            "    <a href=\"/cats/all\">Back</a>";

    public static final String NOT_EXISTENT_BODY = "<h1>Cat with name - {{name}} was not found.</h1>\n" +
            "    <br/>\n" +
            "    <a href=\"/cats/all\">Back To Home</a>";

    public static final String ALL_CATS_BODY = "<h1>All Cats</h1>\n" +
            "   <hr />\n" +
            "   {{content}}" +
            "   <br />\n" +
            "   <a href=\"/\">Back To Home</a>";

    public static final String ALL_CATS_EMPTY = "<h3>There are no cats. <a href=\"/cats/create\">Create some!</a></h3>\n";

}
