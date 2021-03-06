package org.clowdy.maths.matrix;

import org.junit.jupiter.api.BeforeEach;

public abstract class MatrixTest<M extends AbstractMatrix<M>> {
    protected M matrix;

    protected abstract M newMatrixInstance();

    @BeforeEach
    public void setUp() {
        matrix = newMatrixInstance();
    }
}