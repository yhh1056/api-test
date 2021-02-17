package com.sample.demo.sss;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Post> posts = Arrays.asList(new Text(), new Picture());
        List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());

        posts.forEach(p -> sns.forEach(s -> p.postOn(s)));

    }
}

interface Post {
    void postOn(SNS sns);
}

class Text implements Post {

    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

class Picture implements Post {

    @Override
    public void postOn(SNS sns) {
        sns.post(this);
    }
}

interface SNS {
    void post(Text text);
    void post(Picture picture);
}

class Facebook implements SNS {
    @Override
    public void post(Text text) {
        System.out.println("text - facebook");
    }

    @Override
    public void post(Picture picture) {
        System.out.println("picture - facebook");
    }
}

class Twitter implements SNS {
    @Override
    public void post(Text text) {
        System.out.println("text - twitter");
    }

    @Override
    public void post(Picture picture) {
        System.out.println("picture - twitter");
    }
}


