package com.project.springboot.plantidati.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CalendarComment")
public class CalendarComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_Id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "calendar_Id", nullable = false)
    private Calendar calendar;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "calendar_Id", referencedColumnName = "calendar_Id", insertable = false, updatable = false),
            @JoinColumn(name = "entry_Id", referencedColumnName = "entry_Id", insertable = false, updatable = false)
    })
    private CalendarEntry entry;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_posted", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp datePosted;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public CalendarEntry getEntry() {
        return entry;
    }

    public void setEntry(CalendarEntry entry) {
        this.entry = entry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarComment that = (CalendarComment) o;
        return commentId == that.commentId &&
                calendar.equals(that.calendar) &&
                entry.equals(that.entry) &&
                user.equals(that.user);
    }


    @Override
    public int hashCode() {
        return Objects.hash(commentId, calendar, entry, user);
    }
}
