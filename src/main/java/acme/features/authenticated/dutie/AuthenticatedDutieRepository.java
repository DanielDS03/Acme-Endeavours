package acme.features.authenticated.dutie;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Dutie;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutieRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.publica = true")
	Collection<Dutie> findDutie();
	
	@Query("select t from Dutie t where t.id = ?1")
	Dutie findOneDutieById(int id);
}

