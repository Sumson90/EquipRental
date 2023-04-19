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



}
