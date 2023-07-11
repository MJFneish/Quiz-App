package quiz_pages.Models;

import java.time.LocalDate;

public class User {
    private String name, email, password, gender;
    private LocalDate date = LocalDate.now();
    private int score = 0;


    public User() {}

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User(String name, String email, String password, String gender, LocalDate date, int score) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.date = date;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score +=10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User: "+name+ "  \nquiz started :" +date+ "  \nScore: "+score+ "/100 pts";
    }
}
