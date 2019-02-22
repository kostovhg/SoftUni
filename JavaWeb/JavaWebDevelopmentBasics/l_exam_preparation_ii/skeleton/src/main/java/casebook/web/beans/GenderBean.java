package casebook.web.beans;

import casebook.domain.entities.Gender;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named("genderBean")
@RequestScoped
public class GenderBean extends BaseBean {

//    public SelectItem[] getSelectorValues() {
//        SelectItem[] items = new SelectItem[Gender.values().length + 1];
//        items[0] = new SelectItem(Gender.MALE, "Select Gender ...", "", true);
//        int i = 1;
//        for (Gender s : Gender.values()) {
//            items[i++] = new SelectItem(s, s.getGender());
//        }
//        return items;
//    }


    public SelectItem[] getSelectorValues() {
        SelectItem[] items = new SelectItem[Gender.values().length + 1];
        items[0] = new SelectItem(Gender.Male, "Select Gender ...", "", true);
        int i = 1;
        for (Gender s : Gender.values()) {
            items[i++] = new SelectItem(s, s.toString());
        }
        return items;
    }
}