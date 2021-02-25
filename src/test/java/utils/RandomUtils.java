package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public static Long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomPhone() {
        return getRandomLong(1000000000L, 9999999999L)+"";
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        int index = getRandomInt(0, genders.length - 1);

        return genders[index];
    }

    public static String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        int index = getRandomInt(0, months.length - 1);

        return months[index];
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Reading", "Sports", "Music"};
        int index = getRandomInt(0, hobbies.length - 1);

        return hobbies[index];
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Chemistry", "English", "Commerce", "Economics",
                "Social Studies", "Computer Science", "Hindi", "Arts", "History",
                "Accounting", "Physics", "Biology" };
        int index = getRandomInt(0, subjects.length - 1);

        return subjects[index];
    }

    public static String[] getRandomStateAndCity() {
        String[] stateAndCity = new String[2];
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        String[] ncrCities = {"Delhi", "Gurgaon", "Noida"};
        String[] upCities = {"Agra", "Lucknow", "Merrut"};
        String[] harCities = {"Karnal", "Panipat"};
        String[] rajCities = {"Jaipur", "Jaiselmer"};
        int stateIndex = getRandomInt(0, states.length - 1);
        int cityIndex;

        switch (stateIndex) {
            case 0:
                cityIndex = getRandomInt(0, ncrCities.length - 1);
                stateAndCity[0]=states[stateIndex];
                stateAndCity[1]=ncrCities[cityIndex];
            case 1:
                cityIndex = getRandomInt(0, upCities.length - 1);
                stateAndCity[0]=states[stateIndex];
                stateAndCity[1]=upCities[cityIndex];
            case 2:
                cityIndex = getRandomInt(0, harCities.length - 1);
                stateAndCity[0]=states[stateIndex];
                stateAndCity[1]=harCities[cityIndex];
            case 3:
                cityIndex = getRandomInt(0, rajCities.length - 1);
                stateAndCity[0]=states[stateIndex];
                stateAndCity[1]=rajCities[cityIndex];
        }

        return stateAndCity;
    }
}
