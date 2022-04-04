package misc;

class DeltaUpdate extends Cell {
    private double msCount;

    @Override
    void Update(double ms) {
        msCount += ms;

        while(msCount >= 16) {
            Cell.emit(Cell::UpdateDelta);
            msCount -= 16;
        }
    }
}
