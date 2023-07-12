package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "CalendarComment")
public class CalendarComment implements Serializable {

    @Id
    @Column(name = "commentId")
    private String commentId;

    @ManyToOne
    @JoinColumn(name = "calendarId", nullable = false)
    private Calendar calendar;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="calendarId", referencedColumnName="calendarId", insertable = false, updatable = false),
            @JoinColumn(name="entryId", referencedColumnName="entryId", insertable = false, updatable = false)
    })
    private CalendarEntry entry;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private RegisteredUser user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "datePosted", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp datePosted;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
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

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
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
        return commentId.equals(that.commentId) &&
                calendar.equals(that.calendar) &&
                entry.equals(that.entry) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, calendar, entry, user);
    }
}
