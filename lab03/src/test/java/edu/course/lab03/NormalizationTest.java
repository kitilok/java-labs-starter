package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NormalizationTest {

    @Test
    void minMaxNormalizationWorks() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        double[] result = strategy.normalize(new double[]{10, 20, 30});

        assertArrayEquals(
                new double[]{0.0, 0.5, 1.0},
                result,
                0.0001
        );
    }

    @Test
    void minMaxNormalizationDoesNotChangeInput() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        double[] values = {10, 20, 30};

        strategy.normalize(values);

        assertArrayEquals(
                new double[]{10, 20, 30},
                values,
                0.0001
        );
    }

    @Test
    void meanCenteringWorks() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();

        double[] result = strategy.normalize(new double[]{10, 20, 30});

        assertArrayEquals(
                new double[]{-10.0, 0.0, 10.0},
                result,
                0.0001
        );
    }

    @Test
    void meanCenteringDoesNotChangeInput() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();

        double[] values = {10, 20, 30};

        strategy.normalize(values);

        assertArrayEquals(
                new double[]{10, 20, 30},
                values,
                0.0001
        );
    }

    @Test
    void featureProcessorUsesStrategy() {
        FeatureProcessor processor =
                new FeatureProcessor(new MeanCenteringNormalization());

        double[] result = processor.process(new double[]{10, 20, 30});

        assertArrayEquals(
                new double[]{-10.0, 0.0, 10.0},
                result,
                0.0001
        );
    }
}