package controllers

import play.api._
import play.api.mvc._
import play.api.templates._
import models._

object Application extends Controller {

  //个人信息显示界面
  def info = Action {
    Ok(views.html.info(Work.list,Work.form))
  }
  
    //登陆界面
  def loginPage = Action {     
    Ok(views.html.login(User.list, User.form))
  }
  
  	//注册界面
  def registerPage = Action{
     Ok(views.html.register(User.list, User.formRegister))
  }
  
  	//登录操作
  def login = Action{
      implicit request =>
        User.form.bindFromRequest.fold(
        //处理错误
        errors => BadRequest(views.html.login(User.list, errors)),
        {
          case (username, password) =>
            if(MongoTest.chkPwd(username, password)){
              Work.post(username, null, null)
              //重新定向到显示留言列表和发言表单页面
              Redirect(routes.Application.info)// 登陆成功后进入勤务信息的登记    
            }
            else
               Ok(Html("<p>用户名或密码错误，请重新登录</p>"))
        })
  }
  
    //注册
  def register = Action {
    implicit request =>
      User.formRegister.bindFromRequest.fold(
        //处理错误
        errors => BadRequest(views.html.register(User.list, errors)),
        {
          case (username, password, passwordAgain, mail) =>
            if(!MongoTest.chkUser(username)){
              if(password == passwordAgain){
	              MongoTest.add(username, password, passwordAgain, mail)
	              //重新定向到显示留言列表和发言表单页面
	              Redirect(routes.Application.loginPage) 
              }
              else
                Ok(Html("<p>两次密码不一样，请重新输入</p>"))
            }
            else
               Ok(Html("<p>已被注册</p>"))
            
        })

  }
  
  //勤务
  def work = Action {                                                      
     implicit request =>
      Work.form.bindFromRequest.fold(
        //处理错误
        errors => BadRequest(views.html.info(Work.list, errors)),
        {
          case (username, workStart, workEnd) =>
            MongoTest.addWork(username, workStart, workEnd)

            Redirect(routes.Application.info)
        })
  }
}