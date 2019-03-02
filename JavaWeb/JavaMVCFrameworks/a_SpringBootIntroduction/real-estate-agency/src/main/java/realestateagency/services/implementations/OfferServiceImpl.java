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
import java.math.BigDecimal;
import java.math.RoundingMode;
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

        List<OfferServiceModel> offers = this.findOfferByType(offerFindBindingModel.getApartmentType())
                .stream()
                .filter(o -> o.totalPrice().compareTo(offerFindBindingModel.getFamilyBudget()) < 1)
                .sorted(Comparator.comparing(OfferServiceModel::totalPrice))
                .collect(Collectors.toList());

        if (offers.size() < 1) {
            throw new IllegalArgumentException("There is no offer with requested type or in the budget");
        }

        this.offerRepository.deleteById(offers.get(0).getId());
    }

    public void findOfferOld(OfferFindBindingModel offerFindBindingModel) {

        System.out.println();
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Find model is not valid");
        }

        Offer offer = this.findAllOffers()
                .stream()
                .filter(o -> o.getApartmentType()
                        .toLowerCase()
                        .equals(offerFindBindingModel
                                .getApartmentType()
                                .toLowerCase()) &&
                        offerFindBindingModel.getFamilyBudget().compareTo(
                                o.getApartmentRent()
                                        .multiply(o.
                                                getAgencyCommission()
                                                .divide(new BigDecimal(100), RoundingMode.UNNECESSARY)
                                                .add(BigDecimal.ONE))) >= 1)
                .map(o -> this.mapperUtil.map(o, Offer.class))
                .findFirst()
                .orElse(null);

        if (offer == null) {
            throw new IllegalArgumentException("There is no offer with requested type or in the budget");
        }

        this.offerRepository.delete(offer);
    }

    @Override
    public List<OfferServiceModel> findOfferByType(String type) {
        return this.mapperUtil.map(this.offerRepository.findAllByApartmentType(type), OfferServiceModel.class);
    }

}
