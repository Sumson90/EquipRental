package pl.equipRental.assets;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
