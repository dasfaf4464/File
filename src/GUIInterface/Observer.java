package GUIInterface;

public interface Observer {
    interface installerObserver {//설치버튼시 다이얼로그 프레젠터에서 프레임 프레젠터로 전달

    }

    interface fileCreatorObserver {//파일 생성시 메뉴 프레젠터에서 메인 패널 프레젠터(텍스트 뷰 업데이트), 사이드 패널 프레젠터로 전달(파일 트리 업데이트, 파일 리스트 업데이트)

    }

    interface fileDeleterObserver {//파일 삭제시 메뉴 프레젠터에서 메인 패널 프레젠터(텍스트 뷰 업데이트), 사이드 패널 프레젠터로 전달(파일 트리 업데이트, 파일 리스트 업데이트)

    }

    interface fileTreeSelectionObserver {//파일트리에서 선택시 메인 패널 프레젠터로 전달(텍스트에디터 업데이트)

    }
}