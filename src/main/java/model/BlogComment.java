package model;

import model.enums.BlogCommentType;

import java.time.LocalDateTime;

public class BlogComment {

    private User user;
    private String comment;
    private LocalDateTime createdDate;
    private BlogCommentType blogCommentType;

    public BlogComment(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.createdDate = LocalDateTime.now();
        this.blogCommentType = BlogCommentType.INITIAL;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public BlogCommentType getBlogCommentType() {
        return blogCommentType;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "user=" + user +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", blogCommentType=" + blogCommentType +
                '}';
    }
}
