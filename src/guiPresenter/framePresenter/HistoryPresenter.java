package guiPresenter.framePresenter;

import GUIInterface.frame.HistoryInterface;
import guiView.framePanel.HistoryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryPresenter implements HistoryInterface.Presenter {
    private final HistoryView historyView;

    public HistoryPresenter(HistoryView historyView) {
        this.historyView = historyView;
        HistoryViewListener historyViewListener = new HistoryViewListener();
        this.historyView.setHistoryViewListener(historyViewListener);
    }

    public class HistoryViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
