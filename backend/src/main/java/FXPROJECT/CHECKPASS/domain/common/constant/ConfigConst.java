package FXPROJECT.CHECKPASS.domain.common.constant;

import static io.swagger.models.HttpMethod.*;

public class ConfigConst {

    public static final String ALLOWED_ADD_MAPPING = "/**";

    public static final String[] ALLOWED_ORIGINS = {
            "http://localhost:3000",
            "http://127.0.0.1:3000",
            "http://localhost:8080",
            "http://127.0.0.1:8080",
            "http://127.0.0.1:5500",
            "http://localhost:5500"
    };

    public static final String[] ALLOWED_METHODS = {
            POST.name(),
            PUT.name(),
            GET.name(),
            HEAD.name(),
            OPTIONS.name(),
            DELETE.name()
    };

    public static final Boolean ALLOW_CREDENTIALS = true;

    public static final int ALLOW_MAX_AGE = 300;

    public static final String[] EXCLUDE_PATH_PATTERNS = {
            "/",
            "/login",
            "/users/professorSignup",
            "/users/studentSignup",
            "/users/duplication/*",
            "/logout",
            "/css/**",
            "/*.ico",
            "/error",
            "/viewElement/*",
            "/viewElement/*/*"
    };

}
