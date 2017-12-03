package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while (!"HARVEST".equalsIgnoreCase(input = reader.readLine())){
		    Field[] theRichSoilLandFields = RichSoilLand.class.getDeclaredFields();
                    //Class.forName("pr01HarvestingFields.RichSoilLand");
            for (Field field : theRichSoilLandFields) {
                String modToString = Modifier.toString(field.getModifiers());
                if(!"all".equalsIgnoreCase(input) && !modToString.equalsIgnoreCase(input)){
                    continue;
                }
                System.out.println(String.format("%s %s %s", modToString, field.getType().getSimpleName(), field.getName()));

            }
        }
	}
}
