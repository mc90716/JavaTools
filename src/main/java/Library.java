import java.io.*;

public class Library {

    public static void main(String[] args) throws Exception {
        File file = new File("D:/hinj");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = br.readLine();
        String[] strArr = line.split("\001");
        System.out.println(strArr[0]);
        System.out.println(strArr[1]);
        System.out.println(strArr[2]);
        br.close();
    }

}
