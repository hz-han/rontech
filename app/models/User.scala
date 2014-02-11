package models
import play.api._
import play.api.data._
import play.api.data.Forms._

//每条留言的数据模型：包括两个字符串，分别存储名字和内容
case class User(username: String, password: String, passwordAgain : String, mail : String)


//全部留言的数据操作
object User {

  //用于存储全部留言的列表
  var list: List[User] = Nil

  //将留言追加在用于存储全部留言的列表前面
  def post(username: String, password: String, passwordAgain : String, mail : String) {
        list :::=  List(User(username, password, passwordAgain, mail))         

   }

  //定义表单及其校验要求，nonEmptyText表示该项内容不得为空
  val formRegister = Form(tuple(
      "username" -> nonEmptyText(minLength = 3),
      "password" -> nonEmptyText(minLength = 6),
      "passwordAgain" -> nonEmptyText,
      "mail" -> nonEmptyText
  ))
  
  val form = Form(tuple(
    "username" -> text,   
    "password" -> text
  ))
  
  val formFind = Form(tuple(
    "username" -> nonEmptyText,   
    "password" -> nonEmptyText,
    "mail" -> nonEmptyText
  ))

}