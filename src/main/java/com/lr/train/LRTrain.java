package com.lr.train;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;

import scala.Tuple2; 

/**
 * 训练出模型之后预测一批数据
 * @author yunxi
 *
 */
public class LRTrain {

	private static JavaSparkContext jsc = null;

	public static void main(String[] args) throws InterruptedException {
		SparkConf sparkConf = new SparkConf().setAppName("Spark").setMaster("local");
		jsc = new JavaSparkContext(sparkConf);

		String path = "E:\\Study\\GitHub\\JavaTools\\data\\Qualitative_Bankruptcy.data.txt";

		JavaRDD<LabeledPoint> parsedData = loadAndParseData(path);

		JavaRDD<LabeledPoint>[] splits = parsedData.randomSplit(new double[] { 0.9, 0.1 }, System.currentTimeMillis());
		// 训练数据
		JavaRDD<LabeledPoint> trainingData = splits[0];
		// 测试数据
		JavaRDD<LabeledPoint> testData = splits[1];
		// 二分类
		final LogisticRegressionModel model = new LogisticRegressionWithLBFGS().setNumClasses(2)
				.run(trainingData.rdd());

		// 输出概率值，如果注释掉，输出的是二分类的结果
		model.clearThreshold();
		// 保存模型
		model.save(jsc.sc(), "E:\\Study\\GitHub\\JavaTools\\data\\model");

		// 计算AUC的值
		// double auc = new
		// BinaryClassificationMetrics(labelAndPreds).areaUnderROC();

		String resultSavePath = "E:\\Study\\GitHub\\JavaTools\\data\\predict_result";

		predictAndSave(model, testData, resultSavePath);

		jsc.close();
	}

	/**
	 * 预测并保存结果
	 * 
	 * @param model
	 * @param testData
	 * @param modelSavePath
	 */
	private static void predictAndSave(final LogisticRegressionModel model, JavaRDD<LabeledPoint> testData,
			String resultSavePath) {
		JavaRDD<Tuple2<Double, Double>> labelAndPreds = testData
				.map(new Function<LabeledPoint, Tuple2<Double, Double>>() {

					private static final long serialVersionUID = 1L;

					public Tuple2<Double, Double> call(LabeledPoint labeledPoint) throws Exception {
						double prediction = model.predict(labeledPoint.features());
						return new Tuple2<>(labeledPoint.label(), prediction);
					}
				});

		labelAndPreds.saveAsTextFile(resultSavePath);

	}

	/**
	 * 加载并解析数据
	 * 
	 * @param path
	 * @return
	 */
	private static JavaRDD<LabeledPoint> loadAndParseData(String path) {
		JavaRDD<String> rdd = jsc.textFile(path);
		JavaRDD<LabeledPoint> parsedData = rdd.map(new Function<String, LabeledPoint>() {
			private static final long serialVersionUID = 1L;

			@Override
			public LabeledPoint call(String line) throws Exception {
				String[] parts = line.split(",");
				double[] doubles = new double[parts.length - 1];
				for (int i = 0; i < parts.length - 1; i++) {
					doubles[i] = getDoubleValue(parts[i]);
				}
				// parts[6]是要结果，doubles是特征值
				double label = getDoubleValue(parts[6]);
				return new LabeledPoint(label, Vectors.dense(doubles));
			}
		});
		return parsedData;
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
