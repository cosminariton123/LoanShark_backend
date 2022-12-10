package com.loansharkmss.LoanShark.v1.scripts;

import com.loansharkmss.LoanShark.config.DefaultConfigFlag;
import com.loansharkmss.LoanShark.config.DefaultConfigFlagRepository;
import com.loansharkmss.LoanShark.v1.config.PrivilegeConfig;
import com.loansharkmss.LoanShark.v1.config.RoleConfig;
import com.loansharkmss.LoanShark.v1.config.RolePrivilegesConfig;
import com.loansharkmss.LoanShark.v1.model.Privilege;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.repository.PrivilegeRepository;
import com.loansharkmss.LoanShark.v1.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InstantiateDefaultValues {

    public DefaultConfigFlagRepository defaultConfigFlagRepository;

    public PrivilegeRepository privilegeRepository;

    public RoleRepository roleRepository;

    public InstantiateDefaultValues (DefaultConfigFlagRepository defaultConfigFlagRepository, PrivilegeRepository privilegeRepository, RoleRepository roleRepository) {
        this.defaultConfigFlagRepository = defaultConfigFlagRepository;
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
    }

    public void instantiateDefaultValuesIfNotSet() {
        DefaultConfigFlag defaultConfigFlag = defaultConfigFlagRepository.findDefaultConfigFlagById(1);
        if (defaultConfigFlag != null)
            return;

        List<Privilege> privileges = Arrays
                .stream(RoleConfig.class.getEnumConstants())
                .map(privilege -> new Privilege(privilege.toString()))
                .collect(Collectors.toList());

        List<Role> roles = Arrays
                .stream(RoleConfig.class.getEnumConstants())
                .map(role -> new Role(role.toString()))
                .collect(Collectors.toList());


        privilegeRepository.saveAll(privileges);

         Map<RoleConfig, List<PrivilegeConfig>> mapping = RolePrivilegesConfig.getMapping();
         for(RoleConfig roleConfig: mapping.keySet()){
             for (Role role : roles){
                 if (role.toString().equals(roleConfig.toString())){
                     for(PrivilegeConfig privilegeConfig : mapping.get(roleConfig))
                         role.getPrivileges().add(privilegeRepository.findPrivilegeByName(privilegeConfig.toString()));
                 }
             }
         }

        roleRepository.saveAll(roles);

        DefaultConfigFlag defaultConfigFlag1 = new DefaultConfigFlag();
        defaultConfigFlagRepository.save(defaultConfigFlag1);
    }

}
