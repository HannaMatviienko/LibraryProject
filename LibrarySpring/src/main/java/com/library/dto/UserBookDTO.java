package com.library.dto;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "user_books")
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class UserBookDTO {

    public UserBookDTO() {
        status = 0;
        location = 0;
        fine = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private UserDTO user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private BookDTO book;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int location;

    @Column(name = "date_take", columnDefinition = "DATETIME")
    private Date dateTake;

    @Column(name = "date_back", columnDefinition = "DATETIME")
    private Date dateBack;

    @Column(name = "date_actual", columnDefinition = "DATETIME")
    private Date dateActual;

    @Column(nullable = false)
    private double fine;

    @Transient
    public long getDays() {
        long diff = dateTake.getTime() - dateActual.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Transient
    public String getStatusClass() {

        switch (status) {
            case 0:
                return "table-warning";
            case 2:
                return "text-secondary";
            default:
                return "";
        }
    }

    @Transient
    public String getStatusDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        switch (status) {
            case 1:
                return dateBack != null ? df.format(dateBack) : null;
            case 2:
                return dateActual != null ? df.format(dateActual) : null;
            default:
                return null;
        }
    }
}