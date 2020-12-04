package HomeWork7;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ToFile implements IWriteToFile {
    private static String writePath = null;
    static Map<String,Double> resultMap;
    private static String filename = File.separator+"currency.txt";
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String line = null;
    HashMap<String,Double> mapInFile;
    File file;
    void writeToFile(String path, SiteLoader loader) {
        try {
            line = reader.readLine();
            path = line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!path.isBlank()) {
            if(file.length()==0) {
                writeToCollection(line);
            } else {
                writeToCollection(path);
                for(Map.Entry<String,Double> entry : mapInFile.entrySet()) {
                    resultMap.putIfAbsent(entry.getKey(),entry.getValue());
                    writeToCollection(line);
                }
            }
        }
        else {
            line = "C:\\Users\\sturchenko.a\\IdeaProjects\\HomeWork\\src\\HomeWork7";
            writeToCollection(line);
        }
        writePath = line;
    }

    @Override
    public void writeToCollection(String word) {
        try {
            file=new File(word+filename);
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

    @Override
    public void readFromCollection(String word) {
        File toRead=new File(word+filename);
        FileInputStream fis= null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(toRead);
            ois = new ObjectInputStream(fis);
            mapInFile = (HashMap<String,Double>)ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
