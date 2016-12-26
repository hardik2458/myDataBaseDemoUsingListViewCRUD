package com.example.dadabhagwan.mydatabasedemousinglistviewcrud.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dadabhagwan on 10/24/2016.
 */

public class UserModel implements Parcelable {
private String FirstName;
private String LastName;
private String Age;
private String Qualification;
private int id;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeString(this.Age);
        dest.writeString(this.Qualification);
        dest.writeInt(this.id);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Age = in.readString();
        this.Qualification = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
