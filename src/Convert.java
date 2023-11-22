public class Convert {
    // Цифры
    private static final String[] numbers = {
            "", "один", "два", "три",
            "четыре", "пять", "шесть",
            "семь", "восемь", "девять"
    };

    // Числа от 10 до 19
    private static final String[] fromTenToNineteen = {
            "десять", "одиннадцать",
            "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать",
            "восемнадцать", "девятнадцать"
    };

    // Десятые числа
    private static final String[] tenth = {
            "", "десять", "двадцать", "тридцать",
            "сорок", "пятьдесят", "шестьдесят",
            "семьдесят", "восемьдесят", "девяносто"
    };

    // Сотые сисла
    private static final String[] hundredth = {
            "", "сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"
    };

    // Цифры для тысячной суммы
    private static final String[] numberThousand = {
            "", "одна", "две", "три",
            "четыре", "пять", "шесть",
            "семь", "восемь", "девять"
    };

    public static String getConvertSum(double sum) {
        String[] rublesAndKopeck = Double.toString(sum).split("\\.");

        return convertRubles(getStringNumber(rublesAndKopeck[0])) + convertKopeck(rublesAndKopeck[1]);
    }

    private static String convertKopeck(String kopeck) {
        String result = "";

        int firstNumber = Integer.parseInt(String.valueOf(kopeck.charAt(0)));

        int number = Integer.parseInt(kopeck);

        switch (kopeck.length()) {
            case 1 -> {
                if (kopeck.charAt(0) == '1') {
                    result += numberThousand[1] + " копейка ";
                } else if (number > 1 && number < 5) {
                    result += numberThousand[number] + " копейки ";
                } else {
                    result += numberThousand[number] + " копеек ";
                }
            }
            case 2 -> {
                if (firstNumber == 0) {
                    result += convertKopeck(kopeck.substring(1));
                    return result;
                }
                if (number < 20) {
                    result += fromTenToNineteen[number - 10] + " копеек ";
                    return result;
                }
                if (number < 100) {
                    result += tenth[firstNumber] + " " + convertKopeck(kopeck.substring(1));
                }
            }
        }

        return result;
    }

    private static String convertRubles(String sum) {
        String result = "";

        String[] splitSum = sum.split("\\.");

        switch (splitSum.length) {
            case 1 -> result += getNumber(splitSum[0]);
            case 2 -> {
                String thousand = splitSum[0];
                result += getThousandString(splitSum[0]) + convertRubles(sum.substring(thousand.length() + 1));
            }
            case 3 -> {
                String million = splitSum[0];
                result += getMillonString(splitSum[0]) + convertRubles(sum.substring(million.length() + 1));
            }
        }

        return result;
    }

    private static String getNumber(String splitSumNumber) {
        String result = "";

        int firstNumber = Integer.parseInt(String.valueOf(splitSumNumber.charAt(0)));

        int number = Integer.parseInt(splitSumNumber);

        if (number == 0) {
            return "рублей ";
        }

        switch (splitSumNumber.length()) {
            case 1 -> {
                if (splitSumNumber.charAt(0) == '1') {
                    result += numbers[1] + " рубль ";
                } else if (number > 1 && number < 5) {
                    result += numbers[number] + " рубля ";
                } else {
                    result += numbers[number] + " рублей ";
                }
            }
            case 2 -> {
                if (firstNumber == 0) {
                    result += getNumber(splitSumNumber.substring(1));
                    return result;
                }
                if (number < 20) {
                    result += fromTenToNineteen[number - 10] + " рублей ";
                    return result;
                }
                if (number < 100) {
                    result += tenth[firstNumber] + " " + getNumber(splitSumNumber.substring(1));
                }
            }
            case 3 -> result += hundredth[firstNumber] + " " + getNumber(splitSumNumber.substring(1));
        }

        return result;
    }

    private static String getThousandString(String splitSumThousand) {
        if (splitSumThousand.equals("000")) {
            return "";
        }

        String result = "";

        int firstNumber = Integer.parseInt(String.valueOf(splitSumThousand.charAt(0)));

        int number = Integer.parseInt(splitSumThousand);

        switch (splitSumThousand.length()) {
            case 1 -> {
                if (splitSumThousand.charAt(0) == '1') {
                    result += numberThousand[1] + " тысяча ";
                } else if (number > 1 && number < 5) {
                    result += numberThousand[number] + " тысячи ";
                } else {
                    result += numberThousand[number] + " тысяч ";
                }
            }
            case 2 -> {
                if (firstNumber == 0) {
                    result += getThousandString(splitSumThousand.substring(1));
                    return result;
                }
                if (number < 20) {
                    result += fromTenToNineteen[number - 10] + " тысяч ";
                    return result;
                }
                if (number < 100) {
                    result += tenth[firstNumber] + " " + getThousandString(splitSumThousand.substring(1));
                }
            }
            case 3 -> result += hundredth[firstNumber] + " " + getThousandString(splitSumThousand.substring(1));
        }
        return result;
    }

    private static String getMillonString(String splitSumMillion) {
        String result = "";

        int firstNumber = Integer.parseInt(String.valueOf(splitSumMillion.charAt(0)));

        int number = Integer.parseInt(splitSumMillion);

        switch (splitSumMillion.length()) {
            case 1 -> {
                if (splitSumMillion.charAt(0) == '1') {
                    result += numbers[1] + " миллион ";
                } else if (number > 1 && number < 5) {
                    result += numbers[number] + " миллиона ";
                } else {
                    result += numbers[number] + " миллионов ";
                }
            }
            case 2 -> {
                if (firstNumber == 0) {
                    result += getMillonString(splitSumMillion.substring(1));
                    return result;
                }
                if (number < 20) {
                    result += fromTenToNineteen[number - 10] + " миллионов ";
                    return result;
                }
                if (number < 100) {
                    result += tenth[firstNumber] + " " + getMillonString(splitSumMillion.substring(1));
                }
            }
            case 3 -> result += hundredth[firstNumber] + " " + getMillonString(splitSumMillion.substring(1));
        }

        return result;
    }

    private static String getStringNumber(String sum) {
        StringBuilder stringBuilder = new StringBuilder(sum);

        stringBuilder.reverse();

        int countNumber = stringBuilder.length();

        if (countNumber > 3) {
            int increaseString = 0; // Увеличение строки

            for (int i = 1; i < countNumber; i++) {
                if ((i % 3) == 0 && (i + increaseString + 1) <= countNumber) {
                    stringBuilder.insert(i + increaseString, ".");
                    countNumber++;
                    increaseString++;
                }
            }
        }

        return stringBuilder.reverse().toString();
    }
}
