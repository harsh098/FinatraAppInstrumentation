package com.one2nc.scalafinatrainstrumentation

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.annotations.RouteParam

case class ItemDTO(
                    name:  String,
                    price: Double,
                    description: Option[String],
                    tax: Option[Double],
                    request: Request
                  )
