package AutoMappingExerciseApplication.services.impl;

import AutoMappingExerciseApplication.controllers.UserSession;
import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.models.entities.Order;
import AutoMappingExerciseApplication.models.entities.User;
import AutoMappingExerciseApplication.repositories.OrderRepository;
import AutoMappingExerciseApplication.repositories.UserRepository;
import AutoMappingExerciseApplication.services.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    @Override
    public String createOrder(UserSession userSession) {
        if (userSession.getShoppingCard().isEmpty()) {
            return "Shopping card is empty";
        }

        User user = userSession.getCurrentUser();
        Set<Game> currentShoppingCard = userSession.getShoppingCard();
        String gamesTitles = String.join(System.lineSeparator() + " -",
                currentShoppingCard.stream()
                        .map(Game::getTitle)
                        .collect(Collectors.toSet()));
        Order order = new Order();
        order.setBuyer(user);
        order.setProducts(currentShoppingCard);
        this.orderRepository.save(order);
        currentShoppingCard.forEach(g -> user.getGames().add(g));
        userSession.clearShoppingCard();
        this.userRepository.save(user);
        return String.format("Successfully bought games: \n -%s", gamesTitles);
    }
}
