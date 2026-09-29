package com.filmjury.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "evaluations",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"film_id", "jury_id"}
                )
        }
)
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "jury_id", nullable = false)
    private User jury;

    private Integer storyScore;

    private Integer directionScore;

    private Integer actingScore;

    private Integer cinematographyScore;

    private Integer editingScore;

    private Integer soundScore;

    private Integer creativityScore;

    private Integer overallScore;

    private Double totalScore;

    @Column(length = 2000)
    private String comments;

    public Evaluation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getJury() {
        return jury;
    }

    public void setJury(User jury) {
        this.jury = jury;
    }

    public Integer getStoryScore() {
        return storyScore;
    }

    public void setStoryScore(Integer storyScore) {
        this.storyScore = storyScore;
    }

    public Integer getDirectionScore() {
        return directionScore;
    }

    public void setDirectionScore(Integer directionScore) {
        this.directionScore = directionScore;
    }

    public Integer getActingScore() {
        return actingScore;
    }

    public void setActingScore(Integer actingScore) {
        this.actingScore = actingScore;
    }

    public Integer getCinematographyScore() {
        return cinematographyScore;
    }

    public void setCinematographyScore(Integer cinematographyScore) {
        this.cinematographyScore = cinematographyScore;
    }

    public Integer getEditingScore() {
        return editingScore;
    }

    public void setEditingScore(Integer editingScore) {
        this.editingScore = editingScore;
    }

    public Integer getSoundScore() {
        return soundScore;
    }

    public void setSoundScore(Integer soundScore) {
        this.soundScore = soundScore;
    }

    public Integer getCreativityScore() {
        return creativityScore;
    }

    public void setCreativityScore(Integer creativityScore) {
        this.creativityScore = creativityScore;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}