//
//  ViewController.swift
//  TravelTips
//
//  Created by Teng on 12/20/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  首页内容展示

import UIKit
import Alamofire

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
       
        //test
//        ServerModel.login("test", withPass: "lalalala")//OK
//        ServerModel.registerNewUser("", gender: "", email: "", passMD5: "")//OK
        ServerModel.getData("", withType: .Plan)//OK

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

