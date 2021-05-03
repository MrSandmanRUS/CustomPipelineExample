import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{CustomEstimator, CustomTransformer}
import org.apache.spark.ml.{Pipeline}

object Train {
  def main(args: Array[String]) = {
    val spark = SparkSession
      .builder
      .appName("CustomPipelineExample")
      .config("spark.master", "local")
      .getOrCreate()

    val modelPath = "customModel"

    import spark.implicits._

    val df = Seq(
      (1, 1, 1),
      (2, 2, 2),
      (3, 3, 3)
    ).toDF("feature1", "feature2", "label")

    val customTransformer = new CustomTransformer()

    val customEstimator = new CustomEstimator()

    val pipeline = new Pipeline().setStages(Array(customTransformer, customEstimator))

    val model = pipeline.fit(df)

    model.write.overwrite().save(modelPath)
  }
}
