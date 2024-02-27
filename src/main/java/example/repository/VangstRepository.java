package example.repository;

import example.model.Vangst;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VangstRepository extends CrudRepository<Vangst, Long> {

    List<Vangst> findAll();

}
