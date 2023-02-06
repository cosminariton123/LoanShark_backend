package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageMapper {

    Image MultiPartToImage(MultipartFile multipartFile);

}
