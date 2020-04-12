package com.example.miaosha.controller.vo;

public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephpne;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return this.gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephpne() {
        return this.telephpne;
    }

    public void setTelephpne(String telephpne) {
        this.telephpne = telephpne;
    }
}