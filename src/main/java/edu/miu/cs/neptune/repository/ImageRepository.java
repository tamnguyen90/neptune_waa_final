package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getImagesByProductId(Long id);
}
