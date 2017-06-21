import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * @param argv
     * @throws FileNotFoundException
     */
    public static void main(String[] argv) throws FileNotFoundException {
        long begin = System.currentTimeMillis();
        //        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        Scanner sc = new Scanner(System.in);
        //        System.setOut(new PrintStream(new FileOutputStream(new File(WebPath.getAbsolutePathWithClass().getPath() + "output.txt"))));
        //presolve
        //input
        int n = sc.nextInt();
        int t = n;
        StringBuilder sb = new StringBuilder("");
        while (t-- > 0) {
            sb.append(sc.next());
        }
        String str = sb.toString();
        String rev = sb.reverse().toString();

        //resolve
        StringBuilder ans = new StringBuilder("");
        int idx = 0, idx2 = 0;
        while (idx + idx2 < n) {
            int cmp = str.substring(idx).compareTo(rev.substring(idx2));
            if (cmp < 0) {
                ans.append(str.charAt(idx++));
            } else {
                ans.append(rev.charAt(idx2++));
            }
        }

        //output
//        for (char c : ans.toString().toCharArray()) {
//            System.out.println(c);
//        }
        System.out.println(ans);

        sc.close();
        System.err.println("use time: " + (System.currentTimeMillis() - begin) / 1000.0 + " seconds.");
    }
}