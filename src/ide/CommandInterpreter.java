package ide;

import compiler.CompilerCompo;
import file.FileCompo;
import index.IndexCompo;
import manager.ManagerCompo;
import runner.RunnerCompo;
import texteditor.TextEditorCompo;

import java.util.Scanner;

/**
 *  Command Interpreter have command line.
 *  Command Interpreter can get command and interpret command.
 */
public class CommandInterpreter {
    public CommandInterpreter() {

    }

    /**
     * tokenizing by '-'
     */
    protected void getCommandLine() {
        commandLine = null;
        String[] tmp;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(": ");
            commandLine = scanner.nextLine();
            if(commandLine.contains("-")) {
                tmp = commandLine.split("-");
                command = tmp[0].toLowerCase();
                if(tmp.length == 2)
                    option = tmp[1];
            } else {
                command = commandLine.toLowerCase();
                option = null;
            }
        } while (!isValidCommand());
    }

    public String getOption() {
        return option;
    }

    /**
     * 1-9, and IDE common command are valid.
     * developer can add or delete commands through switch-case statement.
     * @return true if valid, false if invalid.
     */
    private boolean isValidCommand() {
        if (commandLine == null || commandLine.isEmpty()) {
            System.out.println("Didn't enter any command.");
            return false;
        } else if (command.length() == 1 && '0' < command.charAt(0) && command.charAt(0) <= '9' && option == null) {
            return true;
        } else if ('a' <= command.charAt(0) && command.charAt(0) <= 'z' || 'A'<= command.charAt(0) && command.charAt(0) <= 'Z') {
            return switch (command.toLowerCase()) {
                case "help", "exit", "version","back",
                     "set", "list", "gcc", "jdk", "run", "read",
                     "upload", "select", "delete" -> true;
                default -> false;
            };
        } else {
            System.out.println("Invalid command");
            return false;
        }
    }

    protected void interpretCommand(IDEComponent component) {
        if (component instanceof IndexCompo) {
            interpretIndex(component);
        } else if (component instanceof FileCompo) {
            interpretFile(component);
        } else if (component instanceof CompilerCompo) {
            interpretCompiler(component);
        } else if (component instanceof RunnerCompo) {
            interpretRunner(component);
        } else if (component instanceof TextEditorCompo) {
            interpretTextEditor(component);
        } else if (component instanceof ManagerCompo) {
            interpretManager(component);
        }
    }

    private void interpretIndex(IDEComponent component) {
        if(component.mode.equals(Mode.indNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicFileStarting, Mode.fileNOFILE)); break;
                case "2": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicErrorFolderPath, Mode.fileLIST)); break;
                //case '3': IDE.compoCaller.callComponent(new TextEditorCompo()); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHAVEFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicFileStarting, Mode.fileHAVEUP)); break;
                case "2": IDE.compoCaller.callComponent(new CompilerCompo(Mode.compileHAVEFILE)); break;
                case "3": IDE.compoCaller.callComponent(new RunnerCompo(FileCompo.getUploadedFile(), Mode.runHAVEFILE)); break;
                case "4": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicErrorFolderPath, Mode.fileLIST)); break;
                case "5": IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile(), Mode.textHAVE)); break;
                case "6": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "7", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        } else if (component.mode.equals(Mode.indHELP) || component.mode.equals(Mode.indVER) || component.mode.equals(Mode.indERROR)) {
            switch (command) {
                case "back": component.setMode(component.indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.indHELP); break;
                case "version": component.setMode(Mode.indVER); break;
            }
        }
    }

    private void interpretFile(IDEComponent component) {
        if(component.mode.equals(Mode.fileNOFILE)){
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indNOFILE); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileLIST)) {
            switch (command) {
                case "1", "back": component.setMode(component.indexMode); break;
                case "select": component.setMode(Mode.fileSEL); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        } else if(component.mode.equals(Mode.fileHAVESEL)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileHAVEUPSEL); break;
                case "3": /*component.setMode(Mode.fileDEL);*/ break;
                case "4": /*IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile()));*/ break;
                case "5": /*component.setMode(Mode.fileMAKE);*/ break;
                case "6", "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUPSEL)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileHAVESEL); break;
                case "3", "delete": /*component.setMode(Mode.fileDEL);*/ break;
                case "4": /*IDE.compoCaller.callComponent(new TextEditorCompo(FileCompo.getUploadedFile()));*/ break;
                case "5": /*component.setMode(Mode.fileMAKE);*/ break;
                case "6", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if (component.mode.equals(Mode.fileHAVEUP)) {
            switch (command) {
                case "1", "list": component.setMode(Mode.fileLIST); break;
                case "2", "upload": component.setMode(Mode.fileNOFILE); break;
                case "3": /*component.setMode(Mode.fileMAKE);*/ break;
                case "4", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
                case "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
            }
        } else if(component.mode.equals(Mode.fileHELP) || component.mode.equals(Mode.fileVER) || component.mode.equals(Mode.fileERROR)) {
            switch (command) {
                case "back": component.setMode(component.indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        }
    }

    private void interpretCompiler(IDEComponent component) {
        if(component.mode.equals(Mode.compileNOFILE)) {
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicFileStarting, Mode.fileLIST)); break;
                case "2", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if(component.mode.equals(Mode.compileHAVEFILE)) {
            switch (command) {
                case "1": component.setMode(Mode.compileC); break;
                case "2": component.setMode(Mode.compileJAVA); break;
                case "3": component.setMode(Mode.compileAUTO); break;
                case "4", "set": IDE.compoCaller.callComponent(new ManagerCompo(Mode.managerHAVE)); break;
                case "5", "exit": IDE.compoCaller.returnComponent(Mode.indHAVEFILE); break;
            }
        } else if (component.mode.equals(Mode.compileCOMPLETE)) {
            switch (command) {
                case "1", "run" : IDE.compoCaller.callComponent(new RunnerCompo(CompilerCompo.getSuccessFile(), Mode.runJAVA)); break;
                case "2", "back": component.setMode(component.indexMode); break;
            }
        } else if (component.mode.equals(Mode.compileNOTCOMPLETE)) {
            switch (command) {
                case "1", "read": IDE.compoCaller.callComponent(new TextEditorCompo(CompilerCompo.getFailedFile(), Mode.textREAD)); break;
                case "2", "back": component.setMode(component.indexMode); break;
            }
        } else if(component.mode.equals(Mode.compileHELP) || component.mode.equals(Mode.compileVER) || component.mode.equals(Mode.compileERROR)) {
            switch (command) {
                case "back": component.setMode(component.indexMode); break;
                case "exit": IDE.compoCaller.returnComponent(); break;
                case "help": component.setMode(Mode.fileHELP); break;
                case "version": component.setMode(Mode.fileVER); break;
            }
        }

    }

    private void interpretRunner(IDEComponent component) {
        if(component.mode.equals(Mode.runHAVEFILE)){
            switch (command) {
                
            }
        } if(component.mode.equals(Mode.runJAVA)) {
            switch (command){
                case "exit": IDE.compoCaller.returnComponent(); break;
            }
        }

    }

    private void interpretTextEditor(IDEComponent component) {
        if(component.mode.equals(Mode.textNOFILE)){
            switch (command) {
                case "1": IDE.compoCaller.callComponent(new FileCompo(ManagerCompo.basicFileStarting, Mode.fileLIST)); break;
                case "read", "2": component.setMode(Mode.textREAD);
                case "3", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } else if (component.mode.equals(Mode.textREAD)) {
            switch (command) {
                case "back": component.setMode(component.indexMode);break;
                case "exit": IDE.compoCaller.returnComponent(); break;
            }
        }
    }

    private void interpretManager(IDEComponent component) {
        if(component.mode.equals(Mode.managerHAVE)) {
            switch (command) {

            }
        }
    }

    private String commandLine;
    private String command;
    private String option;
}