package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.Workload;
import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(j) from Duty j where j.publica = 1")
	Integer numberOfPublicDuty(); 
	
	@Query("select count(j) from Duty j where j.publica = 0")
	Integer numberOfPrivateDuty();
		
	@Query("select j from Duty j")
	Collection<Duty> findDuties();
	
	@Query("select j.workload from Duty j")
	Collection<Workload> findWorkloads();
}