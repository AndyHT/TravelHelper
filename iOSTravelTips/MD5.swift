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