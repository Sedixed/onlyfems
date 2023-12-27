package fr.univrouen.onlyfems.repositories;

import fr.univrouen.onlyfems.entities.Image;

// import org.hibernate.mapping.List;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {
    Image findByName(String name);
    Image findByPrivacy(boolean isPublic);
    List<Image> findAllByPrivacy(boolean isPublic);
}