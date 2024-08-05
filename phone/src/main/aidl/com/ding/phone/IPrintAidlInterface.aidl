// IPrintAidlInterface.aidl
package com.ding.phone;

// Declare any non-default types here with import statements

interface IPrintAidlInterface {


  //oneway 关键字表示 单向异步调用
  //客户端在调用服务端的方法时，不会等待服务端的响应，而是立即返回。这意味着客户端的调用线程不会被阻塞，可以继续执行其他任务
  //oneway String printText(String text);

  String printText(String text);

   String printName(String name);

}