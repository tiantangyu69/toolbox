import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjlizhitao on 2016/10/21.
 */
public class PatternTest {
    @Test
    public void test() {
        Pattern pattern = Pattern.compile("\\w+");
        System.out.println(pattern.matcher("sdfsdfsdf^^^sdf").matches());
    }

    @Test
    public void testSubPattern() {
        String testStr = "Please send main to lizhitao123@126.com. Thanks!";

        Pattern pattern = Pattern.compile("(\\w+)@(\\w+)\\.(\\w+)");

        Matcher matcher = pattern.matcher(testStr);

        matcher.find();

        System.out.println(matcher.group());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
    }
}
