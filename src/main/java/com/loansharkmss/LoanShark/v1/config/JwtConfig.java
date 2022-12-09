package com.loansharkmss.LoanShark.v1.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

    private JwtConfig(){
    }

    private static String get_secret_key() {
        String stored_secret_key = System.getenv("secret_key_for_jwt");

        if (stored_secret_key == null)
            stored_secret_key = "default_abc";

        return stored_secret_key;
    }

    private static String get_domain() {
        String domain = System.getenv("domain");

        if (domain == null)
            domain = "http://localhost";

        return domain;
    }

    private static String get_port() {
        String port = System.getenv("port");

        if (port == null)
            port = "8080";

        return port;
    }

    private static String get_audience() {
        String audience = System.getenv("audience");

        if (audience == null)
            audience = get_domain() + ":" + get_port();

        return audience;
    }

    public static final String secret_key_for_jwt = get_secret_key();

    public static final String audience = get_audience();

    public static final String domain = get_domain();

    public static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

    public static final Long JWT_VALIDITY = Long.valueOf(30 * 24 * 60 * 60 * 1000); // 30 days

}
