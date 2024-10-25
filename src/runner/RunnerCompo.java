package runner;

import compiler.CompilerCompo;
import ide.IDEComponent;
import ide.Mode;
import java.io.File;

public class RunnerCompo extends IDEComponent {
    public RunnerCompo(File file, Mode mode) {
        RunnerCompo.fileToRun = file;
        System.out.println(RunnerCompo.fileToRun);
        setMode(mode);
    }

    public void executeComponent() {
        switch (mode) {
            case Mode.runC: break;
            case Mode.runJAVA: {
                RunnerCompo.fileToRun = CompilerCompo.getSuccessFile();
                System.out.println("fileToRun");
                runnerRunner.runJava(fileToRun);
                setMode(Mode.runHAVEFILE);
                break;
            }
            case Mode.runSIC: break;
            case Mode.runAUTO: break;
        }
    }

    public void showComponent() {

    }

    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x40 < modeValue && modeValue <= 0x42){
            indexMode = m;
        } else if (0x42 < modeValue && modeValue <= 0x46) {
            runableMode = m;
        } else if(0x4D < modeValue && modeValue <= 0x4F){
            viewingMode = m;
        }
        mode = m;
    }

    public static File fileToRun = null;

    public RunnerRunner runnerRunner = new RunnerRunner();
    public RunnerViewer runnerViewer = new RunnerViewer();
}