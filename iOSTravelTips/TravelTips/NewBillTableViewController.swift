//
//  NewBillTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit
import DatePickerCell

class NewBillTableViewController: UITableViewController, SetBillTypeDelegate {

    @IBOutlet weak var billDescriptionTextView: UITextView!
    @IBOutlet weak var billValueTextField: UITextField!
    @IBOutlet weak var billTypeImageView: UIImageView!
    @IBOutlet weak var billSumCell: UITableViewCell!
    @IBOutlet weak var billDescriptionCell: UITableViewCell!
    @IBOutlet weak var billTimeCell: DatePickerCell!
    @IBOutlet weak var billTypeCell: UITableViewCell!
    
    @IBOutlet var newBillTableView: UITableView!
    
    
    var billType: BillType? = nil
    var billDate: NSDate? = NSDate()
    var cells:NSArray = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        self.newBillTableView.rowHeight = UITableViewAutomaticDimension
        self.newBillTableView.estimatedRowHeight = 44
        
        self.billTimeCell.leftLabel.text = "选择消费时间"
        self.billTimeCell.datePicker.datePickerMode = UIDatePickerMode.Date
        
        let formatter = NSDateFormatter()
        formatter.dateFormat = "yyyy.MM.dd"
        self.billTimeCell.rightLabel.text = formatter.stringFromDate(self.billTimeCell.date)
        
        cells = [[billDescriptionCell, billSumCell, billTypeCell, billTimeCell]]
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func viewWillAppear(animated: Bool) {
        if let type = billType {
            switch type {
            case .Food:
                billTypeImageView.image = UIImage(named: "food_bill")
            case .Hotel:
                billTypeImageView.image = UIImage(named: "hotel_bill")
            case .Shopping:
                billTypeImageView.image = UIImage(named: "shopping_bill")
            case .Traffic:
                billTypeImageView.image = UIImage(named: "traffic_bill")
            case .Other:
                billTypeImageView.image = UIImage(named: "other_bill")
            }
        } else {
            billTypeImageView.image = UIImage(named: "other_bill")
        }
    }
    
    
    @IBAction func saveNewBillTapped(sender: AnyObject) {
        //保存新账单到服务器
        let description = billDescriptionTextView.text!
        let value = Double(billValueTextField.text!)
        if description != "" && value != nil && billType != nil && billDate != nil {
            let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as? String
            let dateFormat = NSDateFormatter()
            dateFormat.dateFormat = "yyyy-MM-dd"
            
            let tmp = ["sessionID", "dataType", "value", "bill_description", "bill_type", "bill_time"]
            let tmp2 = [sessionID!, DataType.Bill.rawValue, "\(value)", description, billType!.rawValue, dateFormat.stringFromDate(billDate!)]
            
            let newBill = NSDictionary(objects: tmp2, forKeys: tmp) as! [String: AnyObject]
            
            ServerModel.addNewData(newBill, dataType: DataType.Bill, callbcak: { (isSuccess) -> Void in
                if isSuccess {
                    print("Bill save success")
                    self.navigationController?.popToRootViewControllerAnimated(true)
                } else {
                    print("Bill save fail")
                }
            })
        }
    }
    
    func setBillTypeImage(type: BillType) {
        self.billType = type
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 4
    }
    
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
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Configure the cell...
        
        return cells[indexPath.section][indexPath.row] as! UITableViewCell
    }

    /*
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        // Configure the cell...

        return cell
    }
    */

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


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "setBillType" {
            let vc = segue.destinationViewController as! BillTypeTableViewController
            vc.setTypeImagaDelegate = self
        }
    }
    

}
