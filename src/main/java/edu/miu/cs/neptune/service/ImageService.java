package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Image;

import java.util.List;

public interface ImageService {
    List<Image> getImagesByProductId(Long id);
}
