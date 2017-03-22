package ru.geel.fastest.mvc.image;

import java.util.Random;

/**
 * Created by ivangeel on 27.02.17.
 */
public class CryptName {

    public char capitalLetter() {
        Random gen = new Random();
        return (char) (gen.nextInt(25) + 65);
    }

    public char smallLetter() {
        Random gen = new Random();
        return (char) (gen.nextInt(25) + 97);
    }

    public char figure() {
        Random gen = new Random();
        return (char) (gen.nextInt(10) + 48);
    }

    public String getCryptedName() {
        String cryptedText = "";
        Random generator = new Random();
        for (int i = 0; i < 35; i++) {
            int key = generator.nextInt(3);
            switch (key) {
                case 0:
                    cryptedText = cryptedText + capitalLetter();
                    break;

                case 1:
                    cryptedText = cryptedText + smallLetter();
                    break;

                case 2:
                    cryptedText = cryptedText + figure();
                    break;

                default:
                    break;

            }
        }
        return cryptedText;
    }
}
