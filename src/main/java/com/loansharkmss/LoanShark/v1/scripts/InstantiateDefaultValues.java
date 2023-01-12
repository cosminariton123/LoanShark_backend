package com.loansharkmss.LoanShark.v1.scripts;

import com.loansharkmss.LoanShark.config.DefaultConfigFlag;
import com.loansharkmss.LoanShark.config.DefaultConfigFlagRepository;
import com.loansharkmss.LoanShark.v1.config.RoleConfig;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstantiateDefaultValues {

    public DefaultConfigFlagRepository defaultConfigFlagRepository;

    public RoleRepository roleRepository;

    public InstantiateDefaultValues (DefaultConfigFlagRepository defaultConfigFlagRepository, RoleRepository roleRepository) {
        this.defaultConfigFlagRepository = defaultConfigFlagRepository;
        this.roleRepository = roleRepository;
    }

    public void instantiateDefaultValuesIfNotSet() {
        DefaultConfigFlag defaultConfigFlag = defaultConfigFlagRepository.findDefaultConfigFlagById(1);
        if (defaultConfigFlag != null)
            return;

        List<Role> roles = Arrays
                .stream(RoleConfig.class.getEnumConstants())
                .map(role -> new Role(role.toString()))
                .collect(Collectors.toList());

        roleRepository.saveAll(roles);

        DefaultConfigFlag defaultConfigFlag1 = new DefaultConfigFlag();
        defaultConfigFlagRepository.save(defaultConfigFlag1);
    }

}
