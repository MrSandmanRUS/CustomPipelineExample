import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.{SparkSession}

object Test {
  def main(args: Array[String]) = {
    val spark = SparkSession
      .builder
      .appName("CustomPipelineExample")
      .config("spark.master", "local")
      .getOrCreate()

    val modelPath = "customModel"
    import spark.implicits._

    val df = Seq(
      (1, 1),
      (2, 2),
      (3, 3)
    ).toDF("feature1", "feature2")

    val model = PipelineModel.load(modelPath)

    val res = model.transform(df)
    res.show(10, false)

  }
}
