package index;

import file.FileCompo;
import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import runner.RunnerCompo;

public class IndexCompo extends IDEComponent {

    public IndexCompo(Mode m) {
        super(m);
    }

    @Override
    public void executeComponent() {

    }

    @Override
    public void showComponent() {
        switch (mode.getValue()) {
            case 0x11:{
                indexViewer.showStartProgram(); break;
            }
            case 0x12:{
                indexViewer.showHavingFile(FileCompo.getUploadedFile()); break;
            }
            case 0x1D:{
                indexViewer.showError(1); break;
            }
            case 0x1E:{
                indexViewer.showManual(); break;
            }
            case 0x1F:{
                indexViewer.showVersion(); break;
            }
        }
    }

    /**
     * in runnable mode, if changeMode is viewing
     * @param m to set mode
     */
    @Override
    public void setMode(Mode m) {
        int modeValue = m.getValue();
        if(0x10 < modeValue && modeValue <= 0x12) {
            indexMode = m;
        } else if(0x1C < modeValue && modeValue <= 0x1F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.indNOFILE)){
            switch (command) {
                case "5", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        } else if (mode.equals(Mode.indHAVEFILE)) {
            switch (command) {
                case "3": IDE.compoCaller.callComponent(new RunnerCompo(FileCompo.getUploadedFile(), Mode.runHAVEFILE)); break;
                case "7", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        } else if (mode.equals(Mode.indHELP) || mode.equals(Mode.indVER) || mode.equals(Mode.indERROR)) {
            switch (command) {
                case "back": setMode(indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": setMode(Mode.indHELP); break;
                case "version": setMode(Mode.indVER); break;
            }
        }
    }

    public final IndexViewer indexViewer = new IndexViewer();
    public final IndexRunner indexRunner = new IndexRunner();
}