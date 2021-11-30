package acme.features.officers.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerTaskRepository extends AbstractRepository {

@Query("select p from Provider p where p.userAccount.id = ?1")
Officer findOneProviderByUserAccountId(int id);

@Query("select o from Officer o where o.id = ?1")
Officer findOneOfficerbyUserAccountById(int id);

@Query("select p from Task p where p.id= ?1")
Task findOneTaskById(int taskId);

@Query("select t from Task t")
Collection<Task> findMany();
}