package com.android.androidlearning.learningcode.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiezhaofei on 2019-10-31
 * <p>
 * Describe:
 */
public class Book implements Parcelable {
    protected Book(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
