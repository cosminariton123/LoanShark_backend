package com.loansharkmss.LoanShark.v1.mappers.interfaces;

import com.loansharkmss.LoanShark.v1.dtos.ImageCard;
import com.loansharkmss.LoanShark.v1.dtos.ImageCreateRequest;
import com.loansharkmss.LoanShark.v1.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageMapper {

    Image MultiPartToImage(MultipartFile multipartFile);

    Image ImageCreateRequestToImage(ImageCreateRequest imageCreateRequest);

    ImageCard ImageToImageCard(Image image);

}
