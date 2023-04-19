package pl.equipRental.assets;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    AssetDto save (AssetDto asset){
        Optional<Asset> assetBySerialNo = assetRepository.findBySerialNumber(asset.getSerialNumber());
        assetBySerialNo.ifPresent(a -> {
            throw new DuplicateSerialNumberException("Duplikat numeru seryjnego");
        });
        Asset assetEntity = assetMapper.toEntity(asset);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }
}
