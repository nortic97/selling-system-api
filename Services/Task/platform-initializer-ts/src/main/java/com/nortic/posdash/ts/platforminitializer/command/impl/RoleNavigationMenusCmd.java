package com.nortic.posdash.ts.platforminitializer.command.impl;

import com.nortic.posdash.ts.platforminitializer.command.contract.ICommandNoArgs;
import com.nortic.posdash.ts.platforminitializer.service.impl.RoleNavigationMenuSvcImpl;
import org.springframework.stereotype.Component;

@Component
public class RoleNavigationMenusCmd implements ICommandNoArgs {

    private final RoleNavigationMenuSvcImpl roleNavigationMenuSvc;

    public RoleNavigationMenusCmd(RoleNavigationMenuSvcImpl roleNavigationMenuSvc) {
        this.roleNavigationMenuSvc = roleNavigationMenuSvc;
    }

    @Override
    public void execute() {
        roleNavigationMenuSvc.insertRoleNavigationMenuIfNotExist();
    }
}
