package acme.features.officers.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerDutyRepository extends AbstractRepository {

@Query("select p from Provider p where p.userAccount.id = ?1")
Officer findOneProviderByUserAccountId(int id);

@Query("select o from Officer o where o.id = ?1")
Officer findOneOfficerbyUserAccountById(int id);

@Query("select p from Duty p where p.id= ?1")
Duty findOneDutyById(int taskId);

@Query("select t from Duty t")
Collection<Duty> findMany();
}