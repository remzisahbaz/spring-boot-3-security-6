package example.service;

import example.model.Blog;
import example.repository.BlogRepository;
import example.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllOrderByRedDate() {
        return blogRepository.findByOrderByRedDateAsc();
    }

    public Blog add(Blog blog) throws ServiceException {
        if (blogRepository.existsByUrl(blog.getUrl())) {
            throw new ServiceException("add", "URL not unique");
        }
        return blogRepository.save(blog);
    }

    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    public List<Blog> findByStatus(Status status) {
        return blogRepository.findByStatus(status);
    }

    public void delete(Blog blog){
        blogRepository.delete(blog);
    }

    public void updateStatus(Long blog_id, Status status) {
        blogRepository.updateBlogStatus(blog_id, status);
    }

    public boolean existsByUrl(String url) {
        return blogRepository.existsByUrl(url);
    }

    public List<Object[]> findAllMergedWithMeningAndStatus(Status status) {
        return blogRepository.findAllMergedWithMeningAndStatus(status);
    }
}
