package GUIInterface;

import java.io.File;

public interface Observer {
    abstract void newFileUpdate(File newFile);
    abstract void fileSelectedUpdate(File file);
}