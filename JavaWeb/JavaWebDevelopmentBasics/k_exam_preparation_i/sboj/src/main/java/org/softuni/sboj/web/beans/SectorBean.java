package org.softuni.sboj.web.beans;

import org.softuni.sboj.util.Sector;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named("sectorBean")
@RequestScoped
public class SectorBean {

    public SelectItem[] getSectorValues() {
        SelectItem[] items = new SelectItem[Sector.values().length + 1];
        int i = 1;
        items[0] = new SelectItem("", "Select Sector ...", "", true);
        for(Sector s: Sector.values()) {
            items[i++] = new SelectItem(s, s.getSector());
        }
        return items;
    }

}