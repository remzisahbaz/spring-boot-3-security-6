package example.repository;

import example.model.Blog;
import example.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {

    List<Blog> findByOrderByRedDateAsc();
    List<Blog> findByStatus(Status status);
    boolean existsByUrl(String url);


    @Modifying
    @Transactional
    @Query("update Blog b set b.status = ?2 where b.id = ?1")
    void updateBlogStatus(Long blog_id, Status status);

    @Query("SELECT b, m.message FROM Blog b LEFT JOIN Mening m ON b.id = m.blog.id WHERE b.status = :#{#status}")
    List<Object[]> findAllMergedWithMeningAndStatus(@Param("status") Status status);
}
