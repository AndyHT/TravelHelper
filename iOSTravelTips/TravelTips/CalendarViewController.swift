//
//  CalendarViewController.swift
//  TravelTips
//
//  Created by 张挺然 on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit
import CVCalendar

class CalendarViewController: UIViewController, CVCalendarMenuViewDelegate, CVCalendarViewDelegate{

    @IBOutlet weak var calendarView: CVCalendarView!
    
    @IBOutlet weak var menuView: CVCalendarMenuView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.menuView.delegate = self
        self.calendarView.delegate = self

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        
        calendarView.commitCalendarViewUpdate()
        menuView.commitMenuViewUpdate()
    }
    
    func presentationMode() -> CalendarMode {
        return .MonthView
    }
    
    func firstWeekday() -> Weekday {
        return .Sunday
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
