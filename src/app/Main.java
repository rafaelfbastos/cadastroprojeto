package app;

import views.JanelaPrincipal;

public class Main {

    public static JanelaPrincipal janelaPrincipal;
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        janelaPrincipal = new JanelaPrincipal(controller);

    }
}
