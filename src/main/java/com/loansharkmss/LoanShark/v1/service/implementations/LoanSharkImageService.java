package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.exceptions.UnsupportedMediaType;
import com.loansharkmss.LoanShark.v1.model.Image;
import com.loansharkmss.LoanShark.v1.repository.ImageRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.ImageService;
import org.springframework.stereotype.Service;

@Service
public class LoanSharkImageService implements ImageService {

    private final ImageRepository imageRepository;

    public LoanSharkImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image findImageById(Long id) {
        Image image = imageRepository.findImageById(id);

        if (image == null)
            throw new NotFoundException("Image with id " + id + " not found");

        return image;
    }

    public Image saveNewImage(Image image) {
        //TODO("Uncomment when FE finishes development")
        //if (!checkIfSupportedMediaType(image))
            //throw new UnsupportedMediaType("Only image types allowed. Type uploaded is " + image.getType());

        return imageRepository.save(image);
    }

    public void deleteImageById(Long id) {
        findImageById(id);
        Integer deletedCount = imageRepository.deleteImageById(id);

        if (deletedCount > 0)
            return;

        throw new InternalServerError("Failed to delete user with id " + id);
    }

    private Boolean checkIfSupportedMediaType(Image image) {
        if(!image.getType().split("/")[0].equals("image"))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }
}
