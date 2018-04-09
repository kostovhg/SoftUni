package AutoMappingExerciseApplication.services.api;

import AutoMappingExerciseApplication.controllers.UserSession;
import AutoMappingExerciseApplication.repositories.OrderRepository;
import AutoMappingExerciseApplication.repositories.UserRepository;

public interface OrderService {

    String createOrder(UserSession userSession);
}
