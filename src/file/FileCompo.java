package file;

import ide.IDE;
import ide.IDEComponent;
import ide.Mode;

import java.io.File;

/**
 *
 */
public class FileCompo extends IDEComponent {

    public FileCompo(String sp, Mode m) {
        setMode(m);
        fileRunner.startPoint = sp;
    }

    @Override
    public void executeComponent() {
        switch (mode) {
            case Mode.fileNOFILE, Mode.fileHAVESEL:{
                uploadedFile = null;
                break;
            }
            case Mode.fileHAVEUP: {
                selectedFile = null;
                break;
            }
            case Mode.fileHAVEUPSEL: {
                uploadedFile = selectedFile;
                break;
            }
            case Mode.fileLIST: {
                fileRunner.runFileSearcher();
                childFiles = fileRunner.getListofFile();
            }break;
            case Mode.fileSEL: {
                if(IDE.comInterpreter.getOption() == null){
                    break;
                }
                if(IDE.comInterpreter.getOption().equals("..")) {
                    fileRunner.goParentFolderOfList();
                    childFiles=fileRunner.getListofFile();
                    setMode(Mode.fileLIST);
                } else {
                    String option = IDE.comInterpreter.getOption();
                    File file = new File(fileRunner.startPoint+ "\\" + option);
                    if(file.exists()) {
                        if(file.isFile()) {
                            selectedFile = fileRunner.selectFileOfList(option);
                            if(uploadedFile == null) {
                                setMode(Mode.fileHAVESEL);
                            } else {
                                setMode(Mode.fileHAVEUPSEL);
                            }
                        } else {
                            fileRunner.goChildFolderOfList(option);
                            childFiles = fileRunner.getListofFile();
                            setMode(Mode.fileLIST);
                        }
                    }
                }
            }break;
        }
    }

    @Override
    public void showComponent() {
        switch (mode.getValue()) {
            case 0x21: {
                fileViewer.showFileEmpty(fileRunner.startPoint); break;
            }
            case 0x22: {
                fileViewer.showFileSelected(fileRunner.startPoint, selectedFile); break;
            }
            case 0x23: {
                fileViewer.showFileUP(fileRunner.startPoint, uploadedFile); break;
            }
            case 0x24: {
                fileViewer.showFileUPSelected(fileRunner.startPoint, selectedFile, uploadedFile); break;
            }
            case 0x25: {
                fileViewer.showListFiles(fileRunner.startPoint, childFiles); break;
            }
            case 0x2D: {
                fileViewer.showHavingErrorFile(); break;
            }
            case 0x2E: {
                fileViewer.showManual(); break;
            }
            case 0x2F: {
                fileViewer.showVersion(); break;
            }
        }
    }

    public static File getUploadedFile() {
        return uploadedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    @Override
    public void setMode(Mode m) {//파일 리스트 선택에서 뒤로 갈 때 파일 상태로 다시 돌아가야 함
        int modeValue = m.getValue();
            if(0x20 < modeValue && modeValue <= 0x24) {
                indexMode = m;
            } else if(0x24 < modeValue && modeValue <= 0x28) {
                runableMode = m;
            } else if(0x2C < modeValue && modeValue <= 0x2F) {
                viewingMode = m;
            }
        mode = m;
    }

    public File[] childFiles = null;
    private static File uploadedFile = null;
    public File selectedFile = null;

    public FileRunner fileRunner = new FileRunner();
    public final FileViewer fileViewer = new FileViewer();
}