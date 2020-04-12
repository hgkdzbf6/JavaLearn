package com.example.miaosha.service.model;

public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephpne;
    private String registerMode;
    private String thirdPartyId;

    private String encryptPassword;

    public String getEncryptPassword() {
        return this.encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

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

    public String getRegisterMode() {
        return this.registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return this.thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

}