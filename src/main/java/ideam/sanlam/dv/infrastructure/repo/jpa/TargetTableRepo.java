package ideam.sanlam.dv.infrastructure.repo.jpa;

import ideam.sanlam.dv.domain.model.TargetTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetTableRepo extends CrudRepository<TargetTable,Long> {
}
