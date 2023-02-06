package com.loansharkmss.LoanShark.v1.scripts;

import com.loansharkmss.LoanShark.config.DefaultConfigFlag;
import com.loansharkmss.LoanShark.config.DefaultConfigFlagRepository;
import com.loansharkmss.LoanShark.v1.config.DefaultImagesConfig;
import com.loansharkmss.LoanShark.v1.config.RoleConfig;
import com.loansharkmss.LoanShark.v1.model.Image;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.repository.ImageRepository;
import com.loansharkmss.LoanShark.v1.repository.RoleRepository;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstantiateDefaultValues {

    public DefaultConfigFlagRepository defaultConfigFlagRepository;

    public RoleRepository roleRepository;

    public ImageRepository imageRepository;

    public InstantiateDefaultValues (DefaultConfigFlagRepository defaultConfigFlagRepository, RoleRepository roleRepository, ImageRepository imageRepository) {
        this.defaultConfigFlagRepository = defaultConfigFlagRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
    }

    public void instantiateDefaultValuesIfNotSet() throws IOException{
        DefaultConfigFlag defaultConfigFlag = defaultConfigFlagRepository.findDefaultConfigFlagById(1);
        if (defaultConfigFlag != null)
            return;

        instantiateDefaultRoles();
        instantiateDefaultProfileImage();

        DefaultConfigFlag defaultConfigFlag1 = new DefaultConfigFlag();
        defaultConfigFlagRepository.save(defaultConfigFlag1);
    }

    public void instantiateDefaultRoles() {
        List<Role> roles = Arrays
                .stream(RoleConfig.class.getEnumConstants())
                .map(role -> new Role(role.toString()))
                .collect(Collectors.toList());

        roleRepository.saveAll(roles);
    }

    public void instantiateDefaultProfileImage() throws IOException {

        File file = new File(DefaultImagesConfig.DEFAULT_PROFILE_IMAGE_PATH);

        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] data = new byte[(int)file.length()];

        fileInputStream.read(data);

        fileInputStream.close();

        String imageName = DefaultImagesConfig.DEFAULT_PROFILE_IMAGE_PATH.substring(
                DefaultImagesConfig.DEFAULT_PROFILE_IMAGE_PATH.lastIndexOf("/") + 1);

        Image image = new Image(
                imageName,
                "image/" + imageName.split("\\.")[1],
                data
        );

        imageRepository.save(image);
    }

}
