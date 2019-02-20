package com.storm.Model;

import java.util.Date;
import java.util.List;

public class Result
{
    public String id;
    public Date created_at;
    public Date updated_at;
    public int width;
    public int height;
    public String color;
    public String description;
    public Urls urls;
    public Links links;
    public List<Object> categories;
    public boolean sponsored;
    public Object sponsored_by;
    public Object sponsored_impressions_id;
    public int likes;
    public boolean liked_by_user;
    public List<Object> current_user_collections;
    public User user;
    public List<Tag> tags;
    public List<PhotoTag> photo_tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }

    public Object getSponsored_by() {
        return sponsored_by;
    }

    public void setSponsored_by(Object sponsored_by) {
        this.sponsored_by = sponsored_by;
    }

    public Object getSponsored_impressions_id() {
        return sponsored_impressions_id;
    }

    public void setSponsored_impressions_id(Object sponsored_impressions_id) {
        this.sponsored_impressions_id = sponsored_impressions_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public void setLiked_by_user(boolean liked_by_user) {
        this.liked_by_user = liked_by_user;
    }

    public List<Object> getCurrent_user_collections() {
        return current_user_collections;
    }

    public void setCurrent_user_collections(List<Object> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<PhotoTag> getPhoto_tags() {
        return photo_tags;
    }

    public void setPhoto_tags(List<PhotoTag> photo_tags) {
        this.photo_tags = photo_tags;
    }
}
