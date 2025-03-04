package com.one2nc.scalafinatrainstrumentation

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.logging.Logger


class APIController extends Controller {
  get("/") {
    request: Request => response.ok.json(Map("message" -> "Hi Mom!"))
  }

  get("/items/:item_id") {
    request: Request => {
      val itemId = request.params("item_id").toInt
      if(itemId==0) response.notFound.json(Map("message"->s"Item ID: $itemId not found"))
      else response.ok.json(Map("id" -> itemId ,"message" -> s"item_id $itemId"))
    }
  }

  post("/items") {
    item: ItemDTO => {
      logger.debug(s"Posting Item: $item")
      if(item.price <=0 )
        response.badRequest.json(Map("status_code"->"400", "detail"->"Price must be positive"))
      else
        response.ok.json(Map("message"->"Item Created", "item"->s"$item"))
    }
  }

  put("/items/:item_id"){
    item: ItemDTO => {
      val itemId = item.request.params("item_id").toInt
      if(itemId==0)
        response.notFound.json(Map("status_code"->"404", "message"->"Item not found"))
      else if (item.price<=0)
        response.badRequest.json(Map("status_code"->"400", "detail"->"Price must be positive"))
      else
        response.ok.json(Map("message"->"Item Updated", "item_id"->s"$itemId", "item"->s"$item"))
    }
  }

  patch("/items/:item_id"){
    item: ItemDTO => {
      val itemId = item.request.params("item_id").toInt
      logger.debug(s"Getting ID: $itemId")
      if(itemId==0)
        response.notFound.json(Map("status_code"->"404", "message"->"Item not found"))
      else
        response.ok.json(Map("message"->"Item Partially Updated", "item_id"->s"$itemId", "item"->s"$item"))
    }
  }

  delete("/items/:item_id"){
    request: Request => {
      val itemId = request.params("item_id").toInt
      logger.debug(s"Deleting : $itemId")
      if(itemId==0) {
        logger.error("Hawwww!")
        response.internalServerError
      } else {
        response.ok.json(Map("message" -> s"Item $itemId deleted"))
      }
    }
  }

  get("/redirect"){
    _: Request => response.movedPermanently("Moved")
  }

  get("/server-error"){
    _: Request => response.internalServerError
  }
}
