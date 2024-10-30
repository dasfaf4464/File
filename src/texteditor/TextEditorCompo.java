package texteditor;

import ide.IDEComponent;
import ide.Mode;
import java.io.File;
import java.util.LinkedList;

/**
 * 컴파일러에서 에러파일 읽는 것과 업로드 된 파일 읽는것 구분
 */
public class TextEditorCompo extends IDEComponent {

    public TextEditorCompo(String readFile, Mode mode) {
        setMode(mode);
        editingFile = new File(readFile);
    }

    public TextEditorCompo(File readFile, Mode mode) {
        setMode(mode);
        editingFile = readFile;
    }

    public void executeComponent() {
        switch (mode) {
            case Mode.textREAD: fileLine = textEditorRunner.readFile(editingFile); break;
            case Mode.textWRITE: break;
            case Mode.textBOTH: break;
        }
    }

    public void showComponent() {
        switch (mode) {
            case Mode.textNOFILE: break;
            case Mode.textREAD: textEditorViewer.showReadingFile(fileLine); break;
        }
    }

    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x60 < modeValue && modeValue <= 0x62) {
            indexMode = m;
        } else if (0x62< modeValue && modeValue <= 0x65) {
            runableMode = m;
        } else if (0x6C < modeValue && modeValue <= 0x6F) {
            viewingMode = m;
        }
        mode = m;
    }

    public File editingFile = null;
    public LinkedList<String> fileLine = new LinkedList<>();

    public TextEditorRunner textEditorRunner = new TextEditorRunner();
    public TextEditorViewer textEditorViewer = new TextEditorViewer();
}