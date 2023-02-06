package com.loansharkmss.LoanShark.v1.repository;

import com.loansharkmss.LoanShark.v1.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository  extends JpaRepository<Image, Long> {

    Image findImageById(Long id);

    Integer deleteImageById(Long id);

}
