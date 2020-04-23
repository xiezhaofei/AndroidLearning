package com.android.androidlearning.learningcode.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class Book implements Parcelable {
    public String name;

    public Book(String name) {
        this.name = name;
    }

    protected Book(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
