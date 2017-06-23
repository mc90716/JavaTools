package com.lr.load;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;

import scala.Tuple2;
/**
 * 加载模型并预测
 * @author yunxi
 *
 */
public class LRModelLoad {
	public static void main(String[] args) throws InterruptedException {
		SparkConf sparkConf = new SparkConf().setAppName("Spark").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		
		//加载模型
		final LogisticRegressionModel model = LogisticRegressionModel.load(jsc.sc(), "E://Study//GitHub//JavaTools//data//model");
		
		JavaRDD<String> rdd = jsc.textFile("E:\\Study\\GitHub\\JavaTools\\data\\Qualitative_Bankruptcy.data.txt");
		
		JavaRDD<LabeledPoint> parsedData = rdd.map(new Function<String, LabeledPoint>() {

			private static final long serialVersionUID = 1L;

			@Override 
			public LabeledPoint call(String line) throws Exception {
				String[] parts = line.split(",");
				double[] doubles = new double[parts.length - 1];
				for (int i = 0; i < parts.length - 1; i++) {
					doubles[i] = getDoubleValue(parts[i]);
				}
				return new LabeledPoint(getDoubleValue(parts[6]), Vectors.dense(doubles));
			}
		});
		
		JavaRDD<Tuple2<Double, Double>> labelAndPreds = parsedData
				.map(new Function<LabeledPoint, Tuple2<Double, Double>>() {

					private static final long serialVersionUID = 1L;

					public Tuple2<Double, Double> call(LabeledPoint labeledPoint) throws Exception {
						double prediction = model.predict(labeledPoint.features());
						return new Tuple2<>(labeledPoint.label(), prediction);
					}
				});
		
		JavaRDD<Tuple2<Double, Double>> result = labelAndPreds.filter(new Function<Tuple2<Double, Double>, Boolean>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean call(Tuple2<Double, Double> r) throws Exception {
				return r._1.doubleValue() != r._2.doubleValue();
			}
		});

		Thread.sleep(3000);
		
		double trainErr = (double)(result.count()) / (double)(parsedData.count());
		System.out.println("Train Error Rate is ====== [" + trainErr + "]");
		System.out.println("Error Count is ====== [" + result.count() + "]");
		System.out.println("Test Total Count is ====== [" + parsedData.count() + "]");
		
		jsc.close();
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
