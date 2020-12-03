package HomeWork7;

import java.io.*;
import java.util.Map;

public class ToFile {
    static String writePath = null;
    static Map<String,Double> resultMap;
    static String filename = File.separator+"currency.txt";
    public void writeToFile(String path, SiteLoader loader) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
            path = line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!path.isBlank()) {
            try {
                File file=new File(path+filename);
                FileOutputStream fos=new FileOutputStream(file);
                PrintWriter pw=new PrintWriter(fos);

                for(Map.Entry<String, Double> m :resultMap.entrySet()){
                    pw.println(m.getKey()+"="+m.getValue());
                }

                pw.flush();
                pw.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            line = "c:\\Users\\sturchenko.a\\IdeaProjects\\HomeWork\\src\\HomeWork7\\";
            try {
                File file=new File(line+filename);
                FileOutputStream fos=new FileOutputStream(file);
                PrintWriter pw=new PrintWriter(fos);

                for(Map.Entry<String, Double> m :resultMap.entrySet()){
                    pw.println(m.getKey()+"="+m.getValue());
                }

                pw.flush();
                pw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writePath = line;
    }
}
