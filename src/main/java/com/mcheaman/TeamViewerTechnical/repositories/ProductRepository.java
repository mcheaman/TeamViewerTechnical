package com.mcheaman.TeamViewerTechnical.repositories;

import com.mcheaman.TeamViewerTechnical.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
