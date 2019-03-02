package realestateagency.services;

import realestateagency.domain.models.binding.OfferFindBindingModel;
import realestateagency.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    List<OfferServiceModel> findOfferByType(String type);

    void registerOffer(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> findAllOffers();

    void findOffer(OfferFindBindingModel offerFindBindingModel);

}
