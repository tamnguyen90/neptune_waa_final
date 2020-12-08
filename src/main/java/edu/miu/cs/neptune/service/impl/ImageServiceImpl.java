package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.domain.Image;
import edu.miu.cs.neptune.repository.ImageRepository;
import edu.miu.cs.neptune.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getImagesByProductId(Long id) {
        return imageRepository.getImagesByProductId(id);
    }
}
