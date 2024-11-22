package guiModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * 프로젝트 정보를 가지고있는 클래스
 */
public class Project {
    private final static String projectListFilePath = Installer.idePropFolder + Installer.projectListFileName;
    private final static String jdkListFilePath = Installer.idePropFolder + Installer.JDKListFileName;

    private String projectName;
    private String path;
    private Properties projectProperties;
    private ArrayList<File> lastOpenedFiles;

    private Project() {
    }

    /**
     * 프로젝트 폴더 및 구성요소 생성(소스리스트파일, 아웃폴더, 소스폴더, 프로퍼티)
     * 프로젝트 초기화(jdk리스트 프로퍼티에 가져오고 프로퍼티 키값 생성, 프로젝트 리스트에 등록, 프로젝트 )
     * @param projectName 프로젝트 이름
     * @param projectMakePath 프로젝트 폴더를 생성할 폴더
     * @return 생성된 프로젝트, 생성에 실패했다면 null 반환
     */
    public static Project makeProject(String projectMakePath, String projectName) {
        File projectFile = new File(projectMakePath + "\\" + projectName);
        projectFile.mkdirs();

        try{
            new File(projectFile.getAbsolutePath() + "\\" + projectName + ".properties").createNewFile();
            new File(projectFile.getAbsolutePath() + "\\out").mkdirs();
            new File(projectFile.getAbsolutePath() + "\\src").mkdirs();
            new File(projectFile.getAbsolutePath() + "\\srclist.txt").createNewFile();
            new File(projectFile.getAbsolutePath() + "\\log.txt").createNewFile();

        } catch (IOException e) {
            return null;
        }

        File propertiesFile = new File(projectFile.getAbsolutePath() + "\\" + projectName + ".properties");
        Properties projectProp = new Properties();
        Properties jdkProp = new Properties();
        Properties ideProp = new Properties();
        Properties projectListProp = new Properties();

        PropertiesUtil.loadProperties(projectProp, propertiesFile);
        PropertiesUtil.loadProperties(jdkProp, new File(jdkListFilePath));
        PropertiesUtil.loadProperties(ideProp, new File(Installer.idePropFolder + Installer.idePropName));
        PropertiesUtil.loadProperties(projectListProp, new File(projectListFilePath));

        projectListProp.setProperty(projectName, projectMakePath + "\\" + projectName);
        PropertiesUtil.saveProperties(projectListProp, new File(projectListFilePath), null);

        projectProp.setProperty("jdk", ideProp.getProperty("basicjdk"));
        projectProp.setProperty("mainclass", "");
        projectProp.setProperty("lastedit", "");
        PropertiesUtil.saveProperties(projectProp, propertiesFile, null);

        return Project.open(projectName);
    }

    /**
     * 프로젝트 이름으로 리스트에서 가져오기
     * 프로젝트 폴더에서 프로퍼티 가져오기
     * @return 가져온 프로젝트 반환
     */
    public static Project open(String Name) {
        Project openedProject = new Project();

        Properties projListProp = new Properties();
        PropertiesUtil.loadProperties(projListProp, new File(projectListFilePath));

        String ProjectPath = projListProp.getProperty(Name);
        openedProject.projectProperties = new Properties();
        openedProject.path = ProjectPath;
        openedProject.projectName = Name;
        openedProject.lastOpenedFiles = new ArrayList<>();
        PropertiesUtil.loadProperties(openedProject.projectProperties, new File(openedProject.path + "\\" + openedProject.projectName + ".properties"));

        String lastEdited = openedProject.projectProperties.getProperty("lastedit");
        StringTokenizer edit = new StringTokenizer(lastEdited, ",");

        while(edit.hasMoreTokens()) {
            if(new File(edit.nextToken()).exists()) {
                openedProject.lastOpenedFiles.add(new File(edit.nextToken()));
            }
        }

        return openedProject;
    }

    /**
     * 프로젝트 프로퍼티 변경
     */
    public void changeJDK(String jdkName) {
        Properties jdkProp = new Properties();
        PropertiesUtil.loadProperties(jdkProp, new File(jdkListFilePath));
        String jdkPath = jdkProp.getProperty(jdkName);
        
        projectProperties.setProperty("jdk", jdkPath);
        PropertiesUtil.saveProperties(projectProperties, new File(path + "\\" + projectName + ".properties"), null);
    }

    /**
     * 프로젝트 메인 클래스 변경
     * @param mainClassName
     */
    public void changeMainClass(String mainClassName) {
        projectProperties.setProperty("mainclass", mainClassName);
        PropertiesUtil.saveProperties(projectProperties, new File(path + "\\" + projectName + ".properties"), null);
    }

    /**
     * 프로젝트 전체 컴파일에 사용될 리스트 파일 업데이트
     */
    public void updateSourceListFolder() {
        File sourceFolder = new File(this.getSourceFolder());
        File sourceListFile = new File(this.getSourceListFile());

        ArrayList<File> javaFiles = new ArrayList<>();
        ArrayList<String> javaPaths = new ArrayList<>();

        findAllJavaFiles(sourceFolder, javaFiles);
        for(File javaFile : javaFiles) {
            javaPaths.add(javaFile.getAbsolutePath());
        }

        try (FileWriter sourceListFileWriter = new FileWriter(sourceListFile)){
            for(String javaPath : javaPaths) {
                sourceListFileWriter.write(javaPath+"\n");
            }
        } catch (FileNotFoundException e){
            //저장할 파일이 없음
        } catch (IOException e) {
            //파일 입력 오류
        }
    }

    /**
     * 확장자가 .java인 파일을 리스트에 저장
     * @param folder .java 파일을 찾을 파일
     * @param javaFiles .java파일을 저장할 리스트
     */
    private void findAllJavaFiles(File folder, ArrayList<File> javaFiles) {
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            for(File file : listOfFiles) {
                if(file.isFile() && file.getName().endsWith(".java")) {
                    javaFiles.add(file);
                } else if(file.isDirectory()) {
                    findAllJavaFiles(file, javaFiles);
                }
            }
        }
    }

    public String getMainClass () {
        return projectProperties.getProperty("mainclass");
    }

    public String getSourceFolder() {
        return path + "\\src";
    }

    public String getOutputFolder() {
        return path + "\\out";
    }

    public String getSourceListFile() {
        return path + "\\srclist.txt";
    }

}
