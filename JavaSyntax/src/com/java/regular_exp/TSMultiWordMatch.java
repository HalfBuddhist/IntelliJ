import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TSMultiWordMatch {
    public static void main(String args[]) {
        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);

        String candidateString = "Java. java JAVA jAVA";

        Matcher matcher = p.matcher(candidateString);

        // display the latter match
        System.out.println(candidateString);
        matcher.find(11);
        System.out.println(matcher.group());

        // display the earlier match
        System.out.println(candidateString);
        matcher.find(0);
        System.out.println(matcher.group());


        p = Pattern.compile("(.*)(freq|ratio)(.*)", Pattern.CASE_INSENSITIVE);

        candidateString = "day_avg_grp_a_freq_chilled_primary_pump";

        matcher = p.matcher(candidateString);
        // display the earlier match
        System.out.println(candidateString);
        matcher.find(0);
        System.out.println(matcher.group());
    }
}