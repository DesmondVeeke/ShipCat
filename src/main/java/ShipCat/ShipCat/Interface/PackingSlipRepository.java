package ShipCat.ShipCat.Interface;

import ShipCat.ShipCat.Model.PackingSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingSlipRepository extends JpaRepository<PackingSlip, Long> {
}