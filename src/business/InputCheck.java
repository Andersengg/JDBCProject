package business;

/**
 * Created by suman on 4/18/2017.
 */
public class InputCheck {
    /**
     * Checks if email is valid
     *
     * @param email email string
     * @return boolean
     */
    public boolean emailverification(String email) {
        boolean result = false;
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; //Checks if the string is something@something.something
        result = email.matches(EMAIL_REGEX);
        return result;

    }

    /**
     * Checks if input is numeric
     *
     * @param number number as double
     * @return boolean
     */
    public boolean isnumeric(String number) {
        try {

            Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a string is empty/blank
     *
     * @param list string input
     * @return booean
     */
    public boolean isEmpty(String list) {
        return list.equals("");
    }

    /**
     * Checks if a string is valid as a password
     *
     * @param pass string input
     * @return booean
     */
    public boolean PasswordCheck(String pass) {

        String passRegex = "^(?=.*[0-9])(?=.*[a-å])(?=.*[A-Å])$";
        /*
^ - start-of-string
(?=.*[0-9]) - a digit must occur at least once
(?=.*[a-z]) - a lower case letter must occur at least once
(?=.*[A-Z]) - an upper case letter must occur at least once
         */
        if (isEmpty(pass) || !pass.matches(passRegex)) {
            return false;
        }

        return true;
    }
    /**
     * Checks if a string is valid as a date
     * @param input string input
     * @return boolean
     */
    public boolean dateInputCheck(String input) {
        String Date_REGEX = "^[2-9]{1}[0-9]{3}-{1}[01]{1}[0-2]{1}-{1}([0-3]{1}[1-9]{1}|[1-3]{1}[0-9]{1})$"; //regex to check against the string
        return input.matches(Date_REGEX);       //returns a boolean value depending on the input and regex
    }

    public boolean timeInputCheck(String input) {
        String Time_REGEX = "^(([0-1]{1}[0-9]{1})|([2]{1}[0-3]{1})):{1}[0-5]{1}[0-9]{1}:{1}[0-5]{1}[0-9]{1}$";  //regex to check against the string
        return input.matches(Time_REGEX);       //returns a boolean value depending on the input and regex
    }
}
