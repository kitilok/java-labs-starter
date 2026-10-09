package edu.course.lab03;

public class FeatureProcessor {

    private final NormalizationStrategy strategy;

        public FeatureProcessor(NormalizationStrategy strategy){
            this.strategy = strategy;
        }

        public double[] process(double[] values){
            return strategy.normalize(values);
        }
    }
