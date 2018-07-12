package com.example.adinepst.mybabylist.Utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserData {
    @PrimaryKey
    @NonNull
    private String name;
    private String id;
    private String sex;
    private String dateOfBirth;
    private String motherName;
    private String fatherName;
    private String email;
    private String imageUrl;

    public UserData(){}

    @Ignore
    public UserData(String name, String id, String sex, String dateOfBirth, String motherName, String fatherName, String email, String imageUrl) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
