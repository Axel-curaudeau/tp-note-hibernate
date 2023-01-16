import controller.Controller;
import view.CommandLineView;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller(new CommandLineView());
        controller.run();
    }
}
