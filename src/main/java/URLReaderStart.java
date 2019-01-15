import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
public class URLReaderStart {
    public static void main(String[] args) throws Exception {
        String text = "";
        try {
            URLConnection bc = new URL("https://www.bellevuecollege.edu/courses/exams/").openConnection();
            bc.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader in = new BufferedReader(new InputStreamReader(bc.getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                text += inputLine + "\n";
            }
            in.close();
        }
        catch (Exception e) {
            System.out.println("Failed to connect to URL");
        }


        try {
            // String regex = "<td>(.*?)</td>\\s*<td>(.*?)</td>\\s*<td>(.*?)</td>";
            // String regex = "<h2>(.*?Winter.*?)</h2>";
            String regex = "<h2>.*?Winter.*?</h2>.*";
            //Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            boolean header = true;
            while(matcher.find()) {
                /*
                if (header) {
                    System.out.println(String.format("%1$s", matcher.group(1)));
                    System.out.println();
                    header = false;
                }
                */
                // matcher.group(), matcher.start(), matcher.end()
                System.out.println(String.format("Class Time: %1$s", matcher.group()));
                System.out.println(String.format("Exam Day: %1$s", matcher.group(2)));
                // System.out.println(String.format("Exam Time: %1$s", matcher.group(3)));
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}