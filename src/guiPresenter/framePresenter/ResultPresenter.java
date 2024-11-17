package guiPresenter.framePresenter;

import GUIInterface.ResultInterface;
import guiView.framePanel.ResultView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPresenter implements ResultInterface.Presenter {
    private final ResultView resultView;

    public ResultPresenter(ResultView resultView) {
        this.resultView = resultView;
        ResultViewListener resultViewListener = new ResultViewListener();
        resultView.setResultViewListener(resultViewListener);
    }

    public class ResultViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
