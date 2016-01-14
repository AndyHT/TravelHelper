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
    static func login(userEmail: String, withPass userPassMD5: String) -> Bool {
        var isSuccessed = false
        print("send http request....")
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/login", parameters: ["email": userEmail, "password": userPassMD5], encoding: .JSON)
            .responseJSON { response in
                print("VALUE:\(response.result.value)")
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            print("REUSLT:\(result)")
                            print("get response")
                            let sessionID = json["sessionID"].string!
                            print("sessionID:\t\(sessionID)")
                            NSUserDefaults.standardUserDefaults().setObject(sessionID, forKey: "sessionID")
                            isSuccessed = true
                        } else {
                            //登录失败，重新登录
                            print("登录失败")
                        }
                    }
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        
        return isSuccessed
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
    static func getData(sessionID: String, withType dataType:DataType) -> [UserData] {
        print("send request...")
        
        
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/data", parameters: ["sessionID": sessionID, "dataType": dataType.rawValue], encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    
                    let dataType = DataType(rawValue: json[0]["dataType"].string!)!
                    
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
//                    print("Test:\(json[0]["dataType"].string)")
                } else {
                    //没有接收到response
                    print("未收到response")
                }
        }
        
        return []
    }
    
    
    //向后台发送新数据，OK
    static func addNewData(parameter: [String: AnyObject]) -> Bool {
        var isSuccessed = false
                
        Alamofire.request(.POST, "http://10.0.1.32:8088/travel_helper/returnData", parameters: parameter, encoding: .JSON)
            .responseJSON { response in
                if let resultValue = response.result.value {
                    let json = JSON(resultValue)
                    if let result = json["result"].bool {
                        if result {
                            //数据插入Server数据库成功
                            print("插入数据成功")
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
    
    //获取天气数据
    static func getWeatherData(lat lat: Double, lon: Double) {
        var weatherData:NSDictionary? = nil
        Alamofire.request(.GET, "http://api.openweathermap.org/data/2.5/weather", parameters: ["lat":lat, "lon":lon, "APPID": "1546641be462e54931b559ba73d938ce"])
        .responseJSON { response in
            if let resultValue = response.result.value {
                print("value:\(resultValue)")
                let data = resultValue as! NSDictionary
                //天气数据整理
                if let countryName = data["sys"]?["country"] as? String {
                    let keyStr = "country name condition sunrise sunset temp_max temp_min"
                    let keyArr:NSArray = keyStr.componentsSeparatedByString(" ")
                    let valueArr:NSArray = [
                            countryName,
                            (data["name"] as? String)!,
                            (data["cod"] as? Int)!,
                            (data["sys"]?["sunrise"] as? Int)!,
                            (data["sys"]?["sunset"] as? Int)!,
                            (data["main"]?["temp_max"] as? Int)!,
                            (data["main"]?["temp_min"] as? Int)!
                    ]
                    
                    weatherData = NSDictionary(objects: keyArr as [AnyObject], forKeys: valueArr as! [NSCopying])
                    
                    //在这里需要想办法将数据传回给View
                    
                    print("weatherData:\(weatherData)")
                } else {
                    print("没有获取到天气数据")
                }
                
            } else {
                print("获取天气数据失败")
            }
        }
    }
    
}