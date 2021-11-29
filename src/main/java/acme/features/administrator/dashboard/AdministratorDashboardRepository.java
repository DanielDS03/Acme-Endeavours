package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.datatypes.Workload;
import acme.entities.duties.Dutie;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(j) from Dutie j where j.publica = 1")
	Integer numberOfPublicDutie(); 
	
	@Query("select count(j) from Dutie j where j.publica = 0")
	Integer numberOfPrivateDutie();
		
	@Query("select j from Dutie j")
	Collection<Dutie> findDuties();
	
	@Query("select j.workload from Dutie j")
	Collection<Workload> findWorkloads();
}