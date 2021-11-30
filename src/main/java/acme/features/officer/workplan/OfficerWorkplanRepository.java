package acme.features.officer.workplan;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Officer;
import acme.entities.workplans.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerWorkplanRepository extends AbstractRepository {
	
	@Query("select w from Workplan w where w.officer.userAccount.id = ?1")
	Workplan findWorkplansByUserAccountById(int id);
	
	@Query("select o from Officer o where o.id = ?1")
	Officer findOneOfficerbyUserAccountById(int id);
	
	@Query("select w from Workplan w where w.id= ?1")
	Workplan findOneWorkplanById(int workplanId);
	
	@Query("select w from Workplan w")
	Collection<Workplan> findMany();
}
