package realestateagency.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import realestateagency.domain.models.binding.OfferFindBindingModel;
import realestateagency.domain.models.binding.OfferRegisterBindingModel;
import realestateagency.domain.models.service.OfferServiceModel;
import realestateagency.services.OfferService;
import realestateagency.utils.MapperUtil;

@Controller
public class OfferController extends BaseController {

    private final OfferService offerService;

    @Autowired
    public OfferController(MapperUtil mapper, OfferService offerService ) {
        super(mapper);
        this.offerService = offerService;
    }

    @GetMapping("/reg")
    public String register() {
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel model) {

        try {
            this.offerService.registerOffer(this.mapper.map(model, OfferServiceModel.class));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return "redirect:/reg";
        }

        return "redirect:/";
    }

    @GetMapping("/find")
    public String find() {
        return "find.html";
    }

    @PostMapping("/find")
    public String findConfirm(@ModelAttribute(name = "model") OfferFindBindingModel model) {

        try {
            this.offerService.findOffer(model);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return super.redirect("find");
        }

        return super.redirect();
    }
}
