//
//  WeatherViewController.swift
//  TravelTips
//
//  Created by Teng on 1/14/16.
//  Copyright © 2016 huoteng. All rights reserved.
//  天气显示页面

import UIKit
import MapKit


protocol SetWeatherInfoDelegate {
    func setDestWeatherInfo(currentWeather: NSDictionary)
}
class WeatherViewController: UIViewController {
    
    var destination:Plan? = nil
    
    @IBOutlet weak var destinationNameLabel: UILabel!
    @IBOutlet weak var weatherImage: UIImageView!
    @IBOutlet weak var weatherDescriptionLabel: UILabel!
    @IBOutlet weak var tempLabel: UILabel!
    @IBOutlet weak var startDateLabel: UILabel!
    @IBOutlet weak var countDownLabel: UILabel!
    @IBOutlet weak var weatherNavItem: UINavigationItem!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(destination)
        self.destinationNameLabel.text = destination!.destinationName
        let formatter = NSDateFormatter()
        formatter.dateFormat = "yyyy.MM.dd"
        let startDateText = formatter.stringFromDate(destination!.startDate)
        startDateLabel.text = startDateText
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        let now = NSDate().timeIntervalSince1970
        
        let interval = now - (destination!.startDate.timeIntervalSince1970)
        let leftDay = interval / 86400
        countDownLabel.text = "\(Int(leftDay))"
        print("interval:\(leftDay)")
        
        
        ServerModel.getWeatherData(lat: destination!.destinationLat, lon: destination!.destinationLon) { (weatherInfo) -> Void in
            //将数据填入Label
            let sunriseTime = weatherInfo["sys"]?["sunrise"] as! Double
            let sunsetTime = weatherInfo["sys"]?["sunset"] as! Double
            
            var isNight = false
            
            if (now < sunriseTime || now > sunsetTime) {
                isNight = true
            }
            
            self.updateWeatherIcon((weatherInfo["weather"] as! NSArray)[0]["id"] as! Int, nightTime: isNight)
            
//            self.weatherDescriptionLabel.text = (weatherInfo["weather"] as! NSArray)[0]["description"] as? String
            let temp_min = (weatherInfo["main"]?["temp_min"] as! Double) - 273.15
            let temp_max = (weatherInfo["main"]?["temp_max"] as! Double) - 273.15
            
            self.tempLabel.text = "\(temp_min)℃～\(temp_max)℃"
        }
    }
    
    
    func updateWeatherIcon(condition: Int, nightTime: Bool) {
        var imageName:String!
        switch condition {
        case 0..<300:
            if nightTime {
                imageName = "thunder_moon"
            } else {
                imageName = "thunder_sun"
            }
            self.weatherDescriptionLabel.text = "雷电"
        case 300..<500:
            if nightTime {
                imageName = "light_rain_moon"
            } else {
                imageName = "light_rain_sun"
            }
            self.weatherDescriptionLabel.text = "小雨"

        case 500..<600:
            if nightTime {
                imageName = "shower_moon"
            } else {
                imageName = "shower_sun"
            }
            self.weatherDescriptionLabel.text = "大雨"

        case 600..<700, 903:
            if nightTime {
                imageName = "snow_moon"
            } else {
                imageName = "snow_sun"
            }
            self.weatherDescriptionLabel.text = "雪"

        case 700..<771:
            if nightTime {
                imageName = "fog_moon"
            } else {
                imageName = "fog_sun"
            }
            self.weatherDescriptionLabel.text = "雾"

        case 771..<800, 904:
            if nightTime {
                imageName = "sunny_moon"
            } else {
                imageName = "sunny_sun"
            }
            self.weatherDescriptionLabel.text = "晴朗"

            
        case 800..<804:
            if nightTime {
                imageName = "cloudy_moon"
            } else {
                imageName = "cloudy_sun"
            }
            self.weatherDescriptionLabel.text = "多云"

            
        case 804:
            imageName = "overcast"
            self.weatherDescriptionLabel.text = "阴"

        case 900..<903, 905..<1000:
            if nightTime {
                imageName = "thunder_moon"
            } else {
                imageName = "thunder_sun"
            }
            self.weatherDescriptionLabel.text = "雷电"

        default:
            imageName = "weather_not_known"
            self.weatherDescriptionLabel.text = "未知"
            print("in default")
        }
        
        self.weatherImage.image = UIImage(named: imageName)
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
