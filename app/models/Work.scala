package models
import play.api._
import play.api.data._
import play.api.data.Forms._

//勤务信息，工作时间
case class Work(username: String, workStart: String, workEnd : String)

//work
object Work {

  //用于存储全部工作的列表
  var list: List[Work] = Nil

  //将工作追加在用于存储全部工作信息的列表前面
  def post(username: String, workStart: String, workEnd : String) {
        list :::=  List(Work(username, workStart, workEnd))         

   }

  //定义表单及其校验要求，nonEmptyText表示该项内容不得为空
  val form = Form(tuple(
      "username" -> nonEmptyText,   
      "workStart" -> nonEmptyText,
      "workEnd" -> nonEmptyText
  ))

}