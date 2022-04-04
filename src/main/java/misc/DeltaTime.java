package misc;

class DeltaTime extends Cell {
    @Override
    void Init() {
        new Thread(() -> {
            long current = System.nanoTime();
            long past = System.nanoTime();
            while (true) {

                current = System.nanoTime();
                long dt = current - past;

                emit(c -> {
                    c.Update(dt / 1e6);
                });
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                past = current;
            }
        }).start();
    }
}
