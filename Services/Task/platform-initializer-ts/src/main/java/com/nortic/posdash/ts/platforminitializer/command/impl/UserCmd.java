package com.nortic.posdash.ts.platforminitializer.command.impl;

import com.nortic.posdash.ts.platforminitializer.command.contract.ICommandNoArgs;
import com.nortic.posdash.ts.platforminitializer.service.impl.UserSvcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCmd implements ICommandNoArgs {

    final UserSvcImpl user;

    @Autowired
    UserCmd(UserSvcImpl user) {
        this.user = user;
    }

    @Override
    public void execute() {
        user.insertUserIfNotExist();
    }
}
