package softuni.todolist.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.todolist.entity.User;
import softuni.todolist.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service("listUserDetailsService")
public class ListUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }
        else {
            Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());

            return new org
                    .springframework
                    .security
                    .core
                    .userdetails
                    .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }


    private final UserRepository userRepository;

    public ListUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;


    }
}

