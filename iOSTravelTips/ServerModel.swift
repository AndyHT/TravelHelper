//
//  ServerModel.swift
//  TravelTips
//
//  Created by Teng on 12/28/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  需要连接服务端测试数据；需要搞一份数据库表设计，然后修改为单独用户的数据库数据

import Foundation
import Alamofire
import SwiftyJSON

class ServerModel: NSObject {
    
    //在使用网络前还需要判断网络状态
    
    //登录，未测试
    static func login(userEmail: String, withPass userPassMD5: String) -> Bool {
        var isSuccessed = false
        print("send http request....")
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/login", parameters: ["email": userEmail, "password": userPassMD5], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
//                            NSUserDefaults.standardUserDefaults().setObject(sessionID, forKey: "sessionID")
                            isSuccessed = true
                        } else {
                            //登录失败，重新登录
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        
        return isSuccessed
    }
    

    //注册新用户，未测试
    static func registerNewUser(userName: String, gender: String, email: String, passMD5: String) -> Bool {
        var isSuccessed = false
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/register", parameters: ["userName:" : userName, "gender" : gender, "email" : email, "password" : passMD5], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
//                            NSUserDefaults.standardUserDefaults().setObject(sessionID, forKey: "sessionID")
                            isSuccessed = true
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        return isSuccessed
    }
    
    //获取用户数据，未测试
    static func getData(sessionID: String, withType dataType:DataType) -> [UserData] {
        Alamofire.request(.POST, "http://10.0.1.32:8088/teavel_helper/data", parameters: ["sessionID": sessionID, "dataType": dataType.rawValue], encoding: .JSON)
            .responseJSON { response in
                print("Request:\t\(response.request)")  // original URL request
                print("Response:\t\(response.response)")// URL response
                print("Data:\t\(response.data)")     // server data
                
                
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    
                    let dataType = DataType(rawValue: json[0]["dataType"].int!)!
                    
                    switch dataType {
                    case .Plan:
                        print("拿到Plan，向本地数据库中写入Plan")
                        //遍历数组，将数据打包后作为函数返回值返回
                    case .Bill:
                        print("拿到Bill，向本地数据库中写入Plan")
                    case .Note:
                        print("拿到Note，向本地数据库中写入Plan")
                    case .Item:
                        print("")
                    }
                    
                    
                    print("Result:\(json)")
                    for index in json {
                        print("index:\(index)")
                    }
//                    print("Test:\(json[0]["dataType"].string)")
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        
        return []
    }
    
    
    //向后台发送新数据，未测试
    static func addNewData(newData: NSData, dataType:DataType, sessionID: String) -> Bool {
        var isSuccessed = false
        
        //在这里需要根据数据类型组装Request body
        
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/未确定", parameters: ["": ""], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            //数据插入Server数据库成功
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
//                            NSUserDefaults.standardUserDefaults().setObject(sessionID, forKey: "sessionID")
                            isSuccessed = true
                        } else {
                            //数据插入Server数据库失败
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        return isSuccessed
    }
    
    
    //删除数据，未测试
    static func deleteDataRecord(deleteItemIds: [String]) -> Bool {
        var isSuccessed = false
        
        
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/未确定", parameters: ["": ""], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            //Server数据库删除数据成功
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
//                            NSUserDefaults.standardUserDefaults().setObject(sessionID, forKey: "sessionID")
                            isSuccessed = true
                        } else {
                            //Server数据库删除数据失败
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }

        
        return isSuccessed
    }
}