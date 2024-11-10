package ide;


/**
 * refactoring CLI to GUI
 * MVC or MVP Pattern?
 * 목표 1 결합도 최소화
 * 목표 2 모든 메소드 주석
 * 목표 3 인터페이스 설계 문서화
 *
 * ## 패턴 구성시 도메인별 패키지 구성에서 계층형 패키지로 변경
 *
 * ?? 메인 메소드가 있는 클래스는 진입점 역할만 하는 것이 좋은가?
 */
public class GUImain{

    public static void main(String[] args) {
        GUIWindow window = new GUIWindow();
    }
}