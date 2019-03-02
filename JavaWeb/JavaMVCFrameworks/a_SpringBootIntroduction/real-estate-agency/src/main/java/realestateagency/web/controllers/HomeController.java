package realestateagency.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import realestateagency.domain.models.view.OfferViewModel;
import realestateagency.services.OfferService;
import realestateagency.utils.HtmlReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static realestateagency.utils.Constants.*;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final HtmlReader reader;
    private final ModelMapper mapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader reader, ModelMapper mapper) {
        this.offerService = offerService;
        this.reader = reader;
        this.mapper = mapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        try {
            return prepareHtml(INDEX_TARGET_RELATIVE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return prepareHtml(INDEX_SRC_ABSOLUTE_PATH);
        }
    }

    private String prepareHtml(String filepath) throws IOException {

        List<OfferViewModel> offers = this.offerService.findAllOffers()
                .stream()
                .map(o -> this.mapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());

        StringBuilder offersHtml = new StringBuilder();
        if (offers.size() == 0) {
            offersHtml.append(EMPTY_APARTMENT_DIV);
        } else {
            for (OfferViewModel offer : offers) {
                offersHtml.append(getApartmentDiv(offer));
            }
        }

        return this.reader.readHtmlFile(filepath).replace(HTML_PLACEHOLDER, offersHtml.toString().trim());
    }


    private String getApartmentDiv(OfferViewModel offer) {
        return String
                .format(FORMAT_OFFER_DIV,
                        offer.getApartmentRent(),
                        offer.getApartmentType(),
                        offer.getAgencyCommission()).trim();
    }
}