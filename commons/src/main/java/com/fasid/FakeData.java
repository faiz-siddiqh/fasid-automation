package com.fasid;

import com.github.javafaker.Faker;

public class FakeData {

    private FakeData() {
        //private constructor to avoid instantiation
    }

    private static ThreadLocal<Faker> faker;

    static {
        faker = ThreadLocal.withInitial(Faker::new);
    }

    public static String randomParagraph() {
        return faker.get().lorem().paragraph(10);
    }

    public static String randomParagraphWithCharLimit(final int limit) {
        final StringBuffer buffer = new StringBuffer();
        do {
            buffer.append(faker.get().lorem().paragraph());
        } while (buffer.toString().length() < limit);

        return buffer.substring(0, limit);
    }

    public static String words() {
        return faker.get().lorem().word();
    }

    public static String title() {
        return faker.get().lorem().sentence();
    }

    public static String artist() {
        return faker.get().artist().name();
    }

    public static String url() {
        return faker.get().country().capital();
    }

    public static String address() {
        return faker.get().address().fullAddress();
    }

    public static String phoneNumber() {
        return faker.get().phoneNumber().phoneNumber();
    }

}
