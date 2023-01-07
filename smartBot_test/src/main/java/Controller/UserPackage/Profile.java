package Controller.UserPackage;

import org.json.JSONObject;

public class Profile {

    private JSONObject name;
    private String country;
    private String city;
    private String firstName;
    private String lastName;
    private JSONObject dob;
    private Integer age;
    private String birthday;
    private JSONObject location;
    private JSONObject picture;
    private String large;
    private String gender;


    public Profile() {
    }

    public JSONObject getName() {
        return name;
    }

    public void setName(JSONObject name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JSONObject getDob() {
        return dob;
    }

    public void setDob(JSONObject dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public JSONObject getLocation() {
        return location;
    }

    public void setLocation(JSONObject location) {
        this.location = location;
    }

    public JSONObject getPicture() {
        return picture;
    }

    public void setPicture(JSONObject picture) {
        this.picture = picture;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name=" + name +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", location=" + location +
                ", picture=" + picture +
                ", large='" + large + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
