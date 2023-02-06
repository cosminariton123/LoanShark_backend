package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.BadRequest;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.ImageMapper;
import com.loansharkmss.LoanShark.v1.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class LoanSharkImageMapper implements ImageMapper {

    public Image MultiPartToImage(MultipartFile multipartFile) {

        try {
            return new Image(
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getBytes()
            );

        }catch (IOException e) {
            e.printStackTrace();
            throw new BadRequest("Can't read provided data. Please try again!");
        }
    }
}
