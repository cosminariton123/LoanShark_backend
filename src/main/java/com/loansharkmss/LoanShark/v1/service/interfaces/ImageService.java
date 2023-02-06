package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Image;

public interface ImageService {

    Image findImageById(Long id);

    Image saveNewImage(Image image);

    void deleteImageById(Long id);

}
