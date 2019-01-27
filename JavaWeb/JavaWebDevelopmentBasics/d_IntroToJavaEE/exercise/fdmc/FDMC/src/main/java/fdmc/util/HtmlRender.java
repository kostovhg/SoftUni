package fdmc.util;

import java.util.Map;

import static fdmc.util.Constants.PATTERN_FORMAT;


public class HtmlRender {

       public String render(String templ, Map<String, String> replacements) {
        String result = templ;
        for (Map.Entry<String, String> kvp : replacements.entrySet()) {
            result = result.replace(getPattern(kvp.getKey()), kvp.getValue());
        }
       return result;
    }

    private String getPattern(String str){
        return String.format(PATTERN_FORMAT, str);
    }
}
