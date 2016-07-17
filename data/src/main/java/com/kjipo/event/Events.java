package com.kjipo.event;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Events {
    @Id
    @GeneratedValue
    private long id;

    private long logId;

    private long start;

    private long stop;


    Events() {
        // For use with JPA

    }

    public Events(long id, long logId, long start, long stop) {
        this.id = id;
        this.logId = logId;
        this.start = start;
        this.stop = stop;
    }


    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        if (id != events.id) return false;
        if (logId != events.logId) return false;
        if (start != events.start) return false;
        return stop == events.stop;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (logId ^ (logId >>> 32));
        result = 31 * result + (int) (start ^ (start >>> 32));
        result = 31 * result + (int) (stop ^ (stop >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", logId=" + logId +
                ", start=" + start +
                ", stop=" + stop +
                '}';
    }

}
