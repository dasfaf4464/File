package compiler;

import ide.IDEComponent;
import ide.Mode;
import manager.Keys;
import manager.ManagerCompo;

import java.io.File;

public class CompilerCompo extends IDEComponent {
    public CompilerCompo(File compile, Mode mode) {
        this.fileToCompile = compile;
        setMode(mode);
    }

    public void executeComponent() {
        switch (mode) {
            case Mode.compileC: {
                if(compilerRunner.CompileC(fileToCompile)) {
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
                break;
            }
            case Mode.compileJAVA: {
                if(compilerRunner.CompileJavaClass(fileToCompile)){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
                break;
            }
            case Mode.compileAUTO: {
                if(compilerRunner.CompileAuto(fileToCompile)){
                    setMode(Mode.compileCOMPLETE);
                } else {
                    setMode(Mode.compileNOTCOMPLETE);
                }
            }
        }

    }

    public void showComponent() {
        switch (mode) {
            case Mode.compileNOFILE: {
                compilerViewer.showFileList(); break;
            }
            case Mode.compileHAVEFILE: {
                compilerViewer.showCompileList(fileToCompile.getName(), ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString())+"\\output"); break;
            }
            case Mode.compileCOMPLETE: {
                compilerViewer.showCompiledFile(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output"); break;
            }
            case Mode.compileNOTCOMPLETE: {
                compilerViewer.showCompileError(ManagerCompo.getPropertyValue(Keys.OUTPUT.getKeyString()) + "\\output"); break;
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

    public static File getLastFailedFile() {
        return lastFailedFile;
    }

    public static File getLastsuccessFile(){
        return lastsuccessFile;
    }

    public File fileToCompile;
    public static File lastFailedFile = null;
    public static File lastsuccessFile = null;

    public CompilerRunner compilerRunner = new CompilerRunner();
    public CompilerViewer compilerViewer = new CompilerViewer();
}