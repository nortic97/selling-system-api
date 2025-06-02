package com.nortic.posdash.ts.platforminitializer.service.impl;

import com.nortic.posdash.ts.platforminitializer.command.impl.RoleCmd;
import com.nortic.posdash.ts.platforminitializer.command.impl.RoleNavigationMenusCmd;
import com.nortic.posdash.ts.platforminitializer.command.impl.UserCmd;
import com.nortic.posdash.ts.platforminitializer.service.contract.IPlatformInitializerService;
import com.nortic.posdash.ts.platforminitializer.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformInitializerSvcImpl implements IPlatformInitializerService {

    private static final Logger log = LoggerFactory.getLogger(PlatformInitializerSvcImpl.class);

    private final RoleCmd roleCmd;
    private final UserCmd userCmd;
    private final RoleNavigationMenusCmd roleNavigationMenuCmd;

    @Autowired
    public PlatformInitializerSvcImpl(RoleCmd roleCmd, UserCmd userCmd, RoleNavigationMenusCmd roleNavigationMenuCmd) {
        this.roleCmd = roleCmd;
        this.userCmd = userCmd;
        this.roleNavigationMenuCmd = roleNavigationMenuCmd;
    }

    @Override
    public void initializePlatform() {
        log.info("Initializing platform...");
        roleCmd.execute(Constants.ROLE_ADMIN);
        roleCmd.execute(Constants.ROLE_USER);
        userCmd.execute();
        roleNavigationMenuCmd.execute();
        log.info("Initializing platform complete.");
    }
}
