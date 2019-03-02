package realestateagency.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realestateagency.domain.entities.Offer;
import realestateagency.domain.models.binding.OfferFindBindingModel;
import realestateagency.domain.models.service.OfferServiceModel;
import realestateagency.repository.OfferRepository;
import realestateagency.services.OfferService;
import realestateagency.utils.MapperUtil;

import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final Validator validator;
    private final MapperUtil mapperUtil;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, MapperUtil mapperUtil) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {

        if (this.validator.validate(offerServiceModel).size() != 0) {
            throw new IllegalArgumentException("what?");
        }

        this.offerRepository.saveAndFlush(this.mapperUtil.map(offerServiceModel, Offer.class));

    }

    @Override
    public List<OfferServiceModel> findAllOffers() {
        return this.mapperUtil.map(this.offerRepository.findAll(), OfferServiceModel.class);
    }

    @Override
    public void findOffer(OfferFindBindingModel offerFindBindingModel) {

        System.out.println();
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Find model is not valid");
        }

        List<OfferServiceModel> offers = getFilteredOffers(offerFindBindingModel);

        if (offers.size() < 1) {
            throw new IllegalArgumentException("There is no offer with requested type or in the budget");
        }

        this.offerRepository.deleteById(offers.get(0).getId());
    }

    @Override
    public List<OfferServiceModel> findOfferByType(String type) {
        return this.mapperUtil.map(this.offerRepository.findAllByApartmentType(type), OfferServiceModel.class);
    }

    private List<OfferServiceModel> getFilteredOffers(OfferFindBindingModel offerFindBindingModel) {
        return this.findOfferByType(offerFindBindingModel.getApartmentType())
                .stream()
                .filter(o -> o.totalPrice().compareTo(offerFindBindingModel.getFamilyBudget()) < 1)
                .sorted(Comparator.comparing(OfferServiceModel::totalPrice))
                .collect(Collectors.toList());
    }

}
