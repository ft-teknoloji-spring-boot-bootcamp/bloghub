package service;

import model.Blog;
import model.BlogComment;
import model.User;
import model.enums.BlogStatus;
import repository.BlogRepository;

import java.util.List;

public class BlogService {

    private BlogRepository blogRepository;
    private UserService userService;

    public BlogService(BlogRepository blogRepository, UserService userService) {
        this.blogRepository = blogRepository;
        this.userService = userService;
    }

    public Blog createBlog(String title, String text, String email) {

        User foundUser = userService.getUserByEmail(email);

        Blog blog = new Blog(title, text, foundUser);

        blogRepository.save(blog);

        return blog;
    }

    public Blog getBlogByTitle(String title) {

        return blogRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("blog bulunamadı"));
    }

    public void addComment(String title, String comment, User user) {

        Blog foundBlog = getBlogByTitle(title);
        //TODO log ekle
        foundBlog.getBlogCommentList().add(new BlogComment(user, comment));

    }

    public List<Blog> getBlogsFilterByStatus(BlogStatus blogStatus, String email) {

        User foundUser = userService.getUserByEmail(email);

        return foundUser.getBlogList().stream()
                .filter(blog -> blogStatus.equals(blog.getBlogStatus()))
                .toList();
    }

    public void changeBlogStatus(BlogStatus blogStatus, String title) {

        Blog foundBlog = getBlogByTitle(title);

        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new RuntimeException("statüsü PUBLISHED olan bir blog silinemez.");
        }

        //TODO log eklenecek
        foundBlog.setBlogStatus(blogStatus);

    }
}
