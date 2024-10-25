package manager;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 */
public class ManagerRunner {

    /**
     * check having "setting.txt" in program folder.
     * @param settingPath is the folder where IDE is installed at.
     */
    public void checkSettingFile(String settingPath) {
        File file = new File(settingPath+"\\setting.txt");
        if(!file.exists()) {
            try{
                file.createNewFile();
                installIDE();
            } catch (IOException e) {
                System.out.println("Error occur while making setting file");
            }
        }
    }

    public void installIDE() {

    }

    /**
     * @param settingPath is the folder where IDE is installed at.
     * @return all statements of setting file
     */
    public LinkedList<String> getSettingFile(String settingPath) {
        LinkedList<String> file = new LinkedList<>();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(settingPath + "\\setting.txt"));
            while((line = br.readLine()) != null) {
                file.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error occur while have finding setting file");
        }

        return file;
    }

    /**
     * @param settingFile is List of lines.
     * @param category is setting category such as JDK, GCC.
     * @return List of lines which are under the category.
     */
    public LinkedList<String> getLinesOfCategory(LinkedList<String> settingFile, String category) {
        Iterator<String> lineIterator = settingFile.iterator();
        LinkedList<String> lines = new LinkedList<>();

        while (lineIterator.hasNext()) {
            if (lineIterator.next().equals("[" + category + "]")) {
                while(lineIterator.hasNext()) {
                    lines.add(lineIterator.next());
                    if(lineIterator.next().contains("[")){
                        break;
                    }
                }
                break;
            }
        }

        return lines;
    }

    public void tokenizePropertyAndOption(String options, LinkedList<String> category) {
        StringTokenizer st = new StringTokenizer(category.peek(), "=");
    }
}