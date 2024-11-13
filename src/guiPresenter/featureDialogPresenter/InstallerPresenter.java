package guiPresenter.featureDialogPresenter;

import guiModel.Installer;
import guiView.featureDialog.InstallerDialog;

public class InstallerPresenter  {
    public InstallerPresenter() {
        if(installer.isInstalled()){
          //  installer.showInstallerDialog(installerDialog);
        }
    }



    private InstallerDialog installerDialog = new InstallerDialog();
    private Installer installer = new Installer();
}