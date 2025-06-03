package ra.ss17.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id // đánh dấu là khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng
    private int id;

    private String bookTitle;

    private String author;

    private int price;

    // mã sách
    private String ISBN;

    private String image;
}