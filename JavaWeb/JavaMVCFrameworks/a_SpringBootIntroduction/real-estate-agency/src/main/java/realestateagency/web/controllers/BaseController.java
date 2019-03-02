package realestateagency.web.controllers;

import realestateagency.utils.MapperUtil;

public abstract class BaseController {

    final MapperUtil mapper;

    BaseController(MapperUtil mapper) {
        this.mapper = mapper;
    }

    String redirect(String to) {
        return String.format("redirect:/%s", to);
    }

    String redirect(){
        return redirect("");
    }
}
