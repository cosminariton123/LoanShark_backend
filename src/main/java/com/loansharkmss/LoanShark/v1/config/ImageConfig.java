package com.loansharkmss.LoanShark.v1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageConfig {

    public static final String DEFAULT_PROFILE_IMAGE_PATH = "src/main/resources/defaultProfileImage.jfif";

    public static final Long DEFAULT_PROFILE_IMAGE_ID = 1L;

    @ConstantExpressions(ImageConfig.MAX_IMAGE_SIZE_IN_BYTES)
    public static final int MAX_IMAGE_SIZE_IN_BYTES = 1097152; //1MB MUST ALSO MIRROR IN APP PROPERTIES

}
