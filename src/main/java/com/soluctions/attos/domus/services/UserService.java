package com.soluctions.attos.domus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soluctions.attos.domus.dtos.UserDto;
import com.soluctions.attos.domus.dtos.VerifyEmail;
import com.soluctions.attos.domus.entities.User;
import com.soluctions.attos.domus.entities.Role.Roles;
import com.soluctions.attos.domus.repositories.RoleRepository;
import com.soluctions.attos.domus.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public List<User> listAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try {
            listUsers = userRepository.findAll();

            return listUsers;

        } catch (Exception e) {
            log.error("Error in find users: ", e);
            return null;
        }
    }

    public void newUser(UserDto userDto) {
        try {
            var userRole = roleRepository.findByRoleName(Roles.USER.name());
            var userInDb = userRepository.findByUsername(userDto.username());

            if (userDto.id() != null) {
                updateUser(userDto);
            }

            if (userInDb.isPresent()) {
                throw new DataIntegrityViolationException("User already exists");
            }

            var user = new User();

            user.setUsername(userDto.username());
            user.setPassword(passwordEncoder.encode(userDto.password()));
            user.setRoles(Set.of(userRole));

            userRepository.save(user);

        } catch (Exception e) {
            log.error("Error in register new user: ", e);
        }

    }

    // TODO: Adicionar o token para rotas de inativar usario e dar update

    public void updateUser(UserDto userDto) {
        try {
            var user = userRepository.findById(userDto.id()).get();

            user.setUsername(userDto.username());
            user.setPassword(passwordEncoder.encode(userDto.password()));

            userRepository.save(user);

        } catch (Exception e) {
            log.error("Error in update user: ", e);
        }
    }

    public void inactiveUser(Long id) {
        try {
            userRepository.inactiveUser(id);
        } catch (Exception e) {
            log.error("Error in inactive user: ", e);
        }
    }

    public boolean findUserByEmail(VerifyEmail userEmail) {

        try {
            log.info(userEmail.email());
            Integer response = userRepository.findUserByEmail(userEmail.email());

            if (response > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.error("Error in find user by e-mail: ", e);
            return false;
        }
    }

}
