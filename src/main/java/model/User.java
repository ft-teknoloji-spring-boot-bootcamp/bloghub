package model;

import model.enums.StatusType;
import model.enums.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User {

    private String name;
    private String email;
    private String password;
    private UserType userType;
    private String bio;
    private StatusType statusType;
    private Set<SocialMedia> socialMediaList;
    private List<Blog> blogList;
    private List<BlogTag> followedTagList = new ArrayList<>();

    public User() {
        userType = UserType.STANDARD;
    }

    /*
    public User(UserType userType) {
        this.userType = userType;
    }*/

    public User(String email, String password) {
        this.userType = UserType.STANDARD;
        this.statusType = StatusType.WAITING_APPROVAL;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Set<SocialMedia> getSocialMediaList() {
        return socialMediaList;
    }

    public void setSocialMediaList(Set<SocialMedia> socialMediaList) {
        this.socialMediaList = socialMediaList;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public List<BlogTag> getFollowedTagList() {
        return followedTagList;
    }

    public void setFollowedTagList(List<BlogTag> followedTagList) {
        this.followedTagList = followedTagList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", bio='" + bio + '\'' +
                ", statusType=" + statusType +
                ", socialMediaList=" + socialMediaList +
                ", blogList=" + blogList +
                ", followedTagList=" + followedTagList +
                '}';
    }
}
