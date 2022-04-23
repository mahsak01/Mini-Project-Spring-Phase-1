package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @SequenceGenerator(
            name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lesson_sequence"
    )
    private Long id;

    @NotNull
    @Size(min = 1 ,max = 255)
    private String lessonName;

    @NotNull
    @Min(1)
    private int unit;

    @ManyToOne
    @JoinColumn
    private College college;

    @ManyToMany
    @JoinTable(
            name = "lesson_professor",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @JsonIgnore
    private Set<Professor> professors ;

    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<StudentLesson> studentLessons ;


    public Lesson(String lessonName, int unit) {
        this.lessonName = lessonName;
        this.unit = unit;
    }

    public Lesson() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<StudentLesson> getStudentLessons() {
        return studentLessons;
    }

    public void setStudentLessons(Set<StudentLesson> studentLessons) {
        this.studentLessons = studentLessons;
    }

    public void addStudentLesson(StudentLesson studentLesson) {
        studentLessons.add(studentLesson);
    }

    public void deleteStudentLesson(StudentLesson studentLesson) {
        studentLessons.remove(studentLesson);
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }
    public void deleteProfessor(Professor professor) {
        professors.remove(professor);
    }

 
    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", unit=" + unit +
                ", professors=" + professors +
                '}';
    }
}
