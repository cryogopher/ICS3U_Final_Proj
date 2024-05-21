public class Lesson1_StringMethods {
    public static void main(String[] args){
        String school = "Markville SS!";

        // 1) toUpperCase()
        // This method returns a copy of the string, but with
        // all lower cases converted to upper cases
        System.out.println(school.toUpperCase());

        // 2) toLowerCase()
        // This method returns a copy of the string, but with
        // all upper cases converted to lower cases
        System.out.println(school.toLowerCase());

        // 3) charAt(#)
        // this method returns the character of a string at position #
        // *indexing is actually the position of the cursor not the character
        // when we think about which index a character is at, we think about
        // where the cursor has to be in order to be able to type it
        System.out.println(school.charAt(0));
        System.out.println(school.charAt(5));
        System.out.println(school.charAt(11));
        System.out.println(school.charAt(9));
        // index will be out of range if it isn't a positive integer fitting
        // within the domain of length of the string(StringIndexOutOfBounds)

        // 4) indexOf(s)
        // This method returns the index of the FIRST LEFT occurrence
        // of the string/character s  in the string
        // if no such string/character can be found in the string,
        // a value of -1 will be returned
        System.out.println(school.indexOf("v"));
        System.out.println(school.indexOf("v"));
        System.out.println(school.indexOf("ill"));
        System.out.println(school.indexOf("S"));

        // 5) equals(s)
        // This method returns true if the two strings are the same
        System.out.println(school.equals("chow"));
        System.out.println(school.equals(school));
        System.out.println("Hello".equals("hello")); // case sensitive

        // 6) length()
        // This method returns the number of characters in the string
        // the number of characters is also the same as the
        // last cursor position in the string
        System.out.println(school.length());

        // 7) lastIndexOf(s)
        // returns the index in which the string/ character s
        // is found in s, from the right side

        System.out.println(school.lastIndexOf("l"));
        System.out.println(school.lastIndexOf("ville"));
        // if cant find the particular string it will produce -1

        // 8) substring(#)
        // returns a copy of the string starting from cursor position #

        System.out.println(school.substring(6));
        System.out.println(school.substring(13));
        // even though there is no character at index 13, there is still a cursor at that position
        // substring is able to find this position, and will generate and empty string

        /*
        System.out.println(school.substring(-4)); // exception StringIndexOutOfBounds
        */

        // 9) substring(a,b)
        // method overloaded copy of substring(#)
        // returns a copy of the string starting from cursor position a to cursor position b

        System.out.println(school.substring(4,9));
        System.out.println(school.substring(10,12));
        /*
        System.out.println(school.substring(12,10));
        // ending position being lesser than
        // starting position results in index
        //outofboundsexception
        */
        // the difference between b and a is the number of characters in your resulting string


    }
}
