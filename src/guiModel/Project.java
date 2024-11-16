package guiModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 프로젝트 정보를 가지고있는 클래스
 */
public class Project {
    private final String projectName;
    private final String path;
    private Properties projectProperties;
    //프로퍼티에서 가져오는 내용
    private String MainClassFile;
    private String JDKFolder;
    private String lastEditFile;
    //프로젝트 공통 적용사항
    public static final String SOURCE = "src";
    public static final String OUTPUT = "out";
    public static final String SOURCELIST = "sourcelist.txt";
    //프로퍼티 키 값
    public static final String MAINCLASS = "mainclassfile";
    public static final String JDK = "projectjdkfolder";
    public static final String LASTEDIT = "lastedit";

    boolean isOpened = false;

    /**
     * @param projectPath 프로젝트 폴더 경로로 프로젝트 인스턴스 생성.
     */
    public Project(String projectPath) {
        path = projectPath;
        projectName = projectPath.substring(projectPath.lastIndexOf("\\") + 1);

        MainClassFile = null;
        isOpened = false;
    }

    /**
     * 오픈된 파일이면 오픈취소
     * 파일 오픈시 프로퍼티 파일에서 값을 가져옴
     * @return 프로젝트 오픈에 성공한다면 트루
     */
    public boolean openProject() {
        if(isOpened) {
            return false;
        }

        SettingManager.loadProperties(projectProperties, new File(path + File.separator + projectName));
            if (projectProperties != null) {
                MainClassFile = projectProperties.getProperty(Project.MAINCLASS);
                JDKFolder = projectProperties.getProperty(Project.JDK);
                isOpened = true;
                return true;
            } else{
                return false;
            }
    }

    /**
     * 프로젝트 닫기
     */
    public void closeProject() {
        this.saveProjectProperties();
        isOpened = false;
    }

    /**
     * 프로젝트 프로퍼티 저장
     * @return 성공적으로 저장시 트루
     */
    public boolean saveProjectProperties() {
        if(isOpened) {
            SettingManager.saveProperties(projectProperties, path + "\\" + projectName + ".properties", null);
            return true;
        }
        return false;
    }

    /**
     * 프로퍼티 값 변경
     * @param Key Project 클래스의 의 상수 이용.
     * @param Values 변경할 값
     */
    public void setProjectProperties(String Key, String Values) {
        SettingManager.setProperty(projectProperties, Key, Values);
    }

    /**
     * 프로젝트 폴더 생성 및 프로젝트 프로퍼티 생성
     * @param projectName 프로젝트 이름
     * @param projectMakePath 프로젝트 폴더를 생성할 폴더
     * @return 생성된 프로젝트, 생성에 실패했다면 null 반환
     */
    public static Project makeNewProjectFolder(String projectMakePath, String projectName) {
        File projectParentFolder = new File(projectMakePath);
        File projectFolder = new File(projectParentFolder + "\\" + projectName);
        Project newProject;

        if(!projectParentFolder.exists()) {
            projectParentFolder.mkdirs();
            projectFolder.mkdirs();
            newProject = new Project(projectFolder.getAbsolutePath());

            newProject.initProject();

            return newProject;
        } else if(!projectFolder.exists()) {
            projectFolder.mkdirs();
            newProject = new Project(projectFolder.getAbsolutePath());

            newProject.initProject();

            return newProject;
        } else {
            return null;
        }
    }

    /**
     * 프로젝트 프로퍼티 파일 생성과 초기화
     * 프로젝트 소스파일, 아웃파일, 소스파일리스트 생성
     * @return 생성성공시 트루
     */
    private boolean initProject() {
        File projectPropertiesFile = new File(path + "\\" + projectName + ".properties");
        Properties newProjectProperties = new Properties();

        File sourceFolder = new File(path + "\\" + Project.SOURCE);
        File outputFolder = new File(path + "\\" + Project.OUTPUT);
        File sourceList = new File(path + "\\" + Project.SOURCELIST);

        if(!projectPropertiesFile.exists()) {
            try {
                projectPropertiesFile.createNewFile();
                newProjectProperties.setProperty(Project.LASTEDIT, "");
                newProjectProperties.setProperty(Project.MAINCLASS, "");
                newProjectProperties.setProperty(Project.JDK, "");

                SettingManager.saveProperties(newProjectProperties, projectPropertiesFile, "!!!!!!##Do Not remove this File##!!!!!!\n!!!!!!##Do Not change File Name##!!!!!!");
                sourceFolder.mkdirs();
                outputFolder.mkdirs();
                sourceList.createNewFile();

            } catch (IOException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 프로젝트 전체 컴파일에 사용될 리스트 파일 업데이트
     */
    public void updateSourceListFolder() {
        File sourceFolder = new File(path + "\\" + Project.SOURCE);
        File sourceListFile = new File(path + "\\" + Project.SOURCELIST);

        ArrayList<File> javaFiles = new ArrayList<>();
        ArrayList<String> javaPaths = new ArrayList<>();

        findAllJavaFiles(sourceFolder, javaFiles);
        for(File javaFile : javaFiles) {
            javaPaths.add(javaFile.getAbsolutePath());
        }

        try {
            FileWriter sourceListFileWriter = new FileWriter(sourceListFile);
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

    public String getOutputFolder() {
        return path + "\\" + Project.OUTPUT;
    }

    public String getSourceListFile() {
        return path + "\\" + Project.SOURCELIST;
    }

    public String getMainClassFile() {
        return MainClassFile;
    }
}
