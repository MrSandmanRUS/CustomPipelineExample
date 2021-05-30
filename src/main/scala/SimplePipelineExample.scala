import org.apache.hadoop.conf.Configuration
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.Pipeline

object SimplePipelineExample {
  def main(args: Array[String]) = {
    val spark = SparkSession
      .builder
      .appName("SimplePipelineExample")
      .config("spark.master", "local")
      .getOrCreate()

    val hadoopConfig: Configuration = spark.sparkContext.hadoopConfiguration
    hadoopConfig.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName)
    hadoopConfig.set("fs.file.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName)

    import spark.implicits._

    val df = Seq(
      ("str1 test"),
      ("str2 test test test here here we go"),
      ("str1 str1")
    ).toDF("in")
    df.show()
    val tokenizer = new Tokenizer().setInputCol("in").setOutputCol("out")
    // HashingTF, maps a sequence of terms to their term frequencies using the hashing trick
    val hashingTF = new HashingTF().setNumFeatures(10).setInputCol(tokenizer.getOutputCol).setOutputCol("features")

    val pipeline = new Pipeline().setStages(Array(tokenizer, hashingTF))
    // Fit model
    val model = pipeline.fit(df)

    val res = model.transform(df)

    res.show(10, false)

  }
}
