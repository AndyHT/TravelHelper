//
//  NewDestinationTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/14/16.
//  Copyright © 2016 huoteng. All rights reserved.
//  添加新的目的地，根据输入查找目的地

import UIKit
import MapKit

class NewDestinationTableViewController: UITableViewController, SetDestinationDelegate, UITextFieldDelegate {
    
    var userDestination:MKMapItem? = nil
    @IBOutlet weak var cityNameInput: UITextField!
    @IBOutlet weak var startDatePicker: UIDatePicker!
    @IBOutlet weak var endDatePicker: UIDatePicker!

    @IBOutlet weak var startDateLabel: UILabel!
    @IBOutlet weak var endDateLabel: UILabel!
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        cityNameInput.delegate = self
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
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

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 6
    }
    
    @IBAction func saveDestination(sender: UIBarButtonItem) {
        
        
    }
    /*override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        // Configure the cell...

        return cell
    }*/


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
