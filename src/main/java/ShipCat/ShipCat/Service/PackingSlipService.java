package ShipCat.ShipCat.Service;

import ShipCat.ShipCat.Interface.PackingSlipRepository;
import ShipCat.ShipCat.Model.PackingSlip;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackingSlipService {
    private final PackingSlipRepository packingSlipRepository;
    private final Logger logger;

    @Autowired
    public PackingSlipService(PackingSlipRepository packingSlipRepository, Logger logger) {
        this.packingSlipRepository = packingSlipRepository;
        this.logger = logger;
    }

    public List<PackingSlip> getAllPackingSlips() {
        return packingSlipRepository.findAll();
    }

    public PackingSlip getPackingSlipById(Long id) {
        return packingSlipRepository.findById(id).orElse(null);
    }

    public PackingSlip createPackingSlip(PackingSlip packingSlip) {
        logger.info("Created: " + packingSlip.toString());
        return packingSlipRepository.save(packingSlip);
    }

    public PackingSlip updatePackingSlip(PackingSlip updatedPackingSlip) {
        PackingSlip existingPackingSlip = packingSlipRepository.findById(updatedPackingSlip.getId()).orElse(null);
        if (existingPackingSlip != null) {
            existingPackingSlip.setReferenceNumber(updatedPackingSlip.getReferenceNumber());
            return packingSlipRepository.save(existingPackingSlip);
        }
        logger.error("Could not find packing slip associated with the sent updated packing slip: " + updatedPackingSlip.toString());
        return null;
    }

    public boolean deletePackingSlip(Long id) {
        PackingSlip existingPackingSlip = packingSlipRepository.findById(id).orElse(null);
        if (existingPackingSlip != null) {
            packingSlipRepository.delete(existingPackingSlip);
            return true;
        }
        logger.error("Could not delete packing slip associated with the sent id: " + id + ". It was probably not found.");
        return false;
    }
}
