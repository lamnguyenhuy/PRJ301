/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author lam
 */
public class Movie {
    private int id;
    private String title;
    private String releasedDate;
    private boolean forAdult;
    private Category category;
    private List<Actor> actors;

    public Movie(String title, String releasedDate, boolean forAdult, Category category) {
        this.title = title;
        this.releasedDate = releasedDate;
        this.forAdult = forAdult;
        this.category = category;
        this.actors = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getReleasedDate() { return releasedDate; }
    public void setReleasedDate(String releasedDate) { this.releasedDate = releasedDate; }
    
    public boolean isForAdult() { return forAdult; }
    public void setForAdult(boolean forAdult) { this.forAdult = forAdult; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
}
