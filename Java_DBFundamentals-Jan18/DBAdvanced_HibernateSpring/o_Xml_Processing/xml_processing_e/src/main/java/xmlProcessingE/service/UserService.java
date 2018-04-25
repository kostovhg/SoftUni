package xmlProcessingE.service;

import xmlProcessingE.domain.dto.binding.user.UserCreateBindingModel;
import xmlProcessingE.domain.dto.view.users.UserModel;
import xmlProcessingE.domain.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    void saveAll(List<UserCreateBindingModel> users);

    List<User> findAll();

    List<UserModel> findAllBySoldProducts();
}
