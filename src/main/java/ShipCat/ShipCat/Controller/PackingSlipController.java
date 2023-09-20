package ShipCat.ShipCat.Controller;

import ShipCat.ShipCat.Model.PackingSlip;
import ShipCat.ShipCat.Service.PackingSlipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packing-slips")
public class PackingSlipController {
    @Autowired
    private PackingSlipService packingSlipService;

    @GetMapping
    public List<PackingSlip> getAllPackingSlips() {
        return packingSlipService.getAllPackingSlips();
    }

    @PostMapping
    public ResponseEntity<PackingSlip> createPackingSlip(@Valid @RequestBody PackingSlip packingSlip) {
        PackingSlip createdPackingSlip = packingSlipService.createPackingSlip(packingSlip);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPackingSlip);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackingSlip> getPackingSlipByID(@PathVariable Long id) {
        PackingSlip foundPackingSlip = packingSlipService.getPackingSlipById(id);
        if (foundPackingSlip != null) {
            return ResponseEntity.ok(foundPackingSlip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<PackingSlip> updatePackingSlip(@Valid @RequestBody PackingSlip packingSlip) {
        PackingSlip updatedPackingSlip = packingSlipService.updatePackingSlip(packingSlip);

        if (updatedPackingSlip != null) {
            return ResponseEntity.ok(updatedPackingSlip);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackingSlip(@PathVariable Long id) {
        if (packingSlipService.deletePackingSlip(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
