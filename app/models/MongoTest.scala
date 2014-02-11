package models
import com.mongodb.casbah.Imports._

case class MongoTest() extends App 

object MongoTest{
  val mongoConn = MongoConnection()
  val mongoColl = mongoConn("test")("User")
  val mongoCollWork = mongoConn("test")("Work")
  def add(username : String, password : String, passwordAgain : String, mail : String ) {  
    val bread1 = MongoDBObject("username" -> username,
      "password" -> password, "passwordAgain" -> passwordAgain, "mail" -> mail)
    mongoColl += bread1
  }
    
  def addWork(username : String, workStart : String, workEnd : String) {  
    val bread2 = MongoDBObject("username" -> username,
      "workStart" -> workStart, "workEnd" -> workEnd)
    mongoCollWork += bread2
  }
  
  def chkPwd(username : String, password : String ) : Boolean = {
    val cursor = mongoColl.find(MongoDBObject("username" -> username, "password" -> password))
    while(cursor.hasNext){
      return true
    }
    false
  }
  
  def chkUser(username : String) : Boolean = {
    val cursor = mongoColl.find(MongoDBObject("username" -> username))
     while(cursor.hasNext){
      return true
    }
    false
  }
}