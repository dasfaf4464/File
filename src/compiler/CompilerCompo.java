package compiler;

import file.FileCompo;
import ide.IDEComponent;
import ide.Mode;
import manager.ManagerCompo;

import java.io.File;

public class CompilerCompo extends IDEComponent {
    public CompilerCompo(Mode mode) {
        setMode(mode);
    }

    public void executeComponent() {
        switch (mode) {
            case Mode.compileC: {
                if(compilerRunner.CompileC(FileCompo.getUploadedFile()) == true){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
                break;
            }
            case Mode.compileJAVA: {
                if(compilerRunner.CompileJava(FileCompo.getUploadedFile()) == true){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
            } break;
        }

    }

    public void showComponent() {
        switch (mode) {
            case Mode.compileNOFILE: {
                compilerViewer.showFileList(); break;
            }
            case Mode.compileHAVEFILE: {
                compilerViewer.showCompileList(FileCompo.getUploadedFile().getName(), ManagerCompo.basicCompileFolder); break;
            }
            case Mode.compileCOMPLETE: {
                compilerViewer.showCompiledFile(ManagerCompo.basicCompileFolder); break;
            }
            case Mode.compileNOTCOMPLETE: {
                compilerViewer.showCompileError(ManagerCompo.basicErrorFolderPath); break;
            }
        }
    }

    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x30 < modeValue && modeValue <= 0x32){
            indexMode = m;
        } else if (0x32 < modeValue && modeValue <= 0x37) {
            runableMode = m;
        } else if (0x3A < modeValue && modeValue <= 0x3F) {
            viewingMode = m;
        }
        mode = m;
    }

    public static File getFailedFile() {
        return failedFile;
    }

    public static File getSuccessFile(){
        return successFile;
    }

    File compileFile = FileCompo.getUploadedFile();
    public static File failedFile = null;
    public static File successFile = null;

    public CompilerRunner compilerRunner = new CompilerRunner();
    public CompilerViewer compilerViewer = new CompilerViewer();
}