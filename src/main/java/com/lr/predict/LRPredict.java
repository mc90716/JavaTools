package com.lr.predict;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;

/**
 * 训练处模型之后预测一行数据
 * @author yunxi
 *
 */
public class LRPredict {
	public static void main(String[] args) throws InterruptedException {
		
		SparkConf sparkConf = new SparkConf().setAppName("Spark").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		JavaRDD<String> rdd = jsc.textFile("E:\\Study\\GitHub\\JavaTools\\data\\Qualitative_Bankruptcy.data.txt");
		
		JavaRDD<LabeledPoint> parsedData = rdd.map(new Function<String, LabeledPoint>() {

			private static final long serialVersionUID = 1L;

			@Override
			public LabeledPoint call(String line) throws Exception {
				String[] parts = line.split(",");
				double[] doubles = new double[parts.length];
				for (int i = 0; i < parts.length; i++) {
					doubles[i] = getDoubleValue(parts[i]);
				}
				return new LabeledPoint(getDoubleValue(parts[6]), Vectors.dense(doubles));
			}
		});
		
		JavaRDD<LabeledPoint>[] splits = parsedData.randomSplit(new double[] { 0.9, 0.1 }, System.currentTimeMillis());
		JavaRDD<LabeledPoint> trainingData = splits[0];
		
		final LogisticRegressionModel model = new LogisticRegressionWithLBFGS().setNumClasses(2)
				.run(trainingData.rdd());
		
		jsc.close();
		
		Thread.sleep(5000);
		predictByModel(model);
	}

	private static void predictByModel(LogisticRegressionModel model) {
		String[] parts = "A,N,N,A,P,P,NB".split(",");
		double[] doubles = new double[parts.length];
		for (int i = 0; i < parts.length; i++) {
			doubles[i] = getDoubleValue(parts[i]);
		}
		
		LabeledPoint lp = new LabeledPoint(getDoubleValue(parts[6]), Vectors.dense(doubles));
		double prediction = model.predict(lp.features());
		System.out.println("label is [" + lp.label() + "], Prediction is [" + prediction + "]");
	}
	
	private static double getDoubleValue(String input) {
		double result = 0.0;
		if (input.equals("P")) {
			result = 3.0;
		} else if (input.equals("A")) {
			result = 2.0;
		} else if (input.equals("N")) {
			result = 1.0;
		} else if (input.equals("NB")) {
			result = 1.0;
		} else if (input.equals("B")) {
			result = 0.0;
		}
		return result;
	}

}
