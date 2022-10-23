package no.kantega.kedegari_security_workshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SecretRepository extends JpaRepository<Secret, Long> {
    @Query("SELECT s FROM Secret s WHERE s.category = :category")
    List<Secret> findByCategory(@Param("category") String category);
}
