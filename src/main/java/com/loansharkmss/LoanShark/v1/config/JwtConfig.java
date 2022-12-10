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

    public static final String SECRET_KEY_FOR_JWT = get_secret_key();

    public static final String AUDIENCE = get_audience();

    public static final String DOMAIN = get_domain();

    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public static final Long JWT_VALIDITY = 30L * 24 * 60 * 60 * 1000; // 30 days

}
