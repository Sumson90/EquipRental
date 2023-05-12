package pl.equipRental.assets;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.equipRental.exception.BadRequestException;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    @GetMapping("")
    public List<AssetDto> findAll() {
        return assetService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<AssetDto> save(@RequestBody AssetDto asset) {
        if (asset.getId() != null) {
            throw new BadRequestException("Zapisywany obiekt nie może mieć ustawionego id");
        }
        AssetDto savedAsset = assetService.save(asset);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAsset.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedAsset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> findById(@PathVariable Long id) {
        return assetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDto> update(@PathVariable Long id,
                                           @RequestBody AssetDto asset) {
        if (!id.equals(asset.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt powinien mieć id zgodne z id ścieżki zasobu"
            );
        }
        AssetDto updatedAsset = assetService.update(asset);
        return ResponseEntity.ok(updatedAsset);
    }



}
