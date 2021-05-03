package org.apache.spark.ml.feature

import org.apache.spark.ml.Transformer
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.util.{DefaultParamsReadable, DefaultParamsWritable, Identifiable}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset}

class CustomTransformer(override val uid: String) extends Transformer
  with DefaultParamsWritable
{

  def this() = this(Identifiable.randomUID("org.apache.spark.ml.feature.CustomTransformer"))

  override def transform(dataset: Dataset[_]): DataFrame = {
    dataset.withColumn("feature3", col("feature1") + col("feature2"))
  }

  override def copy(extra: ParamMap): CustomTransformer = defaultCopy(extra)
  override def transformSchema(schema: StructType): StructType = {
    StructType(Seq(StructField("feature1",IntegerType,true),
      StructField("feature2",IntegerType,true),
      StructField("feature3",IntegerType,true),
      StructField("label",IntegerType,true)))


  }
}

object CustomTransformer extends DefaultParamsReadable[CustomTransformer] {
  override def load(path: String): CustomTransformer = super.load(path)
}