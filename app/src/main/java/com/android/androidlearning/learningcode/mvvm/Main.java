package com.android.androidlearning.learningcode.mvvm;

import java.util.List;

public class Main {

    class Data {
        public String value;
        public List<Data> list;
    }


    public Data findC(List<Data> list) {
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            Data data = list.get(i);
            if ("C".equals(data.value)) {
                return data;
            } else {
                return findC(data.list);
            }
        }
        return null;
    }

}
