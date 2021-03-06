//
//  NewDestinationTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/14/16.
//  Copyright © 2016 huoteng. All rights reserved.
//  添加新的目的地，根据输入查找目的地

import UIKit
import MapKit
import DatePickerCell

protocol AddNewPlanDelegate {
    func addNewPlan(newPlan: Plan)
}

class NewDestinationTableViewController: UITableViewController, SetDestinationDelegate, UITextFieldDelegate {
    
    var userDestination:MKMapItem? = nil
    @IBOutlet weak var cityNameInput: UITextField!

    @IBOutlet weak var inputCell: UITableViewCell!
    
    @IBOutlet weak var startDatePickerCell: DatePickerCell!
    @IBOutlet weak var endDatePickerCell: DatePickerCell!
    
    var cells:NSArray = []
    var newPlanDelegate:AddNewPlanDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        cityNameInput.delegate = self
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    
        
        self.tableView.rowHeight = UITableViewAutomaticDimension
        self.tableView.estimatedRowHeight = 44
        
        startDatePickerCell.leftLabel.text = "选择开始时间"
        endDatePickerCell.leftLabel.text = "选择结束时间"
        
        startDatePickerCell.datePicker.datePickerMode = UIDatePickerMode.Date
        endDatePickerCell.datePicker.datePickerMode = UIDatePickerMode.Date
        
        let formatter = NSDateFormatter()
        formatter.dateFormat = "yyyy.MM.dd"
        startDatePickerCell.rightLabel.text = formatter.stringFromDate(startDatePickerCell.date)
        endDatePickerCell.rightLabel.text = formatter.stringFromDate(endDatePickerCell.date)
        
        cells = [[inputCell, startDatePickerCell, endDatePickerCell]]
        
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        self.cityNameInput.resignFirstResponder()
        startSearchDestination()
        
        return true
    }
    
    
    func startSearchDestination() {
        //查找目的地
        let request = MKLocalSearchRequest()//新建一次查询
        request.naturalLanguageQuery = self.cityNameInput.text
        let search = MKLocalSearch(request: request)
        
        //开始查找，当查询完成后会调用参数里的闭包
        search.startWithCompletionHandler { (response, error) in
            //当查询失败时打印error到控制台
            guard let response = response else {
                print("Search error: \(error)")
                return
            }
            
            //将查找结果显示在TableView中
            let resultTableView = self.storyboard!.instantiateViewControllerWithIdentifier("SearchResultTableView") as! DestinationResultTableViewController
            resultTableView.resultArray = response.mapItems
            resultTableView.setDestinationDelegate = self
            
            self.navigationController?.pushViewController(resultTableView, animated: true)
            
            //遍历查询结果，结果封装在MKMapItem中
            for item in response.mapItems {
                print("item:\(item)")
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func setUserDestinationResult(destination: MKMapItem) {
        //store destination information
        self.userDestination = destination
    }


    // MARK: - Table view data source

    
    override func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        let cell = self.tableView(tableView, cellForRowAtIndexPath: indexPath)
        if cell.isKindOfClass(DatePickerCell) {
            return (cell as! DatePickerCell).datePickerHeight()
        }
        return super.tableView(tableView, heightForRowAtIndexPath: indexPath)
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let cell = self.tableView(tableView, cellForRowAtIndexPath: indexPath)
        if cell.isKindOfClass(DatePickerCell) {
            let datePickerTableViewCell = cell as! DatePickerCell
            datePickerTableViewCell.selectedInTableView(tableView)
            self.tableView.deselectRowAtIndexPath(indexPath, animated: true)
        }
    }
    
//    override func touchesEnded(touches: Set<UITouch>, withEvent event: UIEvent?) {
//        self.cityNameInput.resignFirstResponder()
//        startSearchDestination()
//    }
    
    @IBAction func saveDestination(sender: UIBarButtonItem) {
        
        if let destination = userDestination?.name {
            let dateFormat = NSDateFormatter()
            dateFormat.dateFormat = "yyyy-MM-dd"
            
            let lat = userDestination!.placemark.location!.coordinate.latitude
            let lon = userDestination!.placemark.location!.coordinate.longitude
            let start = cells[0][1].date!
            let end = cells[0][2].date!
            let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as! String
//            print("Date:\(start)\t\(end)")
            
            let tmp = ["sessionID", "dataType", "plan_num", "start_date", "end_date", "destination", "latitude", "longitude"]
            let tmp2 = [sessionID, DataType.Plan.rawValue, "1", dateFormat.stringFromDate(start), dateFormat.stringFromDate(end), destination, "\(lat)", "\(lon)"]
            let newPlan = NSDictionary(objects: tmp2, forKeys: tmp) as! [String : AnyObject]
//            print(newPlan)
            //保存Plan数据到数据库
            ServerModel.addNewData(newPlan, dataType: DataType.Plan) { (isSuccess) -> Void in
                if isSuccess {
                    //保存数据成功
                    print("Save Plan Seucceed")
                    if let delegate = self.newPlanDelegate {
                        delegate.addNewPlan(Plan(id: 0, lat: lat, lon: lon, name: destination, startDate: start, endDate: end))
                    }

                    self.navigationController?.popToRootViewControllerAnimated(true)
                } else {
                    //保存数据失败
                    print("Save Plan Fail")
                }
            }
        }
        
        
        
        
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {

        // Configure the cell...

        return cells[indexPath.section][indexPath.row] as! UITableViewCell
    }


    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
