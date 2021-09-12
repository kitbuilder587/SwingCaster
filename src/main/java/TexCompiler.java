import java.io.*;
import java.util.Scanner;

public class TexCompiler {
    public static void startBuild(String pathToTexFile) {
        try {
            Runtime r = Runtime.getRuntime();
            Process process = new ProcessBuilder("C:\\Program Files\\MiKTeX\\miktex\\bin\\x64\\pdflatex.exe","--output-directory","C:\\res").start();
            Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String nextLine = "";
                        while(nextLine != null){
                            nextLine= reader.readLine();
                            System.out.println(nextLine);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(target);
            thread.start();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(pathToTexFile + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}