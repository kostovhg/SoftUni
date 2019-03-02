package realestateagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import realestateagency.domain.entities.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {

    List<Offer> findAllByApartmentType(String type);

    void deleteById(String id);
}
