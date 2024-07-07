import model.Blog;
import model.User;
import model.enums.BlogStatus;
import model.enums.StatusType;
import repository.BlogRepository;
import repository.UserRepository;
import service.BlogService;
import service.UserService;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserService(new UserRepository());

        BlogService blogService = new BlogService(new BlogRepository(), userService);

        userService.saveUser("cem@gmail.com", "password");
        userService.saveUser("mehmet@gmail.com", "123456");
        userService.saveUser("toygun@gmail.com", "123456");

        userService.changeStatus("cem@gmail.com", StatusType.APPROVED);
        userService.changeStatus("mehmet@gmail.com", StatusType.APPROVED);
        userService.changeStatus("toygun@gmail.com", StatusType.APPROVED);

        userService.changeStatus( List.of("cem@gmail.com","toygun@gmail.com"), StatusType.APPROVED);

        userService.getAllUsers().forEach(System.out::println);

        userService.getAllUsers()
                .stream()
                .peek(System.out::println)
                .filter(user -> user.getEmail().equals("cem@gmail.com"))
                .toList();



        List<String> emailList = userService.getAllUsers()
                .stream()
                .map(User::getEmail)
                .toList();

        List<Blog> allUsersBlogList = userService.getAllUsers()
                .stream()
                .flatMap(user -> user.getBlogList().stream())
                .toList();


        Map<String, User> emailUserMap = userService.getAllUsersMap();

        User cemUser = emailUserMap.get("cem@gmail.com");


        User foundUser = userService.getUserByEmail("cem@gmail.com");


        blogService.createBlog("başlık", "içerik", "cem@gmail.com");

        Blog foundBlog = blogService.getBlogByTitle("başlık");

        blogService.addComment("başlık", "çok kötü olmuş", foundUser);


        //-- for ile yapımı

        /*
        List<Blog> userBlogList = user.getBlogList();

        List<Blog> userDraftBlogList = new ArrayList<>();
        List<Blog> userPublishedBlogList = new ArrayList<>();

        for (Blog userBlog : userBlogList) {

            if (BlogStatus.DRAFT.equals(userBlog.getBlogStatus())) {
                userDraftBlogList.add(userBlog);
            } else if (BlogStatus.PUBLISHED.equals(userBlog.getBlogStatus())) {
                userPublishedBlogList.add((userBlog));
            }

        }
        */
        //ödev. kullanıcın takip ettiği tag'lere göre blog'lar gelmeli


        /*
        Optional<Blog> instacioBlog = blogList.stream()
                .filter(blog1 -> blog1.getTitle().equals("Instancio: A New Way to Create Test Data"))
                .findFirst();

        Blog blog1 = instacioBlog.orElseThrow(() -> new RuntimeException("bu isimde bir blog bulunamadı"));

        if (instacioBlog.isEmpty()) {
            System.out.println("bu isimde bir blog bulunamadı");
            throw new RuntimeException("bu isimde bir blog bulunamadı");
        }

        Optional<Blog> willBeDraftBlog = user.getBlogList()
                .stream()
                .filter(blog2 -> blog2.getTitle().equals("Instancio: A New Way to Create Test Data"))
                .findFirst();


        if (willBeDraftBlog.isEmpty()) {
            System.out.println("bu isimde bir blog bulunamadı");
            throw new RuntimeException("bu isimde bir blog bulunamadı");
        }

        if (BlogStatus.PUBLISHED.equals(willBeDraftBlog.get().getBlogStatus())) {
            System.out.println("published olan blog delete edilemez. önce draft yapın.");
            throw new RuntimeException("published olan blog delete edilemez. önce draft yapın.");
        }

        willBeDraftBlog.get().setBlogStatus(BlogStatus.DRAFT);

        */

        blogService.changeBlogStatus(BlogStatus.DELETED, "başlık");


    }
}
