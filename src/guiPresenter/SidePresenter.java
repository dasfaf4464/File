package guiPresenter;

import GUIInterface.SideInterface;
import guiView.SidePanel;

import javax.swing.tree.TreeNode;

public class SidePresenter implements SideInterface.SidePresenterInterface {
    public SidePresenter(SidePanel sideLinkPanel) {
        sidePanel = sideLinkPanel;
    }
    @Override
    public void treeNodeClicked(TreeNode node) {

    }

    @Override
    public void listElementClicked() {

    }

    @Override
    public void makingFileButtonClicked() {

    }

    @Override
    public void deleteFileButtonClicked() {

    }


    private SidePanel sidePanel;
}