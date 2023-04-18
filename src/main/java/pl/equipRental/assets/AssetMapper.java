package pl.equipRental.assets;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.equipRental.category.Category;
import pl.equipRental.category.CategoryRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class AssetMapper {
    private final CategoryRepository categoryRepository;

    AssetDto toDto(Asset asset) {
        AssetDto dto = new AssetDto();
        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setDescription(asset.getDescription());
        dto.setSerialNumber(asset.getSerialNumber());
        if(asset.getCategory() != null)
            dto.setCategory(asset.getCategory().getName());
        return dto;
    }

    Asset toEntity(AssetDto asset) {
        Asset entity = new Asset();
        entity.setId(asset.getId());
        entity.setName(asset.getName());
        entity.setDescription(asset.getDescription());
        Optional<Category> category = categoryRepository.findByName(asset.getCategory());
        category.ifPresent(entity::setCategory);
        entity.setSerialNumber(asset.getSerialNumber());
        return entity;
    }

}

