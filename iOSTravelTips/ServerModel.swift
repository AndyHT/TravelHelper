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
    
    //登录，OK
    static func login(userEmail: String, withPass userPassMD5: String, callback: (String?) -> Void) {
        print("send http request....")
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/login", parameters: ["email": userEmail, "password": userPassMD5], encoding: .JSON)
            .responseJSON { response in
                print("VALUE:\(response.result.value)")
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            print("REUSLT:\(result)")
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
                            callback(sessionID)
                        } else {
                            //登录失败，重新登录
                            callback(nil)
                            print("登录失败")
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
    }
    

    //注册新用户，OK
    static func registerNewUser(userName: String, gender: String, email: String, passMD5: String) -> Bool {
        var isSuccessed = false
        print("register send request...")
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/register", parameters: ["userName" : userName, "gender" : gender, "email" : email, "password" : passMD5], encoding: .JSON)
            .responseJSON { response in
                print("VALUE:\(response.result.value)")
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            print("注册成功")
                            isSuccessed = true
                        } else {
                            print("注册失败")
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        return isSuccessed
    }
    
    //获取用户数据，OK
    static func getData(sessionID: String, withType dataType:DataType, callback: (JSON) -> Void) {
        print("send request...")
        
        
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/data", parameters: ["sessionID": sessionID, "dataType": dataType.rawValue], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    
                    callback(json)
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
    }
    
    static func getTips(sessionID: String, destination: String, callback: (JSON) -> Void ) {
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/data", parameters: ["sessionID": sessionID, "dataType": "tip", "destination": destination], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    
                    callback(json)
                } else {
                    print("没有拿到tips")
                }
        }
    }
    
    
    //向后台发送新数据，OK
    static func addNewData(parameter: [String : AnyObject], dataType: DataType, callbcak: (Bool) -> Void) {
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/returnData", parameters: parameter, encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            //数据插入Server数据库成功
                            print("插入数据成功")
                            callbcak(true)
                        } else {
                            //数据插入Server数据库失败
                            callbcak(false)
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                    callbcak(false)
                }
        }
    }
    
    
    //删除数据，OK
    static func deleteDataRecord(parameter: [String: AnyObject]) -> Bool {
        var isSuccessed = false
        
        
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/delete", parameters: parameter, encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            //Server数据库删除数据成功
                            print("删除数据成功")
                            isSuccessed = true
                        } else {
                            //Server数据库删除数据失败
                            print("数据删除失败")
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }

        
        return isSuccessed
    }
    
    //使用经纬度获取天气数据
    static func getWeatherData(lat lat: Double, lon: Double, callBack: (NSDictionary) -> Void) {
        print("lat:\(lat),lon:\(lon)")
        Alamofire.request(.GET, "http://api.openweathermap.org/data/2.5/weather", parameters: ["lat":lat, "lon":lon, "APPID": "1546641be462e54931b559ba73d938ce"])
        .responseJSON { response in
            if let resultValue = response.result.value {
                print("value:\(resultValue)")
                let data = resultValue as! NSDictionary
                //天气数据整理
                if let _ = data["sys"]?["country"] as? String {
                    
                    //将数据传回给View
                    callBack(data)
                } else {
                    print("没有获取到天气数据")
                }
                
            } else {
                print("获取天气数据失败")
            }
        }
    }
    
}