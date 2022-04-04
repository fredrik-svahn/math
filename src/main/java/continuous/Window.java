package continuous;

import javax.swing.*;

class Window extends GameSystem {
    private JFrame frame;

    @Override
    void Init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        emit(s -> s.FrameCreated(frame));
    }

    @Override
    void WindowVisible() {
        frame.setVisible(true);
    }
}
