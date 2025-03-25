package com.nortic.posdash.ts.platforminitializer.command.impl;

import com.nortic.posdash.ts.platforminitializer.command.contract.ICommandArgs;
import com.nortic.posdash.ts.platforminitializer.service.impl.RoleSvcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleCmd implements ICommandArgs<String> {

    final RoleSvcImpl role;

    @Autowired
    RoleCmd(RoleSvcImpl role) {
        this.role = role;
    }

    @Override
    public void execute(String data) {
        role.insertRoleIfNotExist(data);
    }
}
