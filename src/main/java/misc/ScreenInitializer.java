package misc;

import javax.swing.*;

class ScreenInitializer extends Cell {

    private JFrame frame;

    @Override
    void Init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        emit(cell -> cell.ScreenInit(frame));
    }

    @Override
    void ScreenSizeChanged(int width, int height) {
        frame.setSize(width, height);
    }
}
