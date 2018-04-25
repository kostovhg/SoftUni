package xmlProcessingE.service;

import org.springframework.beans.factory.annotation.Autowired;
import xmlProcessingE.domain.dto.DTOConvertUtil;
import xmlProcessingE.domain.dto.binding.user.UserCreateBindingModel;
import xmlProcessingE.domain.dto.view.users.UserModel;
import xmlProcessingE.domain.model.User;
import xmlProcessingE.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public void saveAll(List<UserCreateBindingModel> usersModel) {
        List<User> users = DTOConvertUtil.convert(usersModel, User.class);
        this.userRepository.saveAll(users);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<UserModel> findAllBySoldProducts() {
        List<UserModel> userModels = new ArrayList<>();
        for (User user : this.userRepository.findAllUsersWithSoldProducts()){
            userModels.add(DTOConvertUtil.convert(user, UserModel.class));
        }

        return userModels;
    }
}
