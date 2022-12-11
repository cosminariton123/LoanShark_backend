package com.loansharkmss.LoanShark.v1.config;

import java.util.*;

public class RolePrivilegesConfig {

    public static Map<RoleConfig, List<PrivilegeConfig>> getMapping() {

        HashMap<RoleConfig, ArrayList<PrivilegeConfig>> mapper = new HashMap<>();

        //<---add mapping here--->
        //example:
        mapper.put(RoleConfig.ROLE_ADMIN, new ArrayList<>(Arrays.asList(PrivilegeConfig.None, PrivilegeConfig.DELETE)));
        mapper.put(RoleConfig.ROLE_SUPER, new ArrayList<>(Arrays.asList(PrivilegeConfig.WRITE, PrivilegeConfig.READ)));
        mapper.put(RoleConfig.ROLE_CLIENT, new ArrayList<>(Arrays.asList(PrivilegeConfig.None)));
        //<---add mapping here--->

        return makeMapImmutable(mapper);
    }

    private static Map<RoleConfig, List<PrivilegeConfig>> makeMapImmutable(HashMap<RoleConfig, ArrayList<PrivilegeConfig>> mapper) {
        Map<RoleConfig, List<PrivilegeConfig>> mapper_with_immutable_arrays = new HashMap<>();
        for (RoleConfig key : mapper.keySet()) {
            mapper_with_immutable_arrays.put(key, mapper.get(key));
        }
        return Collections.unmodifiableMap(mapper_with_immutable_arrays);
    }

}
