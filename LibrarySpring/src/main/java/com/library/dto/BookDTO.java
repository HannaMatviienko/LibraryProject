package com.library.dto;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private AuthorDTO author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    private PublicationDTO publication;

    @Column(nullable = false, name = "year_publication")
    private int yearPublication;

    @Column(nullable = false, name = "number_of")
    private int numberOf;

    @Column(nullable = false)
    private int available;
}