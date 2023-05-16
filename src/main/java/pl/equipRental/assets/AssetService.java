package pl.equipRental.assets;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.equipRental.assets.dto.AssetAssignmentDto;
import pl.equipRental.exception.DuplicateSerialNumberException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    List<AssetDto> findAllByNameOrSerialNumber(String text) {
        return assetRepository.findAllByNameOrSerialNumber(text)
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    Optional<AssetDto> findById(Long id) {
        return assetRepository.findById(id).map(assetMapper::toDto);
    }

    AssetDto save(AssetDto asset) {
        Optional<Asset> assetBySerialNo = assetRepository.findBySerialNumber(asset.getSerialNumber());
        assetBySerialNo.ifPresent(a -> {
            throw new DuplicateSerialNumberException("Duplikat numeru seryjnego");
        });
        return mapAndSave(asset);
    }

    AssetDto update(AssetDto asset) {
        Optional<Asset> assetBySerialNo = assetRepository.findBySerialNumber(asset.getSerialNumber());
        assetBySerialNo.ifPresent(a -> {
            if (!a.getId().equals(asset.getId())) {
                throw new DuplicateSerialNumberException("Duplikat numeru seryjnego");
            }
        });
        return mapAndSave(asset);
    }

    private AssetDto mapAndSave(AssetDto asset) {
        Asset assetEntity = assetMapper.toEntity(asset);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }
    List<AssetAssignmentDto> getAssetAssignments(Long id) {
        return assetRepository.findById(id)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(AssetAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

}
