package com.nortic.posdash.ts.platforminitializer.service.impl;

import com.nortic.posdash.models.entity.RoleEntity;
import com.nortic.posdash.models.entity.UserEntity;
import com.nortic.posdash.ts.platforminitializer.repository.UserRepository;
import com.nortic.posdash.ts.platforminitializer.service.contract.IUserSvc;
import com.nortic.posdash.ts.platforminitializer.utils.Constants;
import com.nortic.posdash.ts.platforminitializer.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserSvcImpl implements IUserSvc {

    private static final Logger log = LoggerFactory.getLogger(UserSvcImpl.class);

    private final UserRepository userRepository;
    private final RoleSvcImpl roleSvc;

    @Autowired
    UserSvcImpl(UserRepository userRepository, RoleSvcImpl roleSvc) {
        this.userRepository = userRepository;
        this.roleSvc = roleSvc;
    }

    @Override
    @Transactional
    public void insertUserIfNotExist() {
        if (!userRepository.existsByUserName(Constants.DEFAULT_USERNAME)) {
            RoleEntity role = roleSvc.getRoleByRoleName(Constants.ROLE_ADMIN);
            UserEntity user = new UserEntity();
            user.setFullName("PosDash");
            user.setLastName("Admin");
            user.setUserName(Constants.DEFAULT_USERNAME);
            user.setPassword(PasswordUtils.encryptPassword("PosdashAdmin*"));
            user.setEmail("admin@posdash.com");
            user.setPhone("1234567890");
            user.setRole(role);
            user.setStatus(true);
            user.setAddress("123 Main St");
            user.setBirthDate(LocalDate.of(1997, 5, 22));
            userRepository.save(user);
            log.info("{} user added", Constants.DEFAULT_USERNAME);
        }
    }
}
