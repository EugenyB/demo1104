package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class Student implements Serializable {
    private int id;
    private String name;
    private double rating;
}