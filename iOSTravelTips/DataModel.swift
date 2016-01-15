//
//  MD5.swift
//  TravelTips
//
//  Created by Teng on 1/5/16.
//  Copyright © 2016 huoteng. All rights reserved.
//  将密码转为MD5值

import Foundation
import CryptoSwift

func encryptPassword(userPass: String) -> String {
    return userPass.md5()
}

enum DataType:String {
    case Bill = "bill"
    case Note = "note"
    case Plan = "plan"
    case Item = "item"
}

struct Tip {
    let title:String
    let content:String
    let type:TipType
    let coverImgName:String
    init(title:String, content:String, type:TipType, coverImgName:String){
        self.title = title
        self.content = content
        self.type = type
        self.coverImgName = coverImgName
    }
}

enum TipType: String{
    case sceneIntro = "Attractions"
    case skill = "Life skills"
    case strategy = "Raiders"
    case diary = "Travel notes"
}

//通过继承实现泛化
class UserData {
    
}

class Bill: UserData {
    
}

class Note: UserData {
    
}

class Plan: UserData {
    var planID:Int
    var destinationLat:Double
    var destinationLon:Double
    var destinationName:String
    var startDate:NSDate
    var endDate:NSDate
    
    init(id:Int, lat:Double, lon:Double, name:String, startDate:NSDate, endDate:NSDate) {
        self.planID = id
        self.destinationLat = lat
        self.destinationLon = lon
        self.destinationName = name
        self.startDate = startDate
        self.endDate = endDate
    }
}